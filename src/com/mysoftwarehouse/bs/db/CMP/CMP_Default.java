/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.db.CMP;

import com.mysoftwarehouse.bs.B.BSBS0CMP;
import com.mysoftwarehouse.bs.data.GeneralEnum;
import com.mysoftwarehouse.bs.data.SaEnum;
import com.mysoftwarehouse.bs.data.StEnum;
import com.mysoftwarehouse.bs.db.PY.PY;
import com.mysoftwarehouse.bs.db.SA.SA;
import com.mysoftwarehouse.bs.db.ST.ST;
import com.mysoftwarehouse.bs.db.ST.STD;
import com.mysoftwarehouse.bs.db.SY.SY;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class CMP_Default {

    public static void createPY(BSBS0CMP form, String Cmp) throws SQLException {
        PY.insert(form, Cmp, "CHEQUE", "Bank Cheque");
        PY.insert(form, Cmp, "CASH", "Cash");
        PY.insert(form, Cmp, "CREDITCARD", "Credit Card");
        PY.insert(form, Cmp, "TT", "Tele Transfer");
        PY.insert(form, Cmp, "IBT", "Inter Bank Transfer");
        PY.insert(form, Cmp, "BT", "Bank Transfer");
    }

    public static void createSA(BSBS0CMP form, String Cmp) throws SQLException {
        SA.insert(form, Cmp,
                "DISC5", "Discount (5%)", SaEnum.Type.FCT.typ,
                1, new BigDecimal("-0.05"), GeneralEnum.YesNo.N.typ, SaEnum.Status.A.typ);
        SA.insert(form, Cmp,
                "DELIVERY", "Delivery Charge (RM10)", SaEnum.Type.FIX.typ,
                5, new BigDecimal("10"), GeneralEnum.YesNo.N.typ, SaEnum.Status.A.typ);
        SA.insert(form, Cmp,
                "GTAX", "Government Tax (5%)", SaEnum.Type.FCT.typ,
                10, new BigDecimal("0.05"), GeneralEnum.YesNo.N.typ, SaEnum.Status.A.typ);
        SA.insert(form, Cmp,
                "SERVICE", "Service Charge (10%)", SaEnum.Type.FCT.typ,
                10, new BigDecimal("0.10"), GeneralEnum.YesNo.N.typ, SaEnum.Status.A.typ);
        SA.insert(form, Cmp,
                "SEN", "Rounding Adj", SaEnum.Type.NEAR.typ,
                99, new BigDecimal("0.05"), GeneralEnum.YesNo.N.typ, SaEnum.Status.A.typ);
    }

    private static Vector<String> getQtt1Term() {
        Vector<String> v = new Vector<String>();
        v.add("* Validity: This quotation is valid within 14 days from the date above.");
        v.add("* Confirmation: Non-refundable of 50% of total amount upon confirmation.");
        v.add("* Payment Mode: All payment must made in form of Banker Cheque or cash only.");
        v.add("                Payment by cheque has to be made payable to");
        v.add("                <<BANK ACC NAME>>");
        v.add("* Delivery: 5 working days upon payment received, valid only upon clearance of");
        v.add("            cheque(s) within the 5 working days.");
        v.add("* Settlement: Balance payment is to be settled within 30 days upon delivery.");
        return v;
    }

    private static Vector<String> getInv1Term() {
        Vector<String> v = new Vector<String>();
        v.add("* Payment Mode: All payment must made in form of Banker Cheque or cash only.");
        v.add("                Payment by cheque has to be made payable to");
        v.add("                <<BANK ACC NAME>>");
        return v;
    }

    private static Vector<String> getRcp1Term() {
        Vector<String> v = new Vector<String>();
        v.add("* This receipt is valid only upon clearance of cheque(s).");
        return v;
    }

    public static void createST(BSBS0CMP form, String Cmp) throws SQLException {
        ST.insert(form, Cmp, "QTT1", "Sales Quotation Term",
                GeneralEnum.YesNo.Y.typ, GeneralEnum.YesNo.N.typ, 
                GeneralEnum.YesNo.N.typ, StEnum.Status.A.typ);
        STD.writeAll(form, Cmp, "QTT1", getQtt1Term());
        
        ST.insert(form, Cmp, "INV1", "Sales Invoice Term",
                GeneralEnum.YesNo.N.typ, GeneralEnum.YesNo.Y.typ, 
                GeneralEnum.YesNo.N.typ, StEnum.Status.A.typ);
        STD.writeAll(form, Cmp, "INV1", getInv1Term());
        
        ST.insert(form, Cmp, "RCP1", "Sales Receipt Term",
                GeneralEnum.YesNo.N.typ, GeneralEnum.YesNo.N.typ, 
                GeneralEnum.YesNo.Y.typ, StEnum.Status.A.typ);
        STD.writeAll(form, Cmp, "RCP1", getRcp1Term());
    }

    public static void createSY(BSBS0CMP form, String Cmp) throws SQLException {
        SY.insert(form, Cmp, "CHEQUE", "Bank Cheque");
        SY.insert(form, Cmp, "CASH", "Cash");
        SY.insert(form, Cmp, "CREDITCARD", "Credit Card");
        SY.insert(form, Cmp, "TT", "Tele Transfer");
        SY.insert(form, Cmp, "IBT", "Inter Bank Transfer");
        SY.insert(form, Cmp, "BT", "Bank Transfer");
    }
}
