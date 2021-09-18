/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class CusEnum {

    public enum CusTyp {

        P("P", "CusEnum.Typ.P"),
        C("C", "CusEnum.Typ.C");
        public String typ,  name;

        CusTyp(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
    public enum CusStatus {

        A("A", "CusEnum.Status.A"),
        I("I", "CusEnum.Status.I");
        public String typ,  name;

        CusStatus(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
}
