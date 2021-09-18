-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Purchase Item
--

DROP TABLE BSPID IF EXISTS;

CREATE CACHED TABLE BSPID(
	Cmp	CHAR(10),
	Itm	CHAR(10),
	Seq	INT,
	Text	CHAR(60),
	PRIMARY KEY(Cmp, Itm, Seq));
