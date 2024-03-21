package com.gs.sisuz.service;

import com.gs.sisuz.model.PaymentVoucher;
import com.gs.sisuz.multifacturalo.DocumentsHttpClient;
import com.gs.sisuz.multifacturalo.dto.Document;
import com.gs.sisuz.multifacturalo.dto.Totales;
import com.gs.sisuz.repository.EnterpriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Service
public class DocumentService {

    private final Logger log = LoggerFactory.getLogger(DocumentService.class);

    private final EnterpriseRepository enterpriseRepository;

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

        Document document = new Document(); //makePayload(voucher, event);

        PaymentVoucher voucher = new PaymentVoucher(100.0, 0.0,0.0,0.0,18.0, 100.0,0.0);

        document.setSerie("F001");
        document.setNumero("#");

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

        var response = client.store(document);
    }
}
