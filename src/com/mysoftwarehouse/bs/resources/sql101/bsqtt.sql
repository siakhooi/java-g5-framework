-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Sales Quotation
--

DROP TABLE BSQTTE IF EXISTS;

CREATE CACHED TABLE BSQTTE(
	Cmp	CHAR(10),
	QttNo	CHAR(20),
	Itm	CHAR(10),
	Seq	INT,
	Text	CHAR(60),
	PRIMARY KEY(Cmp, QttNo, Itm, Seq));
