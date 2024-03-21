package com.gs.sisuz.multifacturalo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatosReceptor {
    @JsonProperty("codigo_tipo_documento_identidad")
    private String tipoDocumentoIdentidad;
    @JsonProperty("numero_documento")
    private String numeroDocumento;
    @JsonProperty("apellidos_y_nombres_o_razon_social")
    private String apelliosNombresRazonSocial;
    @JsonProperty("codigo_pais")
    private String codigoPais;
    private String ubigeo;
    private String direccion;
    @JsonProperty("correo_electronico")
    private String correoElectronico;
    private String telefono;

    public DatosReceptor(String tipoDocumentoIdentidad, String numeroDocumento, String apelliosNombresRazonSocial, String codigoPais, String ubigeo, String direccion, String correoElectronico, String telefono) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
        this.numeroDocumento = numeroDocumento;
        this.apelliosNombresRazonSocial = apelliosNombresRazonSocial;
        this.codigoPais = codigoPais;
        this.ubigeo = ubigeo;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public String getTipoDocumentoIdentidad() {
        return tipoDocumentoIdentidad;
    }

    public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getApelliosNombresRazonSocial() {
        return apelliosNombresRazonSocial;
    }

    public void setApelliosNombresRazonSocial(String apelliosNombresRazonSocial) {
        this.apelliosNombresRazonSocial = apelliosNombresRazonSocial;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
