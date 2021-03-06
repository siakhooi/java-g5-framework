-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Tcd History
--

DROP SEQUENCE ISTCDHSEQ IF EXISTS;

CREATE SEQUENCE ISTCDHSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

DROP TABLE ISTCDH IF EXISTS;

CREATE CACHED TABLE ISTCDH(
        Whs     CHAR(10) NOT NULL,
        HisSeq	INT NOT NULL,
	HisDte	DATETIME,
	HisAct	CHAR(1),
	HisRmk	VARCHAR(200),
-- COPY FROM ISTCD START
        Tcd     CHAR(10) NOT NULL,
        Nme     VARCHAR(30) NOT NULL,
        Typ     CHAR(1) NOT NULL,
        Remark  VARCHAR(200),
-- COPY FROM ISTCD END
	PRIMARY KEY(Whs, HisSeq));
	

	