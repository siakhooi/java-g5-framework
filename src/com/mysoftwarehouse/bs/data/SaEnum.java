/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class SaEnum {

    public enum Type {
        FCT("T", "SaEnum.Typ.T"), //percent/facTor
        FIX("F", "SaEnum.Typ.F"), //Fixed
        NEAR("N", "SaEnum.Typ.N"), //round Nearest
        LOWR("L", "SaEnum.Typ.L"), // round Lower
        UPPR("U", "SaEnum.Typ.U"); //round Upper
        public String typ,  name;

        Type(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
    public enum Status {

        A("A", "SaEnum.Status.A"),
        I("I", "SaEnum.Status.I");
        public String typ,  name;

        Status(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
}
