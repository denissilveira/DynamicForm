CREATE TABLE dynamicform (
    id        NUMBER
        CONSTRAINT dynamicform_id NOT NULL,
    name      VARCHAR2(100)
        CONSTRAINT dynamicform_name NOT NULL,
    version   NUMBER NOT NULL,
    method    VARCHAR2(10),
    show      CHAR(1),
    action    VARCHAR2(50)
)
LOGGING;

CREATE UNIQUE INDEX idx_dynamicform_id ON
    dynamicform ( id ASC );

ALTER TABLE dynamicform ADD CONSTRAINT pk_form PRIMARY KEY ( id );

CREATE SEQUENCE seq_dynamicform_id START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dynamicform_id_trg BEFORE
    INSERT ON dynamicform
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := seq_dynamicform_id.nextval;
END;
/

CREATE TABLE dynamicelement (
    id            NUMBER
        CONSTRAINT nnc_element_id NOT NULL,
    dynamicform   NUMBER NOT NULL,
    position      NUMBER NOT NULL,
    type          VARCHAR2(5) NOT NULL,    
    parent        NUMBER
);

ALTER TABLE dynamicelement ADD CONSTRAINT pk_dynamictype PRIMARY KEY ( id );

ALTER TABLE dynamicelement
    ADD CONSTRAINT fk_element_form FOREIGN KEY ( dynamicform )
        REFERENCES dynamicform ( id );

ALTER TABLE dynamicelement
    ADD CONSTRAINT fk_element_parent FOREIGN KEY ( parent )
        REFERENCES dynamicelement ( id );

CREATE SEQUENCE seq_dynamicelement_id START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dynamicelement_id_trg BEFORE
    INSERT ON dynamicelement
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := seq_dynamicelement_id.nextval;
END;
/

CREATE TABLE dynamicgroup (
    id         NUMBER
        CONSTRAINT nnc_group_id NOT NULL,
    name       VARCHAR2(100),
    type       VARCHAR2(25),
    show       CHAR(1),
    showname   CHAR(1)
);

ALTER TABLE dynamicgroup ADD CONSTRAINT pk_group PRIMARY KEY ( id );

ALTER TABLE dynamicgroup
    ADD CONSTRAINT fk_group_element FOREIGN KEY ( id )
        REFERENCES dynamicelement ( id );
   
        
CREATE TABLE dynamictype (
    id        NUMBER
        CONSTRAINT nnc_tyoe_id NOT NULL,
    type      VARCHAR2(15) NOT NULL,
    subtype   VARCHAR2(15)
);

ALTER TABLE dynamictype ADD CONSTRAINT pk_type PRIMARY KEY ( id );

CREATE SEQUENCE seq_dynamictype_id START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dynamictype_id_trg BEFORE
    INSERT ON dynamictype
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := seq_dynamictype_id.nextval;
END;
/


CREATE TABLE dynamicfield (
    id            NUMBER
        CONSTRAINT nnc_field_id NOT NULL,
    dynamictype   NUMBER NOT NULL,
    name          VARCHAR2(100) NOT NULL,
    label         VARCHAR2(1024) NOT NULL,
    value         VARCHAR2(1024),
    style         CLOB,
    show          CHAR(1),
    required      CHAR(1),
    showlabel     CHAR(1),
    action        VARCHAR2(50),
    actiontype    VARCHAR2(15)
);

ALTER TABLE dynamicfield ADD CONSTRAINT pk_field PRIMARY KEY ( id );

ALTER TABLE dynamicfield
    ADD CONSTRAINT fk_field_element FOREIGN KEY ( id )
        REFERENCES dynamicelement ( id );

ALTER TABLE dynamicfield
    ADD CONSTRAINT fk_field_type FOREIGN KEY ( dynamictype )
        REFERENCES dynamictype ( id );


CREATE TABLE dynamicoption (
    id             NUMBER
        CONSTRAINT nnc_option_id NOT NULL,
    dynamicfield   NUMBER NOT NULL,
    doption       VARCHAR2(50) NOT NULL,
    dvalue          VARCHAR2(50) NOT NULL
);


