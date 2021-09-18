-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Customer
--

-- Mst: BSCUS - Customer Master

DROP TABLE BSCUS IF EXISTS;

CREATE CACHED TABLE BSCUS(
	Cmp	CHAR(10),
	Cus	CHAR(10),
	Nme	VARCHAR(30),
	Typ	CHAR(1),
	RegNo	VARCHAR(15),
	PriAdd	INT,
	PriCnt	INT,
	WebSte	VARCHAR(50),
	Status	CHAR(1),
	Remark	VARCHAR(200),
	PRIMARY KEY(Cmp, Cus));

-- Mst: BSCUSA - Customer Address

DROP TABLE BSCUSA IF EXISTS;

CREATE CACHED TABLE BSCUSA(
	Cmp	CHAR(10),
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
	PRIMARY KEY(Cmp, Cus, Seq));

-- Mst: BSCUSC - Customer Contacts

DROP TABLE BSCUSC IF EXISTS;

CREATE CACHED TABLE BSCUSC(
	Cmp	CHAR(10),
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
	PRIMARY KEY(Cmp, Cus, Seq));

