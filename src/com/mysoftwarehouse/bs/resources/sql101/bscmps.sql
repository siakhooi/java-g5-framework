-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Company Signature
--

DROP TABLE BSCMPS IF EXISTS;

CREATE CACHED TABLE BSCMPS(
	Cmp	 CHAR(10),
	QttSign1 VARCHAR(60),
	QttSign2 VARCHAR(60),
	InvSign1 VARCHAR(60),
	InvSign2 VARCHAR(60),
	RcpSign1 VARCHAR(60),
	RcpSign2 VARCHAR(60),
	PorSign1 VARCHAR(60),
	PorSign2 VARCHAR(60),
	PivSign1 VARCHAR(60),
	PivSign2 VARCHAR(60),
	PRIMARY KEY(Cmp));

INSERT INTO BSCMPS (Cmp, 
QttSign1, QttSign2, InvSign1, InvSign2, RcpSign1, RcpSign2, 
PorSign1, PorSign2, PivSign1, PivSign2) 
SELECT Cmp, '', '', '', '', '', '', '', '', '', '' FROM BSCMP;

