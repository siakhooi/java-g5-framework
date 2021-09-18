-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- sales term tables
--

DROP TABLE BSST IF EXISTS;

CREATE CACHED TABLE BSST(
	Cmp	CHAR(10),
	Trm	CHAR(10),
	Nme	CHAR(30),
	ForQtt	CHAR(1),
	ForInv	CHAR(1),
	ForRcp	CHAR(1),
	Status 	CHAR(1),
	PRIMARY KEY(Cmp, Trm));

DROP TABLE BSSTD IF EXISTS;

CREATE CACHED TABLE BSSTD(
	Cmp	CHAR(10),
	Trm	CHAR(10),
	Seq	INT,
	Text	VARCHAR(80),
	PRIMARY KEY(Cmp, Trm, Seq));

-- History tables

DROP SEQUENCE BSSTHSEQ IF EXISTS;

CREATE SEQUENCE BSSTHSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

DROP TABLE BSSTH IF EXISTS;

CREATE CACHED TABLE BSSTH(
	Cmp	CHAR(10),
	HisSeq	INT NOT NULL,
	HisDte	DATETIME,
	HisAct	CHAR(1),
	HisRmk	VARCHAR(200),
-- COPY FROM BSST START
	Trm	CHAR(10),
	Nme	CHAR(30),
	ForQtt	CHAR(1),
	ForInv	CHAR(1),
	ForRcp	CHAR(1),
	Status 	CHAR(1),
-- COPY FROM BSST END
	PRIMARY KEY(Cmp, HisSeq));

DROP SEQUENCE BSSTDHSEQ IF EXISTS;

CREATE SEQUENCE BSSTDHSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

DROP TABLE BSSTDH IF EXISTS;

CREATE CACHED TABLE BSSTDH(
	Cmp	CHAR(10),
	HisSeq	INT NOT NULL,
	HisDte	DATETIME,
	HisAct	CHAR(1),
	HisRmk	VARCHAR(200),
-- COPY FROM BSSTD START
	Trm	CHAR(10),
	Seq	INT,
	Text	VARCHAR(80),
-- COPY FROM BSSTD END
	PRIMARY KEY(Cmp, HisSeq));
