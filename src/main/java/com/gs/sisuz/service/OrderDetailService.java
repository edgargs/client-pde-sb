package com.gs.sisuz.service;

import com.gs.sisuz.model.OrderDetail;
import com.gs.sisuz.model.PaymentVoucherId;
import com.gs.sisuz.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> listOrderDetail(PaymentVoucherId id) {
        return orderDetailRepository.listOrderDetail(id.codGrupoCia(), id.codLocal(), id.numPedVta());
    }
}
