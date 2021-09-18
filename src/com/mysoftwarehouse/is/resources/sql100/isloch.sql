-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- Cmp History
--

DROP SEQUENCE ISLOCHSEQ IF EXISTS;

CREATE SEQUENCE ISLOCHSEQ AS INTEGER
START WITH 0 INCREMENT BY 1;

DROP TABLE ISLOCH IF EXISTS;

CREATE CACHED TABLE ISLOCH(
        Whs     CHAR(10) NOT NULL,
        HisSeq	INT NOT NULL,
	HisDte	DATETIME,
	HisAct	CHAR(1),
	HisRmk	VARCHAR(200),
-- COPY FROM ISLOC START
        Loc     CHAR(10) NOT NULL,
        Nme     VARCHAR(30) NOT NULL,
        Remark  VARCHAR(200),
-- COPY FROM ISLOC END
	PRIMARY KEY(Whs, HisSeq));
	

	