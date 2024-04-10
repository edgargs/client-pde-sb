package com.gs.sisuz.service;

import com.gs.sisuz.model.Enterprise;
import com.gs.sisuz.multifacturalo.DocumentsRestClient;
import com.gs.sisuz.multifacturalo.dto.Document;
import com.gs.sisuz.multifacturalo.dto.ResponseFact;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Service
public class MultifacturaloService {

    private DocumentsRestClient client;

    public void configClient(Enterprise enterprise, String path) throws MalformedURLException, URISyntaxException {
         client = DocumentsRestClient.instanceClientConfig(enterprise,path);
    }

    public ResponseFact store(Document document) {
        return client.store(document);
    }
}
