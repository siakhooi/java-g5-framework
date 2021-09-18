-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Purchase Item
--

DROP TABLE BSPI IF EXISTS;

CREATE CACHED TABLE BSPI(
	Cmp	CHAR(10),
	Itm	CHAR(10),
	Nme	CHAR(60),
	Status 	CHAR(1),
	PRIMARY KEY(Cmp, Itm));

DROP TABLE BSPIP IF EXISTS;

CREATE CACHED TABLE BSPIP(
	Cmp	CHAR(10),
	Itm	CHAR(10),
	Sup	CHAR(10),
	Price	DECIMAL,
	Status	CHAR(1),
	Remark	CHAR(200),
	PRIMARY KEY(Cmp, Itm, Sup));

