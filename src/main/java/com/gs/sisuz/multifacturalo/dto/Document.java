package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Document {
    @JsonProperty("serie_documento")
    private String serie;
    @JsonProperty("numero_documento")
    private String numero;
    @JsonProperty("fecha_de_emision")
    private String fechaEmision;
    @JsonProperty("hora_de_emision")
    private String horaEmision;
    @JsonProperty("codigo_tipo_operacion")
    private String codigoTipoOperacion;
    @JsonProperty("codigo_tipo_documento")
    private String codigoTipoDocumento;
    @JsonProperty("codigo_tipo_nota")
    private String codigoTipoNota;
    @JsonProperty("motivo_o_sustento_de_nota")
    private String motivoNota;
    @JsonProperty("codigo_tipo_moneda")
    private String tipoMoneda;
    @JsonProperty("fecha_de_vencimiento")
    private String fechaVencimiento;
    @JsonProperty("numero_orden_de_compra")
    private String ordenCompra;
    @JsonProperty("nombre_almacen")
    private String almacen;
    @JsonProperty("documento_afectado")
    private DocumentoAfectado documentoAfectado;
    @JsonProperty("datos_del_emisor")
    private DatosEmisor datosEmisor;
    @JsonProperty("datos_del_cliente_o_receptor")
    private DatosReceptor clienteReceptor;
    private Totales totales;
    private Item[] items;
    private Acciones acciones = new Acciones();

    @JsonProperty("condicion_de_pago")
    private String condicionDePago;

    private Cuota[] cuotas;

    private Guia[] guias;

    private Retencion retencion;

    // Getters and Setters

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(String horaEmision) {
        this.horaEmision = horaEmision;
    }

    public String getCodigoTipoOperacion() {
        return codigoTipoOperacion;
    }

    public void setCodigoTipoOperacion(String codigoTipoOperacion) {
        this.codigoTipoOperacion = codigoTipoOperacion;
    }

    public String getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(String codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public DatosEmisor getDatosEmisor() {
        return datosEmisor;
    }

    public void setDatosEmisor(DatosEmisor datosEmisor) {
        this.datosEmisor = datosEmisor;
    }

    public DatosReceptor getClienteReceptor() {
        return clienteReceptor;
    }

    public void setClienteReceptor(DatosReceptor clienteReceptor) {
        this.clienteReceptor = clienteReceptor;
    }

    public Totales getTotales() {
        return totales;
    }

    public void setTotales(Totales totales) {
        this.totales = totales;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public String getCodigoTipoNota() {
        return codigoTipoNota;
    }

    public void setCodigoTipoNota(String codigoTipoNota) {
        this.codigoTipoNota = codigoTipoNota;
    }

    public String getMotivoNota() {
        return motivoNota;
    }

    public void setMotivoNota(String motivoNota) {
        this.motivoNota = motivoNota;
    }

    public DocumentoAfectado getDocumentoAfectado() {
        return documentoAfectado;
    }

    public void setDocumentoAfectado(DocumentoAfectado documentoAfectado) {
        this.documentoAfectado = documentoAfectado;
    }

    public Acciones getAcciones() {
        return acciones;
    }

    public void setAcciones(Acciones acciones) {
        this.acciones = acciones;
    }

    public String getCondicionDePago() {
        return condicionDePago;
    }

    public void setCondicionDePago(String condicionDePago) {
        this.condicionDePago = condicionDePago;
    }

    public Cuota[] getCuotas() {
        return cuotas;
    }

    public void setCuotas(Cuota[] cuotas) {
        this.cuotas = cuotas;
    }

    public Guia[] getGuias() {
        return guias;
    }

    public void setGuias(Guia[] guias) {
        this.guias = guias;
    }

    public Retencion getRetencion() {
        return retencion;
    }

    public void setRetencion(Retencion retencion) {
        this.retencion = retencion;
    }

    @Override
    public String toString() {
        String result="";
        try {
            result = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
