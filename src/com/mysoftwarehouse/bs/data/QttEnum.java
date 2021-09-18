/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class QttEnum {

    public enum Status {

        D("D", "QttEnum.Status.D"), //Draft
        A("A", "QttEnum.Status.A"), //Active, Printed
        X("X", "QttEnum.Status.X"), //Cancel
        C("C", "QttEnum.Status.C"); //Close
        public String typ,  name;

        Status(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }

    public enum Format {

        B("B", "QttEnum.Format.B"), //blank
        T("T", "QttEnum.Format.T"), //text
        I("I", "QttEnum.Format.I"); //image
        public String typ,  name;

        Format(String t, String n) {
            this.typ = t;
            this.name = n;
        }

        public static Format get(String text) {
            if (B.typ.equals(text)) {
                return B;
            }
            if (I.typ.equals(text)) {
                return I;
            }
            if (T.typ.equals(text)) {
                return T;
            }
            return T;
        }
    }
}
