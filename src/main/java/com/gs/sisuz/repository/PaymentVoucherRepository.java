package com.gs.sisuz.repository;

import com.gs.sisuz.model.PaymentVoucher;
import com.gs.sisuz.model.PaymentVoucherId;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PaymentVoucherRepository extends ListCrudRepository<PaymentVoucher, PaymentVoucherId> {

    @Modifying
    @Query("""
            UPDATE VTA_COMP_PAGO
            SET 
                CE_ESTADO_ENVIO_SUNAT =     :ceEstadoEnvioSunat ,
                CE_DESCRIPCION_OBSERVADO =  :ceDescripcionObservado ,
                EXTERNAL_ID =               :externalId
            WHERE 
                COD_GRUPO_CIA =     :codGrupoCia
                AND COD_LOCAL =     :codLocal
                AND NUM_PED_VTA =   :numPedVta
                AND SEC_COMP_PAGO = :secCompPago
            """)
    boolean updateEnvioSunat(String ceEstadoEnvioSunat, String ceDescripcionObservado, String externalId,
                             String codGrupoCia, String codLocal, String numPedVta, String secCompPago);

    @Query("""
            SELECT PAG.* 
            FROM VTA_PEDIDO_VTA_CAB CAB 
                     INNER JOIN VTA_COMP_PAGO PAG  
                     ON CAB.COD_GRUPO_CIA = PAG.COD_GRUPO_CIA AND CAB.COD_LOCAL = PAG.COD_LOCAL AND CAB.NUM_PED_VTA = PAG.NUM_PED_VTA 
            WHERE PAG.COD_GRUPO_CIA = :codGrupoCia 
                     AND   PAG.COD_LOCAL = :codLocal 
                     AND   PAG.IND_COMP_PAGO_E = 'S' 
                     AND   PAG.TIP_COMP_PAGO = :tipCompPago 
                     AND   PAG.CE_ESTADO_ENVIO_SUNAT = 'N' 
                     AND   PAG.CE_ESTADO_ENVIO_SUNAT != 'X' 
                     AND   CAB.EST_PED_VTA = 'C' 
                     AND   TRUNC(PAG.FEC_CREA_COMP_PAGO) BETWEEN :fechaInicioEnvio AND :fechaFinEnvio 
            ORDER BY PAG.NUM_COMP_PAGO_E ASC
            """)
    List<PaymentVoucher> listPendingVouchers(String codGrupoCia, String codLocal, String tipCompPago, String fechaInicioEnvio, String fechaFinEnvio);
}
