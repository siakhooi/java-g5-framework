/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.rockey;

/**
 *
 * @author Ng Siak Hooi
 */
public class DongleSeedValue {

    public int seed;
    public short s1,  s2,  s3,  s4;

    public DongleSeedValue(int seed, short s1, short s2, short s3, short s4) {
        this.seed = seed;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    public boolean compare(short s1, short s2, short s3, short s4) {
        if (this.s1 != s1) {
            return false;
        }
        if (this.s2 != s2) {
            return false;
        }
        if (this.s3 != s3) {
            return false;
        }
        if (this.s4 != s4) {
            return false;
        }
        return true;
    }
}
