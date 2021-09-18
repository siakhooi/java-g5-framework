-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Warehouse
--

-- Mst: ISWHS - Warehouse Master

DROP TABLE ISWHS IF EXISTS;

CREATE CACHED TABLE ISWHS(
        Whs         CHAR(10) NOT NULL,
        Nme         VARCHAR(30) NOT NULL,
        CstTyp      CHAR(1) NOT NULL, 
        Remark      VARCHAR(200),
        LstTxnSeq   INT DEFAULT 0,
        PRIMARY KEY (Whs));

DROP SEQUENCE ISWHSLSTTXNSEQ IF EXISTS;

CREATE SEQUENCE ISWHSLSTTXNSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

