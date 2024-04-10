package com.gs.sisuz.service

import com.gs.sisuz.model.*
import com.gs.sisuz.multifacturalo.dto.Document
import com.gs.sisuz.multifacturalo.dto.ResponseFact
import spock.lang.Specification
import spock.lang.Shared

class DocumentServiceTest extends Specification {

    @Shared DocumentService documentService
    @Shared EnterpriseService enterpriseService
    @Shared PaymentVoucherService paymentVoucherService
    @Shared OrderDetailService orderDetailService
    @Shared ProductService productService
    @Shared MultifacturaloService multifacturaloService

    def setup() {
        enterpriseService = Mock(EnterpriseService)
        paymentVoucherService = Mock(PaymentVoucherService)
        orderDetailService = Mock(OrderDetailService)
        productService = Mock(ProductService)
        multifacturaloService = Mock(MultifacturaloService)
        documentService = new DocumentService(
                enterpriseService, paymentVoucherService, orderDetailService, productService, multifacturaloService)
    }

    def newPaymentVoucher() {
        return new PaymentVoucher(
                new PaymentVoucherId("001","001","0000000001","0000000001"), // replace with a default PaymentVoucherId
                "defaultTipCompPago",
                "defaultCePrefijo",
                "defaultCeSerie",
                "11",
                new java.sql.Date(System.currentTimeMillis()), // current date as default
                "defaultCodTipMoneda",
                "defaultCodTipIdentRecepE",
                "defaultNumDocImpr",
                "defaultNomImprComp",
                "defaultDirecImprComp",
                0.0, // default value for totalGravE
                0.0, // default value for totalInafE
                0.0, // default value for totalExonE
                0.0, // default value for totalGratuE
                0.0, // default value for valIgvCompPago
                0.0, // default value for valNetoCompPago
                0.0, // default value for valRedondeoCompPago
                "defaultCeEstadoEnvioSunat",
                "defaultCeDescripcionObservado",
                "defaultExternalId"
        )
    }

    def newOrderDetail() {
        return new OrderDetail(
                new OrderDetailId("001","001","0000000001",1), // replace with a default OrderDetailId
                "defaultCodProd",
                1,
                0.0,
                0.0,
                0.0,
                "defaultCodTipAfeIgv",
                0.0,
                0.0,
                0.0
        )
    }

    def newProduct() {
        return new Product(
                new ProductId("001","001"), // replace with a default ProductId
                "defaultDescProd",
                "defaultDescUnidPresent"
        )
    }

    def "test processDocumentsPending"() {
        given: "A list of payment vouchers and details"
        PaymentVoucher voucher1 = newPaymentVoucher()
        PaymentVoucher voucher2 = newPaymentVoucher()
        List<PaymentVoucher> vouchers = [voucher1, voucher2]
        List<OrderDetail> details = [newOrderDetail()]

        and:
        paymentVoucherService.listPendingVouchers(_, _, _, _, _) >> vouchers
        orderDetailService.listOrderDetail(_ as PaymentVoucherId) >> details
        productService.findByCodProd(_, _) >> newProduct()
        multifacturaloService.store(_ as Document) >> new ResponseFact(false, "defaultMessage")

        when: "processDocumentsPending is called"
        documentService.processDocumentsPending()

        then: "updateEnvioSunat is called twice"
        2 * paymentVoucherService.updateEnvioSunat(_)
    }
}