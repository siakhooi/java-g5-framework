/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.rockey;

/**
 *
 * @author Ng Siak Hooi
 */
public class DongleAlgoFormula {

    private String algo = "";

    private void add(String c) {
        if (c == null) {
            return;
        }
        if (c.length() == 0) {
            return;
        }
        if (algo.length() == 0) {
            algo = c;
        } else {
            algo = algo + "," + c;
        }
    }

    byte[] getAlgo() {
        add("A=A^H");
        add("B=B^G");
// Group 1
        add("A=A<5");
        add("B=B<47");
        add("C=C<13");
        add("D=D<2");
        add("E=E*11");
        add("F=F*57");
        add("G=G*23");
        add("H=H*47");

        add("A=A^E");
        add("B=B^F");
        add("C=C^G");
        add("D=D^H");

        add("C=C^A");
        add("D=D^B");
// Group 2
        add("A=A*61");
        add("B=B*29");
        add("C=C*3");
        add("D=D*43");
        add("E=E<23");
        add("F=F<13");
        add("G=G<5");
        add("H=H<11");

        add("A=A^F");
        add("B=B^H");
        add("C=C^E");
        add("D=D^G");

        add("C=C^E");
        add("D=D^F");
// Group 3
        add("A=A<29");
        add("B=B*3");
        add("C=C*43");
        add("D=D<61");
        add("E=E*13");
        add("F=F<23");
        add("G=G*7");
        add("H=H<57");

        add("A=A^H");
        add("B=B^G");
        add("C=C^F");
        add("D=D^E");

        add("A=A^D");
        add("B=B^C");
// Group 4
        add("A=A*23");
        add("B=B<5");
        add("C=C<8");
        add("D=D*11");
        add("E=E<11");
        add("F=F*43");
        add("G=G<17");
        add("H=H*17");

        add("A=A^G");
        add("B=B^E");
        add("C=C^H");
        add("D=D^F");

        return algo.getBytes();
    }
}
