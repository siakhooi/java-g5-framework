-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Item
--

-- Mst: ISITM - Item Master

DROP TABLE ISITM IF EXISTS;

CREATE CACHED TABLE ISITM(
        Whs     CHAR(10) NOT NULL,
        Itm     CHAR(10) NOT NULL,
        Nme     VARCHAR(30) NOT NULL,
        Uom     CHAR(10) NOT NULL,
        Qty     DECIMAL NOT NULL,
        SsQty   DECIMAL NOT NULL, 
        StdCst  DECIMAL NOT NULL,
        WacCst  DECIMAL NOT NULL,
        Remark  VARCHAR(200),
        PRIMARY KEY (Whs, Itm));

-- : ISITMB - Item Inventory by Location

CREATE CACHED TABLE ISITMB(
        Whs     CHAR(10) NOT NULL,
        Itm     CHAR(10) NOT NULL,
        Loc     CHAR(10) NOT NULL,
        Qty     DECIMAL NOT NULL,
        PRIMARY KEY (Whs, Itm, Loc));
