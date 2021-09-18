-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Inventory Transaction
--

DROP TABLE ISTXN IF EXISTS;

CREATE CACHED TABLE ISTXN(
	Whs         CHAR(10) NOT NULL,
        Seq         INT NOT NULL,
        TxnDte      DATE NOT NULL,
        TxnTme      TIME NOT NULL,
        Tcd         CHAR(10) NOT NULL,
        Itm         CHAR(10) NOT NULL,
        FrmLoc      CHAR(10) NOT NULL,
        ToLoc       CHAR(10) NOT NULL,
        TxnQty      DECIMAL NOT NULL,
        TxnCst      DECIMAL NOT NULL,
        OpnBal      DECIMAL NOT NULL,
        NewBal      DECIMAL NOT NULL,
        NewCst      DECIMAL NOT NULL,
	Remark      VARCHAR(200),
	PRIMARY KEY(Whs, Seq));

CREATE INDEX IDXISTXNA ON ISTXN (Whs, Itm, TxnDte, TxnTme, Seq);

CREATE CACHED TABLE ISTXNA(
	Whs         CHAR(10) NOT NULL,
        Seq         INT NOT NULL,
        TxnDte      DATE NOT NULL,
        TxnTme      TIME NOT NULL,
        Tcd         CHAR(10) NOT NULL,
        Typ         CHAR(1) NOT NULL,
        Itm         CHAR(10) NOT NULL,
        Loc         CHAR(10) NOT NULL,
        TxnQty      DECIMAL NOT NULL,
        OpnBal      DECIMAL NOT NULL,
        NewBal      DECIMAL NOT NULL,
	PRIMARY KEY(Whs, Seq, Loc));
CREATE INDEX IDXISTXNAA ON ISTXNA (Whs, Itm, TxnDte, TxnTme, Seq);
CREATE INDEX IDXISTXNAB ON ISTXNA (Whs, Itm, Loc, TxnDte, TxnTme, Seq);
