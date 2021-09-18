/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class SiEnum {

    public static int SID_LENGTH = 60;

    public enum Status {

        A("A", "SiEnum.Status.A"),
        I("I", "SiEnum.Status.I");
        public String typ,  name;

        Status(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
}
