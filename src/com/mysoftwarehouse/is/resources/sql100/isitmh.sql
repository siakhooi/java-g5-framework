-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- itm History
--

DROP SEQUENCE ISITMHSEQ IF EXISTS;

CREATE SEQUENCE ISITMHSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

DROP TABLE ISITMH IF EXISTS;

CREATE CACHED TABLE ISITMH(
        Whs     CHAR(10) NOT NULL,
        HisSeq	INT NOT NULL,
	HisDte	DATETIME,
	HisAct	CHAR(1),
	HisRmk	VARCHAR(200),
-- COPY FROM ISITM START
        Itm     CHAR(10) NOT NULL,
        Nme     VARCHAR(30) NOT NULL,
        Uom     CHAR(10) NOT NULL,
        Qty     DECIMAL NOT NULL,
        SsQty   DECIMAL NOT NULL, 
        StdCst  DECIMAL NOT NULL,
        WacCst  DECIMAL NOT NULL,
        Remark  VARCHAR(200),
-- COPY FROM ISITM END
	PRIMARY KEY(Whs, HisSeq));
	

	