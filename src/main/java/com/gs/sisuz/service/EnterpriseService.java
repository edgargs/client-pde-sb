package com.gs.sisuz.service;

import com.gs.sisuz.model.Enterprise;
import com.gs.sisuz.repository.EnterpriseRepository;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {

    private final EnterpriseRepository repository;

    public EnterpriseService(EnterpriseRepository repository) {
        this.repository = repository;
    }

    public Enterprise findByCodGrupoCiaAndCodCia(String codGrupoCia, String codCia) {
        return repository.findByCodGrupoCiaAndCodCia(codGrupoCia, codCia);
    }
}
