-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- sales item tables
--

DROP TABLE BSSI IF EXISTS;

CREATE CACHED TABLE BSSI(
	Cmp	CHAR(10),
	Itm	CHAR(10),
	Nme	CHAR(30),
	Price	DECIMAL,
	Status 	CHAR(1),
	PRIMARY KEY(Cmp, Itm));

DROP TABLE BSSID IF EXISTS;

CREATE CACHED TABLE BSSID(
	Cmp	CHAR(10),
	Itm	CHAR(10),
	Seq	INT,
	Text	CHAR(60),
	PRIMARY KEY(Cmp, Itm, Seq));
