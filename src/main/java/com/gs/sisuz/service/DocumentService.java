package com.gs.sisuz.service;

import com.gs.sisuz.model.OrderDetail;
import com.gs.sisuz.model.PaymentVoucher;
import com.gs.sisuz.model.Product;
import com.gs.sisuz.multifacturalo.DocumentsHttpClient;
import com.gs.sisuz.multifacturalo.dto.*;
import com.gs.sisuz.repository.EnterpriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class DocumentService {

    private final Logger log = LoggerFactory.getLogger(DocumentService.class);

    private final EnterpriseRepository enterpriseRepository;

    private DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat dtmf = new SimpleDateFormat("HH:mm:ss");

    public DocumentService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    void processDocumentsPending() throws MalformedURLException, URISyntaxException {
        log.info("Documents Pending");
        sendDocuments();
    }

    private void sendDocuments() throws MalformedURLException, URISyntaxException {

        String codNegocio = "001"; //event.getCodNegocio();
        DocumentsHttpClient client = DocumentsHttpClient.intanceClientConfig(enterpriseRepository, codNegocio,"/api/documents");

        PaymentVoucher voucher = new PaymentVoucher(
                "02","F","001","13",new Date(2024-1900,3-1,22),
                "01",
                "6","10418540341","Edgar Rios","Chaclacayo",
                100.0, 0.0,0.0,0.0,18.0, 118.0,0.0);

        //2.1 Make payload of document
        Document document = makePayload(voucher);

        var response = client.store(document);
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

        List<Item> lstItem = new ArrayList<>();

        OrderDetail detail = new OrderDetail("P0121",2,50.0,100,59,
                "10",18.0,18.0,118.0);
        Product product = new Product("Inca Kola 250 ml","UNIDAD");

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

        Item[] itemsToArray = new Item[lstItem.size()];
        Item[] items = lstItem.toArray(itemsToArray);
        document.setItems(items);

        Acciones acciones = new Acciones();
        acciones.setEnviarEmail(enviarEmail);
        document.setAcciones(acciones);

        return document;
    }
}
