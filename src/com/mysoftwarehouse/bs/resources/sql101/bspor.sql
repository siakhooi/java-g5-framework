-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Purchase Order
--

DROP TABLE BSPORE IF EXISTS;

CREATE CACHED TABLE BSPORE(
	Cmp	CHAR(10),
	PorNo	CHAR(20),
	Itm	CHAR(10),
	Seq	INT,
	Text	CHAR(60),
	PRIMARY KEY(Cmp, PorNo, Itm, Seq));
