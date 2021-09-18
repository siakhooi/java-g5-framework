/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class GeneralEnum {

    public enum YesNo {

        Y("Y", "GeneralEnum.YesNo.Y"),
        N("N", "GeneralEnum.YesNo.N");
        public String typ,  name;

        YesNo(String t, String n) {
            this.typ = t;
            this.name = n;
        }
    }
}
