/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class CstEnum {

    public enum CstTyp {

        S("S", "CstTyp.Typ.S"),
        W("W", "CstTyp.Typ.W");
        public String typ,  name;

        CstTyp(String t, String n) {
            this.typ = t;
            this.name = n;
        }

        public static CstTyp getType(String t) {
            if (S.typ.equals(t)) {
                return S;
            }
            if (W.typ.equals(t)) {
                return W;
            }
            return null;
        }
    }
}
