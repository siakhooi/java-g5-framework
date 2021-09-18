/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.rockey;

/**
 *
 * @author Ng Siak Hooi
 */
public class DongleAlgoValue {

    public int start,  seed;
    public short p1,  p2,  p3,  p4;
    public short r1,  r2,  r3,  r4;

    public DongleAlgoValue(int start, int seed,
            short p1, short p2, short p3, short p4,
            short r1, short r2, short r3, short r4) {
        this.start = start;
        this.seed = seed;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
    }

    public boolean compare(short r1, short r2, short r3, short r4) {
        if (this.r1 != r1) {
            return false;
        }
        if (this.r2 != r2) {
            return false;
        }
        if (this.r3 != r3) {
            return false;
        }
        if (this.r4 != r4) {
            return false;
        }
        return true;
    }
}