ALTER TABLE dynamicoption ADD CONSTRAINT pk_option PRIMARY KEY ( id );

ALTER TABLE dynamicoption
    ADD CONSTRAINT fk_option_field FOREIGN KEY ( dynamicfield )
        REFERENCES dynamicfield ( id );

CREATE SEQUENCE seq_dynamicoption_id START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dynamicoption_id_trg BEFORE
    INSERT ON dynamicoption
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := seq_dynamicoption_id.nextval;
END;
/


-- GRANTS
GRANT SELECT ON sedr.seq_dynamicform_id TO rl_sedr_rw;
GRANT SELECT,INSERT,UPDATE  ON sedr.dynamicform TO rl_sedr_rw;

GRANT SELECT ON sedr.seq_dynamicelement_id TO rl_sedr_rw;
GRANT SELECT,INSERT,UPDATE  ON sedr.dynamicelement TO rl_sedr_rw;

GRANT SELECT,INSERT,UPDATE  ON sedr.dynamicgroup TO rl_sedr_rw;

GRANT SELECT,INSERT,UPDATE  ON sedr.dynamicfield TO rl_sedr_rw;

GRANT SELECT ON sedr.seq_dynamictype_id TO rl_sedr_rw;
GRANT SELECT,INSERT,UPDATE  ON sedr.dynamictype TO rl_sedr_rw;

GRANT SELECT ON sedr.seq_dynamicoption_id TO rl_sedr_rw;
GRANT SELECT,INSERT,UPDATE  ON sedr.dynamicoption TO rl_sedr_rw;


-- SYNONYMS
CREATE OR REPLACE SYNONYM sedr_rw.seq_dynamicform_id FOR sedr.seq_dynamicform_id;
CREATE OR REPLACE SYNONYM sedr_rw.dynamicform FOR sedr.dynamicform;

CREATE OR REPLACE SYNONYM sedr_rw.seq_dynamicelement_id FOR sedr.seq_dynamicelement_id;
CREATE OR REPLACE SYNONYM sedr_rw.dynamicelement FOR sedr.dynamicelement;

CREATE OR REPLACE SYNONYM sedr_rw.dynamicfield FOR sedr.dynamicfield;
CREATE OR REPLACE SYNONYM sedr_rw.dynamicgroup FOR sedr.dynamicgroup;

CREATE OR REPLACE SYNONYM sedr_rw.seq_dynamictype_id FOR sedr.seq_dynamictype_id;
CREATE OR REPLACE SYNONYM sedr_rw.dynamictype FOR sedr.dynamictype;

CREATE OR REPLACE SYNONYM sedr_rw.seq_dynamicoption_id FOR sedr.seq_dynamicoption_id;
CREATE OR REPLACE SYNONYM sedr_rw.dynamicoption FOR sedr.dynamicoption;


-- TYPES 
insert into DYNAMICTYPE values (null, 'input', 'text');
insert into DYNAMICTYPE values (null, 'input', 'password');
insert into DYNAMICTYPE values (null, 'input', 'checkbox');
insert into DYNAMICTYPE values (null, 'input', 'button');
insert into DYNAMICTYPE values (null, 'input', 'email ');
insert into DYNAMICTYPE values (null, 'input', 'file');
insert into DYNAMICTYPE values (null, 'input', 'hidden');
insert into DYNAMICTYPE values (null, 'input', 'number ');
insert into DYNAMICTYPE values (null, 'input', 'date ');
insert into DYNAMICTYPE values (null, 'input', 'image');
insert into DYNAMICTYPE values (null, 'select', '');
insert into DYNAMICTYPE values (null, 'radio', '');
insert into DYNAMICTYPE values (null, 'checkbok', '');
insert into DYNAMICTYPE values (null, 'img', '');
insert into DYNAMICTYPE values (null, 'button', 'button');
insert into DYNAMICTYPE values (null, 'button', 'submit');
insert into DYNAMICTYPE values (null, 'button', 'reset');


