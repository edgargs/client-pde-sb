package com.gs.sisuz.service;

import com.gs.sisuz.model.PaymentVoucher;
import com.gs.sisuz.repository.PaymentVoucherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentVoucherService {

    private final PaymentVoucherRepository paymentVoucherRepository;

    public PaymentVoucherService(PaymentVoucherRepository paymentVoucherRepository) {
        this.paymentVoucherRepository = paymentVoucherRepository;
    }


    public boolean updateEnvioSunat(PaymentVoucher voucher) {
        return paymentVoucherRepository.updateEnvioSunat(
                voucher.ceEstadoEnvioSunat(), voucher.ceDescripcionObservado(), voucher.externalId(),
                voucher.id().codGrupoCia(), voucher.id().codLocal(), voucher.id().numPedVta(), voucher.id().secCompPago());
    }

    public List<PaymentVoucher> listPendingVouchers(String codGrupoCia, String codLocal, String tipCompPago, String fechaInicioEnvio, String fechaFinEnvio) {
        return paymentVoucherRepository.listPendingVouchers(codGrupoCia, codLocal, tipCompPago, fechaInicioEnvio, fechaFinEnvio);
    }
}
