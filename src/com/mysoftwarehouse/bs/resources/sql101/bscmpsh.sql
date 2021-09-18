-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Cmps History
--

DROP SEQUENCE BSCMPSHSEQ IF EXISTS;

CREATE SEQUENCE BSCMPSHSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

DROP TABLE BSCMPSH IF EXISTS;

CREATE CACHED TABLE BSCMPSH(
	Cmp	CHAR(10),
	HisSeq	INT NOT NULL,
	HisDte	DATETIME,
	HisAct	CHAR(1),
	HisRmk	VARCHAR(200),
-- COPY FROM BSCMPS START
	QttSign1 VARCHAR(60),
	QttSign2 VARCHAR(60),
	InvSign1 VARCHAR(60),
	InvSign2 VARCHAR(60),
	RcpSign1 VARCHAR(60),
	RcpSign2 VARCHAR(60),
	PorSign1 VARCHAR(60),
	PorSign2 VARCHAR(60),
	PivSign1 VARCHAR(60),
	PivSign2 VARCHAR(60),
-- COPY FROM BSCMPS END
	PRIMARY KEY(Cmp, HisSeq));

INSERT INTO BSCMPSH (Cmp, HisSeq, HisDte, HisAct, HisRmk,
QttSign1, QttSign2, InvSign1, InvSign2, RcpSign1, RcpSign2, 
PorSign1, PorSign2, PivSign1, PivSign2) 
SELECT Cmp, NEXT VALUE FOR BSCMPSHSEQ, CURRENT_TIMESTAMP, 'S', '',
QttSign1, QttSign2, InvSign1, InvSign2, RcpSign1, RcpSign2, 
PorSign1, PorSign2, PivSign1, PivSign2 
FROM BSCMPS;
