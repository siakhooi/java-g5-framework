/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFACC {

    public enum Status {

        A("A", "PFACC.Status.Enum.A"),
        I("I", "PFACC.Status.Enum.I");
        public String code,  name;

        Status(String c, String n) {
            this.code = c;
            this.name = n;
        }
    }

    public enum AccTyp {

        A("A", "PFACC.AccTyp.Enum.A"),
        L("L", "PFACC.AccTyp.Enum.L"),
        I("I", "PFACC.AccTyp.Enum.I"),
        E("E", "PFACC.AccTyp.Enum.E");
        public String code,  name;

        AccTyp(String c, String n) {
            this.code = c;
            this.name = n;
        }
    }
}
