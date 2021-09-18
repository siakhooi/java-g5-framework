-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Company & Configuration
--

-- Mst: BSCMP - Company Master

DROP TABLE BSCMP IF EXISTS;

CREATE CACHED TABLE BSCMP(
	Cmp	CHAR(10),
	Nme	VARCHAR(30),
	RegNo	VARCHAR(15),
	PriAdd	INT,
	PriCnt	INT,
	WebSte	VARCHAR(50),
	CurCde	CHAR(5),
	PRIMARY KEY(Cmp));

-- Mst: BSCMPA - Company Address

DROP TABLE BSCMPA IF EXISTS;

CREATE CACHED TABLE BSCMPA(
	Cmp	CHAR(10),
	Seq	INT,
	Add1	VARCHAR(60),
	Add2	VARCHAR(60),
	City	VARCHAR(30),
	ZipCde	CHAR(5),
	Stte	VARCHAR(30),
	Ctry	VARCHAR(30),
	Tel	VARCHAR(15),
	Fax	VARCHAR(15),
	Remark	VARCHAR(200),
	PRIMARY KEY(Cmp, Seq));

-- Mst: BSCMPC - Company Contacts

DROP TABLE BSCMPC IF EXISTS;

CREATE CACHED TABLE BSCMPC(
	Cmp	CHAR(10),
	Seq	INT,
	FstNme	VARCHAR(30),
	LstNme	VARCHAR(30),
	Title	VARCHAR(30),
	Tel	VARCHAR(15),
	Fax	VARCHAR(15),
        Mobile  VARCHAR(15),
	Email	VARCHAR(50),
	Remark	VARCHAR(200),
	PRIMARY KEY(Cmp, Seq));

-- Cfg: BSCMPG - Configuration by Company

DROP TABLE BSCFG IF EXISTS;

CREATE CACHED TABLE BSCFG(
	Cmp		CHAR(10),
	LstNumQtt	INT,
	LstNumInv	INT,
	LstNumRcp	INT,
	LstNumPor	INT,
	LstNumPiv	INT,
	NumDgtsQtt	INT,
	NumDgtsInv	INT,
	NumDgtsRcp	INT,
	NumDgtsPor	INT,
	NumDgtsPiv	INT,
	PrfxQtt         CHAR(10),
	PrfxInv         CHAR(10),
	PrfxRcp         CHAR(10),
	PrfxPor         CHAR(10),
	PrfxPiv         CHAR(10),
	DfltTrmQtt	CHAR(10),
	DfltTrmInv	CHAR(10),
	DfltTrmRcp	CHAR(10),
	PrtFmtQtt	CHAR(1),
	PrtFmtInv	CHAR(1),
	PrtFmtRcp	CHAR(1),
	PrtFmtPor	CHAR(1),
	PrtFmtPiv	CHAR(1),
	PRIMARY KEY(Cmp));

-- Prefix Length + NumDgts* can not > 20	

DROP TABLE BSCFGI IF EXISTS;

CREATE CACHED TABLE BSCFGI(
	Cmp		CHAR(10),
	LetterHead	BINARY,
	PRIMARY KEY(Cmp));
