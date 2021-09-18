-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Sales Invoice
--

DROP TABLE BSINVE IF EXISTS;

CREATE CACHED TABLE BSINVE(
	Cmp	CHAR(10),
	InvNo	CHAR(20),
	Itm	CHAR(10),
	Seq	INT,
	Text	CHAR(60),
	PRIMARY KEY(Cmp, InvNo, Itm, Seq));
