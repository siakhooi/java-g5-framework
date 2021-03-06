-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Customer History
--

-- History tables

DROP SEQUENCE BSCUSHSEQ IF EXISTS;

CREATE SEQUENCE BSCUSHSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

DROP TABLE BSCUSH IF EXISTS;

CREATE CACHED TABLE BSCUSH(
	Cmp	CHAR(10),
	HisSeq	INT NOT NULL,
	HisDte	DATETIME,
	HisAct	CHAR(1),
	HisRmk	VARCHAR(200),
-- COPY FROM BSCUS START
	Cus	CHAR(10),
	Nme	VARCHAR(30),
	Typ	CHAR(1),
	RegNo	VARCHAR(15),
	PriAdd	INT,
	PriCnt	INT,
	WebSte	VARCHAR(50),
	Status	CHAR(1),
	Remark	VARCHAR(200),
-- COPY FROM BSCUS END
	PRIMARY KEY(Cmp, HisSeq));

DROP SEQUENCE BSCUSAHSEQ IF EXISTS;

CREATE SEQUENCE BSCUSAHSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

DROP TABLE BSCUSAH IF EXISTS;

CREATE CACHED TABLE BSCUSAH(
	Cmp	CHAR(10),
	HisSeq	INT NOT NULL,
	HisDte	DATETIME,
	HisAct	CHAR(1),
	HisRmk	VARCHAR(200),
-- COPY FROM BSCUSA START
	Cus	CHAR(10),
	Seq	INT,
	Add1	VARCHAR(50),
	Add2	VARCHAR(50),
	City	VARCHAR(30),
	ZipCde	CHAR(5),
	Stte	VARCHAR(30),
	Ctry	VARCHAR(30),
	Tel	VARCHAR(15),
	Fax	VARCHAR(15),
	Remark	VARCHAR(200),
-- COPY FROM BSCUSA END
	PRIMARY KEY(Cmp, HisSeq));

DROP SEQUENCE BSCUSCHSEQ IF EXISTS;

CREATE SEQUENCE BSCUSCHSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

DROP TABLE BSCUSCH IF EXISTS;

CREATE CACHED TABLE BSCUSCH(
	Cmp	CHAR(10),
	HisSeq	INT NOT NULL,
	HisDte	DATETIME,
	HisAct	CHAR(1),
	HisRmk	VARCHAR(200),
-- COPY FROM BSCUSC START
	Cus	CHAR(10),
	Seq	INT,
	FstNme	VARCHAR(30),
	LstNme	VARCHAR(30),
	Title	VARCHAR(30),
	Tel	VARCHAR(15),
	Fax	VARCHAR(15),
	Mobile	VARCHAR(15),
	Email	VARCHAR(50),
	Remark	VARCHAR(200),
-- COPY FROM BSCUSC END
	PRIMARY KEY(Cmp, HisSeq));
