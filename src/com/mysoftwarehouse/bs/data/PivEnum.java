/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class PivEnum {
    public enum Status {

        D("D", "PivEnum.Status.D"), //Draft
        A("A", "PivEnum.Status.A"), //Active, Printed
        X("X", "PivEnum.Status.X"), //Cancel
        C("C", "PivEnum.Status.C"); //Close
        public String typ,  name;

        Status(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }

    public enum Format {

        B("B", "PivEnum.Format.B"), //blank
        T("T", "PivEnum.Format.T"), //text
        I("I", "PivEnum.Format.I"); //image
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
