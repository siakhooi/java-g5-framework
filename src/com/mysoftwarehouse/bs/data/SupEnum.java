/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class SupEnum {

    public enum SupTyp {

        P("P", "SupEnum.Typ.P"),
        C("C", "SupEnum.Typ.C");
        public String typ,  name;

        SupTyp(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
    public enum SupStatus {

        A("A", "SupEnum.Status.A"),
        I("I", "SupEnum.Status.I");
        public String typ,  name;

        SupStatus(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
}
