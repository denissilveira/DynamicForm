CREATE TABLE dynamicform (
    id        NUMBER
        CONSTRAINT dynamicform_id NOT NULL,
    name      VARCHAR2(100)
        CONSTRAINT dynamicform_name NOT NULL,
    version   NUMBER NOT NULL
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
    name          VARCHAR2(100)
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
    fname           VARCHAR2(100),
    flabel          VARCHAR2(1024) NOT NULL,
    fvalue          VARCHAR2(1024),
    fstyle          VARCHAR2(1024),
    fshow           CHAR(1),
    frequired       CHAR(1)
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

-- Hello World Form
insert into DYNAMICFORM values (null, 'Hello World', 1);
insert into DYNAMICGROUP values (null, 1, 'Hello World Group');
insert into DYNAMICFIELD values (1, 1, 1, 'Hello World', 'Hello World', 'Hello World', null, '1', '1');

