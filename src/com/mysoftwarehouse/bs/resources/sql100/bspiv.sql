-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- purchase invoice tables
--

-- Mst: BSPIVY - Purchase Invoice

DROP TABLE BSPIV IF EXISTS;

CREATE CACHED TABLE BSPIV(
	Cmp		CHAR(10),
	PivNo		CHAR(20),
	Sup		CHAR(10),
	PivDte		DATETIME,
	RefNum     	VARCHAR(20),
	PayTyp		CHAR(1),
        PayRefNum       VARCHAR(20),
        PayInfo         VARCHAR(60),
	Remark		VARCHAR(200),
	Status 		CHAR(1),
        TtlGrsAmt       DECIMAL,
        TtlAmt          DECIMAL,
	PRIMARY KEY(Cmp, PivNo));

DROP TABLE BSPIVD IF EXISTS;

CREATE CACHED TABLE BSPIVD(
	Cmp	CHAR(10),
	PivNo	CHAR(20),
	Itm	CHAR(10),
	Price	DECIMAL,
	Qty	DECIMAL,
	TtlAmt	DECIMAL,
	PRIMARY KEY(Cmp, PivNo, Itm));

DROP TABLE BSPIVA IF EXISTS;

CREATE CACHED TABLE BSPIVA(
	Cmp	CHAR(10),
	PivNo	CHAR(20),
	Add1	VARCHAR(60),
	Add2	VARCHAR(60),
	City	VARCHAR(30),
	ZipCde	CHAR(5),
	Stte	VARCHAR(30),
	Ctry	VARCHAR(30),
	Tel	VARCHAR(15),
	Fax	VARCHAR(15),
	Remark	VARCHAR(200),
	PRIMARY KEY(Cmp, PivNo));

DROP TABLE BSPIVC IF EXISTS;

CREATE CACHED TABLE BSPIVC(
	Cmp	CHAR(10),
	PivNo	CHAR(20),
	FstNme	VARCHAR(30),
	LstNme	VARCHAR(30),
	Title	VARCHAR(30),
	Tel	VARCHAR(15),
	Fax	VARCHAR(15),
	Mobile	VARCHAR(15),
	Email	VARCHAR(50),
	Remark	VARCHAR(200),
	PRIMARY KEY(Cmp, PivNo));
