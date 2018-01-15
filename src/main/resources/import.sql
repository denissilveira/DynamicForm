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



CREATE TABLE dynamicgroup (
    id            NUMBER
        CONSTRAINT dynamicgroup_id NOT NULL,
    dinamicform   NUMBER NOT NULL,
    name          VARCHAR2(100),
    type          VARCHAR2(25),
    parent        NUMBER,
    show          CHAR(1),
    showname      CHAR(1)
)
LOGGING;

CREATE UNIQUE INDEX idx_dynamicgroup_id ON
    dynamicgroup ( id ASC );

CREATE INDEX idx_dynamicgroup_dinamicform ON
    dynamicgroup ( dinamicform ASC );
    
ALTER TABLE dynamicgroup ADD CONSTRAINT pk_group PRIMARY KEY ( id );

ALTER TABLE dynamicgroup
    ADD CONSTRAINT fk_group_form FOREIGN KEY ( dinamicform )
        REFERENCES dynamicform ( id )
    NOT DEFERRABLE;
    
ALTER TABLE dynamicgroup
    ADD CONSTRAINT fk_group_parent FOREIGN KEY ( parent )
        REFERENCES dynamicgroup ( id );

CREATE SEQUENCE seq_dynamicgroup_id START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dynamicgroup_id_trg BEFORE
    INSERT ON dynamicgroup
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := seq_dynamicgroup_id.nextval;
END;
/


CREATE TABLE dynamictype (
    id        NUMBER
        CONSTRAINT dynamictype_id NOT NULL,
    type      VARCHAR2(15)
        CONSTRAINT dynamictype_name NOT NULL,
    subtype   VARCHAR2(15)
)
LOGGING;


CREATE UNIQUE INDEX idx_dynamictype_id ON
    dynamictype ( id ASC );

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
    id             NUMBER
        CONSTRAINT dynamicfield_id NOT NULL,
    dynamicgroup   NUMBER NOT NULL,
    dynamictype    NUMBER NOT NULL,
    name           VARCHAR2(100),
    label          VARCHAR2(1024),
    value          VARCHAR2(1024),
    style          CLOB,
    show           CHAR(1),
    required       CHAR(1),
    showlabel      CHAR(1),
    action         VARCHAR2(50),
    actiontype     VARCHAR2(15)
)
LOGGING;


CREATE UNIQUE INDEX idx_dynamicfield_id ON
    dynamicfield ( id ASC );

CREATE INDEX idx_dynamicfield_dynamicgroup ON
    dynamicfield ( dynamicgroup ASC );

CREATE INDEX idx_dynamicfield_dynamictype ON
    dynamicfield ( dynamictype ASC );

ALTER TABLE dynamicfield ADD CONSTRAINT pk_field PRIMARY KEY ( id );

ALTER TABLE dynamicfield
    ADD CONSTRAINT fk_field_group FOREIGN KEY ( dynamicgroup )
        REFERENCES dynamicgroup ( id )
    NOT DEFERRABLE;

ALTER TABLE dynamicfield
    ADD CONSTRAINT fk_field_type FOREIGN KEY ( dynamictype )
        REFERENCES dynamictype ( id )
    NOT DEFERRABLE;

CREATE SEQUENCE seq_dynamicfield_id START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dynamicfield_id_trg BEFORE
    INSERT ON dynamicfield
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := seq_dynamicfield_id.nextval;
END;
/


CREATE TABLE dynamicoption (
    id             NUMBER
        CONSTRAINT dynamicoption_id NOT NULL,
    dynamicfield   NUMBER NOT NULL,
    doption       VARCHAR2(50),
    dvalue          VARCHAR2(50) NOT NULL
)
LOGGING;

CREATE UNIQUE INDEX idx_dynamicoption_id ON
    dynamicoption ( id ASC );

CREATE INDEX idx_dynamicoption_dynamicfield ON
    dynamicoption ( dynamicfield ASC );

ALTER TABLE dynamicoption ADD CONSTRAINT pk_option PRIMARY KEY ( id );

ALTER TABLE dynamicoption
    ADD CONSTRAINT fk_option_field FOREIGN KEY ( dynamicfield )
        REFERENCES dynamicfield ( id )
    NOT DEFERRABLE;

CREATE SEQUENCE seq_dynamicoption_id START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dynamicoption_id_trg BEFORE
    INSERT ON dynamicoption
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := seq_dynamicoption_id.nextval;
END;
/

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
insert into DYNAMICTYPE values (null, 'img', '');
insert into DYNAMICTYPE values (null, 'button', 'button');
insert into DYNAMICTYPE values (null, 'button', 'submit');
insert into DYNAMICTYPE values (null, 'button', 'reset');


-- Hello World Form
insert into DYNAMICFORM values (null, 'POC FORM', 1, 'POST', '1', '/save');
insert into DYNAMICGROUP values (null, 1, 'Group Simple DIV', 'div', null, '1', '1');
insert into DYNAMICGROUP values (null, 1, 'Group Simple Fieldset', 'fieldset', null, '1', '1');

insert into DYNAMICFIELD values (null, 1, 1, 'name', 'Name:', 'Value name', null, '1', '1', '1', null, null);
insert into DYNAMICFIELD values (null, 1, 2, 'password', 'Password:', '12345678', null, '1', '1', '1', null, null);
insert into DYNAMICFIELD values (null, 1, 12, 'sexo', 'Sexo:', null, null, '1', '1', '1', null, null);
insert into DYNAMICFIELD values (null, 1, 11, 'pais', 'País:', null, null, '1', '1', '1', null, null);
insert into DYNAMICOPTION values (null, 4, ' Brasil', 'br');
insert into DYNAMICOPTION values (null, 4, 'Portugal', 'pt');
insert into DYNAMICFIELD values (null, 1, 11, 'estado', 'Estado:', null, null, '1', '1', '1', null, null);
insert into DYNAMICOPTION values (null, 5, ' São Paulo', 'sp');
insert into DYNAMICOPTION values (null, 5, 'Rio de Janeiro', 'rj');
insert into DYNAMICOPTION values (null, 5, ' Lisboa', 'lsb');
insert into DYNAMICOPTION values (null, 5, 'Porto', 'prt');
insert into DYNAMICFIELD values (null, 2, 1, 'inputevento', 'Teste Evento:', null, null, '1', '1', '1', null, null);
insert into DYNAMICFIELD values (null, 2, 1, 'oculto', 'Campo Oculto:', '12345678', null, '0', '0', '0', null, null);

