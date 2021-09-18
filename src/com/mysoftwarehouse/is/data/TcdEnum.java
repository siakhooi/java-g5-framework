/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.data;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.F.ISFL2TCD;
import com.mysoftwarehouse.is.F.ISFL3TCD;
import com.mysoftwarehouse.is.F.ISFL4TCD;
import com.mysoftwarehouse.is.F.ISFL5TCD;

/**
 *
 * @author Ng Siak Hooi
 */
public class TcdEnum {

    public enum TxnTyp {

        I("I", "TxnTyp.Typ.I", ISFL2TCD.class),
        O("O", "TxnTyp.Typ.O", ISFL3TCD.class),
        A("A", "TxnTyp.Typ.A", ISFL4TCD.class),
        X("X", "TxnTyp.Typ.X", ISFL5TCD.class);
        public String typ,  name;
        public Class<? extends UserFormInterface> lookupForm;

        TxnTyp(String t, String n, Class<? extends UserFormInterface> f) {
            this.typ = t;
            this.name = n;
            this.lookupForm = f;
        }

        public static TxnTyp getType(String t) {
            if (I.typ.equals(t)) {
                return I;
            }
            if (O.typ.equals(t)) {
                return O;
            }
            if (A.typ.equals(t)) {
                return A;
            }
            if (X.typ.equals(t)) {
                return X;
            }
            return null;
        }
    }
}
