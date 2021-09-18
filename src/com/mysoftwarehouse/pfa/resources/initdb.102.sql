-- Copyright 2007 GQR Solutions. All rights reserved.
-- PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
--
-- initdb.102.sql - initialize new database
--

-- Delete Transactions upload

DROP TABLE PFTXNT2 IF EXISTS;

CREATE CACHED TABLE PFTXNT2(
  Id VARCHAR(100) NOT NULL,
  Seq INT NOT NULL,
  TxnId INT,
  PRIMARY KEY (id, seq));

DROP SEQUENCE PFTXNT2SEQ IF EXISTS;

CREATE SEQUENCE PFTXNT2SEQ AS INTEGER 
START WITH 0 INCREMENT BY 1;
  
UPDATE PFDB SET Version=102 WHERE Typ='A';
