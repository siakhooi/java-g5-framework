-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Sales Receipt
--

DROP TABLE BSRCPE IF EXISTS;

CREATE CACHED TABLE BSRCPE(
	Cmp	CHAR(10),
	RcpNo	CHAR(20),
	Itm	CHAR(10),
	Seq	INT,
	Text	CHAR(60),
	PRIMARY KEY(Cmp, RcpNo, Itm, Seq));
