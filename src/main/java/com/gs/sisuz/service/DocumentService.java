package com.gs.sisuz.service;

import com.gs.sisuz.model.*;
import com.gs.sisuz.multifacturalo.DocumentsRestClient;
import com.gs.sisuz.multifacturalo.dto.*;
import com.gs.sisuz.repository.EnterpriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class DocumentService {

    private final Logger log = LoggerFactory.getLogger(DocumentService.class);

    private final EnterpriseRepository enterpriseRepository;
    private final PaymentVoucherService paymentVoucherService;

    private final OrderDetailService orderDetailService;

    private final ProductService productService;

    private DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat dtmf = new SimpleDateFormat("HH:mm:ss");

    public DocumentService(EnterpriseRepository enterpriseRepository,
                           PaymentVoucherService paymentVoucherService,
                           OrderDetailService orderDetailService,
                           ProductService productService) {
        this.enterpriseRepository = enterpriseRepository;
        this.paymentVoucherService = paymentVoucherService;
        this.orderDetailService = orderDetailService;
        this.productService = productService;
    }

    void processDocumentsPending() throws MalformedURLException, URISyntaxException {
        log.info("Documents Pending");
        processDocumentsBF();

    }

    private void processDocumentsBF() throws MalformedURLException, URISyntaxException {

        var vouchers = listOrdersPendingF("001","001", "2024-04-08", "2024-04-08");
        sendDocuments(vouchers);
    }

    private List<PaymentVoucher> listOrdersPendingF(String codGrupoCia, String codLocal, String dateFecIni, String dateFecFin) {
        //2. Envio de facturas
        List<PaymentVoucher> listVoucherF = paymentVoucherService.listPendingVouchers(codGrupoCia, codLocal, "02", dateFecIni, dateFecIni);
        //3. Envio de NC de facturas
        List<PaymentVoucher> listVoucherFN = Collections.emptyList(); //paymentVoucherService.listPendingNCredit(codGrupoCia, codLocal, "1", dateFecIni, dateFecIni);

        List<PaymentVoucher> combinedList = Stream.of(listVoucherF, listVoucherFN)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        return combinedList;
    }

    private void sendDocuments(List<PaymentVoucher> vouchers) throws MalformedURLException, URISyntaxException {

        String codNegocio = "001"; //event.getCodNegocio();
        var companyId = new CompanyId("001", codNegocio);
        Enterprise enterprise = enterpriseRepository.findByCodGrupoCiaAndCodCia(companyId.codGrupoCia(), companyId.codCia());
        DocumentsRestClient client = DocumentsRestClient.instanceClientConfig(enterprise,"/api/documents");

        //1. Read all vouchers pending by company
        for(PaymentVoucher voucher : vouchers) {
            //2.1 Make payload of document
            Document document = makePayload(voucher);

            //2.2 Update document with response
            String numeroDoc = voucher.cePrefijo() + voucher.ceSerie()+"-"+voucher.ceCorrelativo();
            if(document==null){
                log.warn("Documento {} no enviado",numeroDoc);
            } else {
                var response = client.store(document);
                updateSentVoucher(voucher, response);
            }
        }
        log.info("Fin proceso: {}", vouchers.size());
    }

    public Document makePayload(PaymentVoucher voucher) {

        Document document = null;
        //if(voucher.valTipoDocumentoSunat().equals("07")) {
            //document = makePayloadNC(voucher, event);
        //} else {
            document = makePayloadFB(voucher);
        //}

        return document==null?null:addDetails(document,voucher);
    }

    private Document makePayloadFB(PaymentVoucher voucher) {
        Document document = new Document();
        document.setSerie(voucher.cePrefijo() + voucher.ceSerie());
        //if(event.isSecuencial()) {
        //    document.setNumero("#");
        //} else {
            document.setNumero(""+Integer.parseInt(voucher.ceCorrelativo()));
        //}
        document.setFechaEmision(dtf.format(voucher.fecCreaCompPago()));
        document.setHoraEmision(dtmf.format(voucher.fecCreaCompPago()));
        document.setCodigoTipoOperacion("0101");
        document.setCodigoTipoDocumento(voucher.valTipoDocumentoSunat());
        document.setTipoMoneda(voucher.valTipoMonedaSunat());
        document.setFechaVencimiento(dtf.format(voucher.fecCreaCompPago()));
        document.setOrdenCompra("");
        document.setAlmacen("Almacen 1");

        return document;
    }

    private Document addDetails(Document document, PaymentVoucher voucher) {

        DatosEmisor datosEmisor = new DatosEmisor("0000");
        document.setDatosEmisor(datosEmisor);

        boolean enviarEmail = false;

        DatosReceptor datosReceptor = new DatosReceptor(voucher.codTipIdentRecepE(),
                voucher.valNumeroDocumento(),
                voucher.valNombreImpreso(),
                "PE",
                "",
                voucher.direcImprComp(),
                "",
                "");
        document.setClienteReceptor(datosReceptor);

        Totales totales = new Totales(0.00,
                voucher.totalGravE(),
                voucher.totalInafE(),
                voucher.totalExonE(),
                voucher.totalGratuE(),
                voucher.valTotalImpuestos(),
                voucher.valTotalImpuestos(),
                voucher.valValorVenta(),
                voucher.valTotalVenta());
        document.setTotales(totales);

        List<OrderDetail> details = orderDetailService.listOrderDetail(voucher.id());

        List<Item> lstItem = new ArrayList<>();
        Product product;
        for(OrderDetail detail : details) {
            product = productService.findByCodProd(detail.id().codGrupoCia(), detail.codProd());

            Item item = new Item();
            item.setCodigoInterno(detail.codProd());
            item.setDescripcion(product.descProd());
            item.setCodigoProductoSunat("-");
            item.setUnidadMedida(product.valUnidadMedidaSunat());
            item.setCantidad(Math.abs(detail.cantAtendida()));
            //if (voucher.valTipoDocumentoSunat().equals("07")) { // 20.03.2023 ERIOS Fix credit price
            //    double valorUnitario = detail.valVtaItemE() / Math.abs(detail.cantAtendida());
            //    item.setValorUnitario(round(valorUnitario, 5));
            //} else {
            item.setValorUnitario(Math.abs(detail.sinIgvBaseValPrecVta()));
            //}
            //if("31".equals(detail.getCodTipAfecIgvE())) {
            //    item.setCodigoTipoPrecio("02");
            //    item.setPrecioUnitario(detail.getValPrecVtaUnitE());
            //    item.setTipoAfectacionIgv("15");
            //    double totalItem = Math.abs(detail.getCantAtendida())*detail.getValPrecVtaUnitE();
            //    item.setTotalBaseIgv(totalItem);
            //    item.setPorcentajeIgv(detail.getValIgv());
            //    item.setTotalIgv(1.00);
            //    item.setTotalValorItem(totalItem);
            //} else {
            item.setCodigoTipoPrecio("01");
            item.setPrecioUnitario(detail.valPrecVtaUnitE());
            item.setTipoAfectacionIgv(detail.codTipAfecIgvE());
            item.setTotalBaseIgv(detail.valVtaItemE());
            item.setPorcentajeIgv(detail.valIgv());
            item.setTotalIgv(detail.valTotalIgvItemE());
            item.setTotalValorItem(detail.valVtaItemE());
            //}
            item.setTotalImpuestos(detail.getTotalImpuestos());

            item.setTotalItem(Math.abs(detail.valPrecTotal()));

            lstItem.add(item);
        }

        Item[] itemsToArray = new Item[lstItem.size()];
        Item[] items = lstItem.toArray(itemsToArray);
        document.setItems(items);

        Acciones acciones = new Acciones();
        acciones.setEnviarEmail(enviarEmail);
        document.setAcciones(acciones);

        return document;
    }

    public void updateSentVoucher(PaymentVoucher voucher, ResponseFact response) {
        String numeroDoc = voucher.cePrefijo() + voucher.ceSerie()+"-"+voucher.ceCorrelativo();
        //P: EN PROCESO, S: EMITIDO, N: NO EMITIDO, B: DADO DE BAJA, R: ENVIADO EN RESUMEN DIARIO, E: ERROR, Q: EN PROCESO BAJA
        if(response.success()){
            String ceEstadoEnvioSunat,ceDescripcionObservado="-";
            switch(voucher.valTipoDocumentoSunat()) {
                case "03": ceEstadoEnvioSunat="R"; break;
                case "07": ceEstadoEnvioSunat="B"; break;
                default: ceEstadoEnvioSunat="S"; break;
            }

            if(response.response().notes() != null &&
                    response.response().notes().length > 0) {

                ceDescripcionObservado = Arrays.toString(response.response().notes());
                if(ceDescripcionObservado.length()>480) ceDescripcionObservado = ceDescripcionObservado.substring(0,480);
            }

            voucher = voucher.updateEnvioSunat(
                    ceEstadoEnvioSunat,
                    ceDescripcionObservado,
                    response.data().externalId()
            );
            log.info("Documento {} enviado.",numeroDoc);
        } else {
            voucher = voucher.updateEnvioSunat(
                    "E",
                    response.message(),
                    "");
            log.error("Documento {} enviado con error.",numeroDoc);
        }

        paymentVoucherService.updateEnvioSunat(voucher);
    }
}
