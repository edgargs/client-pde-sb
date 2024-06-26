create table NEG_EMPRESA(
    cod_grupo_cia varchar(3),
    cod_cia       varchar(3),
    url           varchar(150),
    token         varchar(150),

    PRIMARY KEY (COD_GRUPO_CIA, COD_CIA)
);

CREATE TABLE VTA_PEDIDO_VTA_CAB(
    COD_GRUPO_CIA CHAR(3),
    COD_LOCAL CHAR(3),
    NUM_PED_VTA CHAR(10),

    EST_PED_VTA CHAR(1),

    PRIMARY KEY (COD_GRUPO_CIA, COD_LOCAL, NUM_PED_VTA)
);

CREATE TABLE VTA_COMP_PAGO(
    COD_GRUPO_CIA CHAR(3),
    COD_LOCAL CHAR(3),
    NUM_PED_VTA CHAR(10),
    SEC_COMP_PAGO CHAR(10),

    TIP_COMP_PAGO  CHAR(2),
    CE_PREFIJO CHAR(1),
    CE_SERIE CHAR(3),
    CE_CORRELATIVO VARCHAR(10),

    FEC_CREA_COMP_PAGO DATE,
    COD_TIP_MONEDA CHAR(2),
    COD_TIP_IDENT_RECEP_E CHAR(1),
    NUM_DOC_IMPR VARCHAR(100),
    NOM_IMPR_COMP VARCHAR(100),
    DIREC_IMPR_COMP CHAR(100),

    TOTAL_GRAV_E NUMERIC(10,4),
    TOTAL_INAF_E NUMERIC(10,4),
    TOTAL_EXON_E NUMERIC(10,4),
    TOTAL_GRATU_E NUMERIC(10,4),
    VAL_IGV_COMP_PAGO NUMERIC(10,4),
    VAL_NETO_COMP_PAGO NUMERIC(10,4),
    VAL_REDONDEO_COMP_PAGO NUMERIC(10,4),

    CE_ESTADO_ENVIO_SUNAT CHAR(1),
    CE_DESCRIPCION_OBSERVADO VARCHAR(100),
    EXTERNAL_ID VARCHAR(100),

    IND_COMP_PAGO_E CHAR(1),
    NUM_COMP_PAGO_E VARCHAR(20),

    PRIMARY KEY (COD_GRUPO_CIA, COD_LOCAL, NUM_PED_VTA, SEC_COMP_PAGO)
);

CREATE TABLE VTA_PEDIDO_VTA_DET(
	COD_GRUPO_CIA CHAR(3),
    COD_LOCAL CHAR(3),
    NUM_PED_VTA CHAR(10),
    SEC_PED_VTA_DET NUMERIC,
    
    COD_PROD CHAR(8),
    CANT_ATENDIDA NUMERIC,
    SIN_IGV_BASE_VAL_PREC_VTA NUMERIC(10,4),
    VAL_VTA_ITEM_E NUMERIC(10,4),
    VAL_PREC_VTA_UNIT_E NUMERIC(10,4),
    COD_TIP_AFEC_IGV_E CHAR(2),
    VAL_IGV NUMERIC(10,4),
    VAL_TOTAL_IGV_ITEM_E NUMERIC(10,4),
    VAL_PREC_TOTAL NUMERIC(10,4),
    
    PRIMARY KEY (COD_GRUPO_CIA, COD_LOCAL, NUM_PED_VTA, SEC_PED_VTA_DET)
);

CREATE TABLE LGT_PROD(
	COD_GRUPO_CIA CHAR(3),
	COD_PROD CHAR(8),
	
	DESC_PROD VARCHAR(100),
	DESC_UNID_PRESENT VARCHAR(100),
	
	PRIMARY KEY (COD_GRUPO_CIA, COD_PROD)
);