-- Exemple Form CCP2018_ANEXO2
insert into DYNAMICFORM values (1, 'CCP2018_ANEXO2', 1, 'POST', '1', '/save');

-- ENTIDADE_ADJUDICANTE
insert into DYNAMICELEMENT values (1, 1, 1, 'GROUP', null); 
insert into DYNAMICGROUP values (1, 'ENTIDADE_ADJUDICANTE', 'div', '1', '1');

-- CONTRATO
insert into DYNAMICELEMENT values (2, 1, 2, 'GROUP', null); 
insert into DYNAMICGROUP values (2, 'CONTRATO', 'div', '1', '1');


-- FIELDS AND GROUPS ENTIDADE_ADJUDICANTE
insert into DYNAMICELEMENT values (3, 1, 1, 'GROUP', (select id from DYNAMICGROUP where name = 'ENTIDADE_ADJUDICANTE')); 
insert into DYNAMICGROUP values (3, 'PRINCIPAL', 'div', '1', '1');

insert into DYNAMICELEMENT values (4, 1, 1, 'FIELD', (select id from DYNAMICGROUP where name = 'ENTIDADE_ADJUDICANTE')); 
insert into DYNAMICFIELD values (4, (SELECT ID FROM DYNAMICTYPE WHERE subtype = 'text'), 'ENDERECO', 'ENDERECO:', '', null, '1', '1', '1', null, null);

insert into DYNAMICELEMENT values (5, 1, 2, 'FIELD', (select id from DYNAMICGROUP where name = 'PRINCIPAL')); 
insert into DYNAMICFIELD values (5, (SELECT ID FROM DYNAMICTYPE WHERE subtype = 'text'), 'NIF', 'NIF:', '', null, '1', '1', '1', null, null);

insert into DYNAMICELEMENT values (6, 1, 3, 'FIELD', (select id from DYNAMICGROUP where name = 'ENTIDADE_ADJUDICANTE')); 
insert into DYNAMICFIELD values (6, (SELECT ID FROM DYNAMICTYPE WHERE subtype = 'text'), 'ENDERECO', 'ENDERECO:', '', null, '1', '1', '1', null, null);

insert into DYNAMICELEMENT values (7, 1, 4, 'FIELD', (select id from DYNAMICGROUP where name = 'ENTIDADE_ADJUDICANTE')); 
insert into DYNAMICFIELD values (7, (SELECT ID FROM DYNAMICTYPE WHERE subtype = 'text'), 'COD_POSTAL', 'COD_POSTAL:', '', null, '1', '1', '1', null, null);

insert into DYNAMICELEMENT values (8, 1, 5, 'FIELD', (select id from DYNAMICGROUP where name = 'ENTIDADE_ADJUDICANTE')); 
insert into DYNAMICFIELD values (8, (SELECT ID FROM DYNAMICTYPE WHERE subtype = 'text'), 'LOCALIDADE', 'LOCALIDADE:', '', null, '1', '1', '1', null, null);


insert into DYNAMICELEMENT values (9, 1, 6, 'GROUP', (select id from DYNAMICGROUP where name = 'ENTIDADE_ADJUDICANTE')); 
insert into DYNAMICGROUP values (9, 'LOCAL_CODIGOS', 'div', '1', '1');

insert into DYNAMICELEMENT values (10, 1, 1, 'FIELD', (select id from DYNAMICGROUP where name = 'LOCAL_CODIGOS')); 
insert into DYNAMICFIELD values (10, (SELECT ID FROM DYNAMICTYPE WHERE subtype = 'text'), 'CODIGO', 'CODIGO:', '', null, '1', '1', '1', null, null);


insert into DYNAMICELEMENT values (11, 1, 7, 'FIELD', (select id from DYNAMICGROUP where name = 'ENTIDADE_ADJUDICANTE')); 
insert into DYNAMICFIELD values (11, (SELECT ID FROM DYNAMICTYPE WHERE subtype = 'text'), 'EMAIL', 'EMAIL:', '', null, '1', '1', '1', null, null);

