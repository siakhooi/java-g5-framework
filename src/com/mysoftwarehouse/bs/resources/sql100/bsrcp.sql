-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- sales receipt tables
--

-- Mst: BSRCPY - Sales Receipt payment Type

DROP TABLE BSRCP IF EXISTS;

CREATE CACHED TABLE BSRCP(
	Cmp		CHAR(10),
	RcpNo		CHAR(20),
	Cus		CHAR(10),
	RcpDte		DATETIME,
	RefNum  	VARCHAR(20),
        PayTyp          CHAR(10),
        PayRefNum       VARCHAR(20),
        PayInfo         VARCHAR(60),
	Remark		VARCHAR(200),
	Status 		CHAR(1),
        TtlGrsAmt       DECIMAL,
        TtlAmt          DECIMAL,
	PRIMARY KEY(Cmp, RcpNo));

DROP TABLE BSRCPD IF EXISTS;

CREATE CACHED TABLE BSRCPD(
	Cmp	CHAR(10),
	RcpNo	CHAR(20),
	Itm	CHAR(10),
	Price	DECIMAL,
	Qty	INT,
	TtlAmt	DECIMAL,
	PRIMARY KEY(Cmp, RcpNo, Itm));

DROP TABLE BSRCPA IF EXISTS;

CREATE CACHED TABLE BSRCPA(
	Cmp	CHAR(10),
	RcpNo	CHAR(20),
	Add1	VARCHAR(60),
	Add2	VARCHAR(60),
	City	VARCHAR(30),
	ZipCde	CHAR(5),
	Stte	VARCHAR(30),
	Ctry	VARCHAR(30),
	Tel	VARCHAR(15),
	Fax	VARCHAR(15),
	Remark	VARCHAR(200),
	PRIMARY KEY(Cmp, RcpNo));

DROP TABLE BSRCPC IF EXISTS;

CREATE CACHED TABLE BSRCPC(
	Cmp	CHAR(10),
	RcpNo	CHAR(20),
	FstNme	VARCHAR(30),
	LstNme	VARCHAR(30),
	Title	VARCHAR(30),
	Tel	VARCHAR(15),
	Fax	VARCHAR(15),
	Mobile	VARCHAR(15),
	Email	VARCHAR(50),
	Remark	VARCHAR(200),
	PRIMARY KEY(Cmp, RcpNo));
	
DROP TABLE BSRCPJ IF EXISTS;

CREATE CACHED TABLE BSRCPJ(
	Cmp	CHAR(10),
	RcpNo	CHAR(20),
	Seq	INT,
	Adj	CHAR(10),
	Nme	CHAR(30),
	Typ	CHAR(1),
	Prio	INT,
	Amt	DECIMAL,
	EffAmt	DECIMAL,
	OpenAmt	DECIMAL,
	NewAmt	DECIMAL,
	PRIMARY KEY(Cmp, RcpNo, Seq));

DROP TABLE BSRCPR IF EXISTS;

CREATE CACHED TABLE BSRCPR(
	Cmp	CHAR(10),
	RcpNo	CHAR(20),
	Seq	INT,
	Text	CHAR(60),
	PRIMARY KEY(Cmp, RcpNo, Seq));
