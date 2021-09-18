/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class InvEnum {

    public enum Status {

        D("D", "InvEnum.Status.D"), //Draft
        A("A", "InvEnum.Status.A"), //Active, Printed
        X("X", "InvEnum.Status.X"), //Cancel
        C("C", "InvEnum.Status.C"); //Close
        public String typ,  name;

        Status(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }

    public enum Format {

        B("B", "InvEnum.Format.B"), //blank
        T("T", "InvEnum.Format.T"), //text
        I("I", "InvEnum.Format.I"); //image
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
