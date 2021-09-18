/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class StEnum {
    public static int STD_LENGTH=80;

    public enum Status {

        A("A", "StEnum.Status.A"),
        I("I", "StEnum.Status.I");
        public String typ,  name;

        Status(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
}
