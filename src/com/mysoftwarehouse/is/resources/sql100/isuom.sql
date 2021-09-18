-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Unit of Measurement
--

-- Mst: ISUOM - Unit Of Measurement Master

DROP TABLE ISUOM IF EXISTS;

CREATE CACHED TABLE ISUOM(
        Whs     CHAR(10) NOT NULL,
        Uom     CHAR(10) NOT NULL,
        Nme     VARCHAR(30) NOT NULL,
        Remark  VARCHAR(200),
        PRIMARY KEY (Whs, Uom));

