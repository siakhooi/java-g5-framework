/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class PiEnum {
    public static int PID_LENGTH = 60;

    public enum Status {

        A("A", "PiEnum.Status.A"),
        I("I", "PiEnum.Status.I");
        public String typ,  name;

        Status(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
}
