-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- bizsuite.db.sys.100.sql - initialize system tables
--

DROP TABLE BSDB IF EXISTS;

CREATE CACHED TABLE BSDB(
  Typ CHAR(1) NOT NULL,
  Version INT NOT NULL,
  PRIMARY KEY(Typ));

INSERT INTO BSDB(Typ, Version) VALUES('A', 100);
