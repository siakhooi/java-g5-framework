-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Location
--

-- Mst: ISLOC - Warehouse Location Master

DROP TABLE ISLOC IF EXISTS;

CREATE CACHED TABLE ISLOC(
        Whs     CHAR(10) NOT NULL,
        Loc     CHAR(10) NOT NULL,
        Nme     VARCHAR(30) NOT NULL,
        Remark  VARCHAR(200),
        PRIMARY KEY (Whs, Loc));
