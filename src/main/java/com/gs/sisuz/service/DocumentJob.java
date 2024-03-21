package com.gs.sisuz.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Component
public class DocumentJob {

    private final DocumentService documentService;

    public DocumentJob(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Scheduled(fixedDelay = 60_000, initialDelay = 15_000)
    void executePending() throws MalformedURLException, URISyntaxException {
        documentService.processDocumentsPending();
    }
}
