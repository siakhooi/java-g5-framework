/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.data;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFUSR {

    public enum Gender {

        M("M", "PFUSR.Gender.Enum.M"),
        F("F", "PFUSR.Gender.Enum.F");
        public String code,  name;

        Gender(String c, String n) {
            this.code = c;
            this.name = n;
        }
    }

    public enum MrtSts {

        S("S", "PFUSR.MrtSts.Enum.S"),
        M("M", "PFUSR.MrtSts.Enum.M"),
        W("W", "PFUSR.MrtSts.Enum.W"),
        D("D", "PFUSR.MrtSts.Enum.D");
        public String code,  name;

        MrtSts(String c, String n) {
            this.code = c;
            this.name = n;
        }
    }
}
