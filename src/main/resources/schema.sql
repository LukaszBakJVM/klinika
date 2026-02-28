-----------------------------------------
-- PACJENT
-----------------------------------------

CREATE TABLE PACJENT (
                         ID BIGINT NOT NULL,
                         IMIE VARCHAR(100) NOT NULL,
                         NAZWISKO VARCHAR(100) NOT NULL,
                         PESEL VARCHAR(11) NOT NULL,

                         CONSTRAINT PK_PACJENT PRIMARY KEY (ID),
                         CONSTRAINT UQ_PACJENT_PESEL UNIQUE (PESEL)
);

CREATE SEQUENCE GEN_PACJENT_ID;

SET TERM !! ;

CREATE TRIGGER TR_PACJENT_BI FOR PACJENT
ACTIVE BEFORE INSERT
AS
BEGIN
    NEW.ID = COALESCE(NEW.ID, NEXT VALUE FOR GEN_PACJENT_ID);
END!!

SET TERM ; !!


-----------------------------------------
-- WIZYTA
-----------------------------------------

CREATE TABLE WIZYTA (
                        ID BIGINT NOT NULL,
                        PACJENT_ID BIGINT NOT NULL,
                        DATA_WIZYTY DATE NOT NULL,
                        ROZPOZNANIE VARCHAR(255),
                        KWOTA DECIMAL(10,2) NOT NULL,

                        CONSTRAINT PK_WIZYTA PRIMARY KEY (ID),

                        CONSTRAINT FK_WIZYTA_PACJENT
                            FOREIGN KEY (PACJENT_ID)
                                REFERENCES PACJENT(ID)
                                ON DELETE CASCADE,

                        CONSTRAINT CHK_WIZYTA_KWOTA
                            CHECK (KWOTA >= 0)
);

CREATE SEQUENCE GEN_WIZYTA_ID;

SET TERM !! ;

CREATE TRIGGER TR_WIZYTA_BI FOR WIZYTA
ACTIVE BEFORE INSERT
AS
BEGIN
    NEW.ID = COALESCE(NEW.ID, NEXT VALUE FOR GEN_WIZYTA_ID);
END!!

SET TERM ; !!