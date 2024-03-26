package com.gs.sisuz.multifacturalo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.sisuz.model.Enterprise;
import com.gs.sisuz.multifacturalo.dto.Document;
import com.gs.sisuz.multifacturalo.dto.ResponseFact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class DocumentsRestClient {

    private final Logger log = LoggerFactory.getLogger(DocumentsRestClient.class);

    private final ObjectMapper mapper;

    private final ClientConfiguration configuration;

    private final RestClient restClient;

    public DocumentsRestClient(ClientConfiguration configuration) {

        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        this.restClient = RestClient.builder()
                .baseUrl(configuration.url())
                .defaultHeader("Authorization", String.format("Bearer %s", configuration.token()))
                .build();
        this.configuration = configuration;
    }

    public static DocumentsRestClient instanceClientConfig(Enterprise enterprise, String path) throws MalformedURLException, URISyntaxException {

        ClientConfiguration configuration = new ClientConfiguration(
                enterprise.url(),
                enterprise.token(),
                path
        );
        //log.debug("Configuration: {}",configuration);

        return new DocumentsRestClient(configuration);
    }

    public ResponseFact store(Document document) {
        log.debug("Document: {}",document);

        ResponseFact message;

        try {
            message = restClient.post()
                    .uri(configuration.path())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(document)
                    .exchange( (request, response) -> {

                        ResponseFact apiResponse = null;
                        if (response.getStatusCode().is4xxClientError()
                                || response.getStatusCode().is5xxServerError()) {
                            apiResponse = mapper.readValue(response.getBody(), ResponseFact.class);
                        } else {
                            apiResponse = mapper.readValue(response.getBody(), ResponseFact.class);
                        }
                        return apiResponse;
                    } );
                    /*.retrieve()
                    .body(ResponseFact.class);*/

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
