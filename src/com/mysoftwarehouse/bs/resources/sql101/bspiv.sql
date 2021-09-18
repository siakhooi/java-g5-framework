-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Purchase Invoice
--

DROP TABLE BSPIVE IF EXISTS;

CREATE CACHED TABLE BSPIVE(
	Cmp	CHAR(10),
	PivNo	CHAR(20),
	Itm	CHAR(10),
	Seq	INT,
	Text	CHAR(60),
	PRIMARY KEY(Cmp, PivNo, Itm, Seq));
