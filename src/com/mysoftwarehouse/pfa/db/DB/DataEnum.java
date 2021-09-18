/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.db.DB;

/**
 *
 * @author Ng Siak Hooi
 */
public class DataEnum {

    public enum TxnTyp {

        B("B"),
        N("N"),
        R("R");
        public String code;

        TxnTyp(String code) {
            this.code = code;
        }

        public static boolean contains(String at) {
            for (TxnTyp at1 : TxnTyp.values()) {
                if (at1.code.equals(at)) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum AccTyp {

        A("A"),
        L("L"),
        C("C"),
        I("I"),
        E("E");
        public String code;

        AccTyp(String code) {
            this.code = code;
        }

        public static boolean contains(String at) {
            for (AccTyp at1 : AccTyp.values()) {
                if (at1.code.equals(at)) {
                    return true;
                }
            }
            return false;
        }

        public static boolean isBSType(String at) {
            return A.code.equals(at) ||
                    L.code.equals(at) ||
                    C.code.equals(at);
        }

        public static boolean isDebitType(String at) {
            return A.code.equals(at) ||
                    E.code.equals(at);
        }

        public boolean isBSType() {
            return isBSType(code);
        }
    }

    public enum SysTyp {

        Y("Y"),
        N("N");
        public String code;

        SysTyp(String code) {
            this.code = code;
        }
    }

    public enum Gender {

        M("M"),
        F("F");
        public String code;

        Gender(String code) {
            this.code = code;
        }
    }

    public enum MrtSts {

        S("S"),
        M("M"),
        W("W"),
        D("D");
        public String code;

        MrtSts(String code) {
            this.code = code;
        }
    }

    public enum MainAccLstTyp {

        A("A"),
        B("B"),
        M("M"),
        Y("Y");
        public String code;

        MainAccLstTyp(String code) {
            this.code = code;
        }
    }
}
