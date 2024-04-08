package com.gs.sisuz.repository;

import com.gs.sisuz.model.OrderDetail;
import com.gs.sisuz.model.OrderDetailId;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrderDetailRepository extends ListCrudRepository<OrderDetail, OrderDetailId> {
    @Query("""
            SELECT b.* FROM VTA_PEDIDO_VTA_DET b 
            WHERE b.cod_grupo_cia = :codGrupoCia AND b.cod_local = :codLocal AND b.num_ped_vta = :numPedVta
            ORDER BY b.sec_ped_vta_det
            """)
    List<OrderDetail> listOrderDetail(String codGrupoCia, String codLocal, String numPedVta);

}
