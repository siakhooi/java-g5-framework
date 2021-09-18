/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.data;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.pfa.C.PFCL0ACCM;
import com.mysoftwarehouse.pfa.C.PFCL0ACCY;
import com.mysoftwarehouse.pfa.C.PFCL1ACCY;
import com.mysoftwarehouse.pfa.C.PFCL2ACCY;
import com.mysoftwarehouse.pfa.C.PFCL3ACCY;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFCFG {

    public enum MainListType {

        A("A", "PFCFG.MainListType.Enum.A", "PFCL2ACCY", PFCL2ACCY.class),
        S("S", "PFCFG.MainListType.Enum.S", "PFCL3ACCY", PFCL3ACCY.class),
        B("B", "PFCFG.MainListType.Enum.B", "PFCL0ACCY", PFCL0ACCY.class),
        Y("Y", "PFCFG.MainListType.Enum.Y", "PFCL1ACCY", PFCL1ACCY.class),
        M("M", "PFCFG.MainListType.Enum.M", "PFCL0ACCM", PFCL0ACCM.class);
//        D("D", "PFCFG.MainListType.Enum.D", "PFCL0TXN", PFCL0TXN.class, true),
//        I("I", "PFCFG.MainListType.Enum.I", "PFCL1TXN", PFCL1TXN.class, true);
        public String code,  name;
        public String formCode;
        public Class<? extends UserFormInterface> formClass;

        MainListType(String c, String n, String fc,
                Class<? extends UserFormInterface> fz) {
            this.code = c;
            this.name = n;
            this.formCode = fc;
            this.formClass = fz;
        }

        public static Class<? extends UserFormInterface> getClass(String code) {
            for (MainListType m : MainListType.values()) {
                if (m.code.equals(code)) {
                    return m.formClass;
                }
            }
            return A.formClass;
        }
    }
}
