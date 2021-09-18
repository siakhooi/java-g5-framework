-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Transaction Code
--

-- Mst: ISTCD - Transaction Code Master

DROP TABLE ISTCD IF EXISTS;

CREATE CACHED TABLE ISTCD(
        Whs     CHAR(10) NOT NULL,
        Tcd     CHAR(10) NOT NULL,
        Nme     VARCHAR(30) NOT NULL,
        Typ     CHAR(1) NOT NULL,
        Remark  VARCHAR(200),
        PRIMARY KEY (Whs, Tcd));

