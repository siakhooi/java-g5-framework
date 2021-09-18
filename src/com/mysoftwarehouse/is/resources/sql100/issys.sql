-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- application database version
--

DROP TABLE ISDB IF EXISTS;

CREATE CACHED TABLE ISDB(
  Typ CHAR(1) NOT NULL,
  Version INT NOT NULL,
  PRIMARY KEY(Typ));

INSERT INTO ISDB(Typ, Version) VALUES('A', 100);
