package com.gs.sisuz.multifacturalo;

import com.gs.sisuz.model.CompanyId;
import com.gs.sisuz.model.Enterprise;
import com.gs.sisuz.multifacturalo.dto.Document;
import com.gs.sisuz.multifacturalo.dto.ResponseFact;
import com.gs.sisuz.repository.EnterpriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class DocumentsHttpClient {

    private final Logger log = LoggerFactory.getLogger(DocumentsHttpClient.class);

    private URI uri;
    private ClientConfiguration configuration;

    public static DocumentsHttpClient intanceClientConfig(EnterpriseRepository enterpriseRepository, String codNegocio, String path) throws MalformedURLException, URISyntaxException {

        //List<Enterprise> enterprises = enterpriseRepository.findAll();
        //Enterprise enterprise = enterprises.getFirst();
        var companyId = new CompanyId("001", codNegocio);
        Enterprise enterprise = enterpriseRepository.findByCodGrupoCiaAndCodCia(companyId.codGrupoCia(), companyId.codCia());

        ClientConfiguration configuration = new ClientConfiguration(
                enterprise.url(),
                enterprise.token(),
                path
        );
        //log.debug("Configuration: {}",configuration);

        DocumentsHttpClient client = new DocumentsHttpClient();
        client.setConfiguration(configuration);

        return client;
    }

    void setConfiguration(ClientConfiguration configuration) throws URISyntaxException, MalformedURLException {

        this.uri = new URI(configuration.url() + configuration.path()).toURL().toURI();
        this.configuration = configuration;
    }

    public ResponseFact store(Document document) {
        log.debug("Document: {}",document);
        /*
        HttpRequest<?> req = HttpRequest.POST(uri,document).contentType(MediaType.APPLICATION_JSON)
            .bearerAuth(configuration.getToken());
         return postClient(req);
         */
        RestClient restClient = RestClient.create();
        ResponseFact message;

        try {
            message = restClient.post()
                    .uri(uri)
                    .header("Authorization", String.format("Bearer %s", configuration.token()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(document)
                    .retrieve()
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        StringBuilder json = new StringBuilder();
                        try (InputStream input = response.getBody()) {
                            InputStreamReader isr = new InputStreamReader(input);
                            BufferedReader reader = new BufferedReader(isr);

                            int c;
                            while ((c = reader.read()) != -1) {
                                json.append((char) c);
                            }
                        }
                        throw new RuntimeException(json.toString());
                    })
                    .body(ResponseFact.class);

        } catch (Exception exception){
            log.error("HttpClient error",exception);
            message = new ResponseFact(
            false,
            exception.getMessage());
        }
        log.info("Response Fact: {}",message);
        return message;
    }
}
