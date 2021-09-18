/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.A;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.bs.CG.C.BSCL0CUS;
import com.mysoftwarehouse.bs.CG.C.BSCR0CUS;
import com.mysoftwarehouse.bs.CG.C.BSCR1CUS;
import com.mysoftwarehouse.bs.CG.C.BSCR2CUS;
import com.mysoftwarehouse.bs.CG.D.BSDL0SI;
import com.mysoftwarehouse.bs.CG.D.BSDR0SI;
import com.mysoftwarehouse.bs.CG.D.BSDR0SID;
import com.mysoftwarehouse.bs.CG.E.BSEL0SA;
import com.mysoftwarehouse.bs.CG.E.BSER0SA;
import com.mysoftwarehouse.bs.CG.F.BSFL0SY;
import com.mysoftwarehouse.bs.CG.F.BSFR0SY;
import com.mysoftwarehouse.bs.CG.G.BSGL0ST;
import com.mysoftwarehouse.bs.CG.G.BSGR0ST;
import com.mysoftwarehouse.bs.CG.G.BSGR0STD;
import com.mysoftwarehouse.bs.HJ.H.BSHL0QTT;
import com.mysoftwarehouse.bs.HJ.H.BSHR0QTT;
import com.mysoftwarehouse.bs.HJ.H.BSHR2QTT;
import com.mysoftwarehouse.bs.HJ.H.BSHR2QTTD;
import com.mysoftwarehouse.bs.HJ.H.BSHR3QTT;
import com.mysoftwarehouse.bs.HJ.H.BSHR3QTTD;
import com.mysoftwarehouse.bs.HJ.I.BSIL0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIR0INV;
import com.mysoftwarehouse.bs.HJ.I.BSIR2INV;
import com.mysoftwarehouse.bs.HJ.I.BSIR2INVD;
import com.mysoftwarehouse.bs.HJ.I.BSIR3INV;
import com.mysoftwarehouse.bs.HJ.I.BSIR3INVD;
import com.mysoftwarehouse.bs.HJ.J.BSJL0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJR0RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJR2RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJR2RCPD;
import com.mysoftwarehouse.bs.HJ.J.BSJR3RCP;
import com.mysoftwarehouse.bs.HJ.J.BSJR3RCPD;
import com.mysoftwarehouse.bs.KM.K.BSKL0SUP;
import com.mysoftwarehouse.bs.KM.K.BSKR0SUP;
import com.mysoftwarehouse.bs.KM.K.BSKR1SUP;
import com.mysoftwarehouse.bs.KM.K.BSKR2SUP;
import com.mysoftwarehouse.bs.KM.L.BSLL0PI;
import com.mysoftwarehouse.bs.KM.L.BSLR0PI;
import com.mysoftwarehouse.bs.KM.L.BSLR0PID;
import com.mysoftwarehouse.bs.KM.L.BSLR1PI;
import com.mysoftwarehouse.bs.KM.L.BSLR2PI;
import com.mysoftwarehouse.bs.KM.M.BSML0PY;
import com.mysoftwarehouse.bs.KM.M.BSMR0PY;
import com.mysoftwarehouse.bs.KM.Q.BSQL0PSI;
import com.mysoftwarehouse.bs.KM.Q.BSQR0PSI;
import com.mysoftwarehouse.bs.KM.Q.BSQR0PSID;
import com.mysoftwarehouse.bs.NO.N.BSNL0POR;
import com.mysoftwarehouse.bs.NO.N.BSNR0POR;
import com.mysoftwarehouse.bs.NO.N.BSNR2POR;
import com.mysoftwarehouse.bs.NO.N.BSNR2PORD;
import com.mysoftwarehouse.bs.NO.N.BSNR3POR;
import com.mysoftwarehouse.bs.NO.N.BSNR3PORD;
import com.mysoftwarehouse.bs.NO.O.BSOL0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOR0PIV;
import com.mysoftwarehouse.bs.NO.O.BSOR2PIV;
import com.mysoftwarehouse.bs.NO.O.BSOR2PIVD;
import com.mysoftwarehouse.bs.NO.O.BSOR3PIV;
import com.mysoftwarehouse.bs.NO.O.BSOR3PIVD;
import com.mysoftwarehouse.bs.P.BSPR0INV;
import com.mysoftwarehouse.bs.P.BSPR0PIV;
import com.mysoftwarehouse.bs.P.BSPR0POR;
import com.mysoftwarehouse.bs.P.BSPR0QTT;
import com.mysoftwarehouse.bs.P.BSPR0RCP;
import com.mysoftwarehouse.bs.P.BSPR1INV;
import com.mysoftwarehouse.bs.P.BSPR1PIV;
import com.mysoftwarehouse.bs.P.BSPR1POR;
import com.mysoftwarehouse.bs.P.BSPR1QTT;
import com.mysoftwarehouse.bs.P.BSPR1RCP;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSAM0 extends MenuForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return BSAI1.class;
    }

    private void co(String n) {
        String title = "BSAM0." + n + ".title";
        String description = "BSAM0." + n + ".description";
        super.openCategory(title, description);
    }

    private void cc() {
        super.closeCategory();
    }

    private void a(Class<? extends UserFormInterface> f, String n) {
        a(f, n, true);
    }

    private void a(Class<? extends UserFormInterface> f, String n, boolean newThread) {
        String title = n + ".title";
        String description = n + ".description";
        super.addApplication(f, title, description, newThread);
    }

    private void a(String action) {
        a(action, "BSAM0." + action);
    }

    private void a(String action, String n) {
        String title = n + ".title";
        String description = n + ".description";
        super.addApplication(action, title, description);
    }

    @Override
    public void build() {
        super.openRole("BSAM0.role.sale.title", "BSAM0.role.sale.description");
        co("CG");
        a(BSCL0CUS.class, "BSCL0CUS"); //customer info
        a(BSDL0SI.class, "BSDL0SI");
        a(BSEL0SA.class, "BSEL0SA");
        a(BSFL0SY.class, "BSFL0SY");
        a(BSGL0ST.class, "BSGL0ST");
        co("CG.Report");
        a(BSCR0CUS.class, "BSCR0CUS");
        a(BSCR1CUS.class, "BSCR1CUS");
        a(BSCR2CUS.class, "BSCR2CUS");
        a(BSDR0SI.class, "BSDR0SI");
        a(BSDR0SID.class, "BSDR0SID");
        a(BSER0SA.class, "BSER0SA");
        a(BSFR0SY.class, "BSFR0SY");
        a(BSGR0ST.class, "BSGR0ST");
        a(BSGR0STD.class, "BSGR0STD");
        cc();
        cc();

        co("H");
        a(BSHL0QTT.class, "BSHL0QTT");
        a(BSHR0QTT.class, "BSHR0QTT");
        co("H.Report");
        a(BSHR2QTT.class, "BSHR2QTT");
        a(BSHR3QTT.class, "BSHR3QTT");
        a(BSHR2QTTD.class, "BSHR2QTTD");
        a(BSHR3QTTD.class, "BSHR3QTTD");
        cc();
        cc();

        co("I");
        a(BSIL0INV.class, "BSIL0INV");
        a(BSIR0INV.class, "BSIR0INV");
        co("I.Report");
        a(BSIR2INV.class, "BSIR2INV");
        a(BSIR3INV.class, "BSIR3INV");
        a(BSIR2INVD.class, "BSIR2INVD");
        a(BSIR3INVD.class, "BSIR3INVD");
        cc();
        cc();

        co("J");
        a(BSJL0RCP.class, "BSJL0RCP");
        a(BSJR0RCP.class, "BSJR0RCP");
        co("J.Report");
        a(BSJR2RCP.class, "BSJR2RCP");
        a(BSJR3RCP.class, "BSJR3RCP");
        a(BSJR2RCPD.class, "BSJR2RCPD");
        a(BSJR3RCPD.class, "BSJR3RCPD");
        cc();
        cc();
        super.closeRole();
        super.openRole("BSAM0.role.purchase.title", "BSAM0.role.purchase.description");

        co("KM");
        a(BSKL0SUP.class, "BSKL0SUP"); //supplier info
        a(BSLL0PI.class, "BSLL0PI");
        a(BSML0PY.class, "BSML0PY");
        a(BSQL0PSI.class, "BSQL0PSI");
        co("KM.Report");
        a(BSKR0SUP.class, "BSKR0SUP");
        a(BSKR1SUP.class, "BSKR1SUP");
        a(BSKR2SUP.class, "BSKR2SUP");
        a(BSLR0PI.class, "BSLR0PI");
        a(BSLR1PI.class, "BSLR1PI");
        a(BSLR2PI.class, "BSLR2PI");
        a(BSLR0PID.class, "BSLR0PID");
        a(BSMR0PY.class, "BSMR0PY");
        a(BSQR0PSI.class, "BSQR0PSI");
        a(BSQR0PSID.class, "BSQR0PSID");
        cc();
        cc();

        co("N");
        a(BSNL0POR.class, "BSNL0POR");
        a(BSNR0POR.class, "BSNR0POR");
        co("N.Report");
        a(BSNR2POR.class, "BSNR2POR");
        a(BSNR3POR.class, "BSNR3POR");
        a(BSNR2PORD.class, "BSNR2PORD");
        a(BSNR3PORD.class, "BSNR3PORD");
        cc();
        cc();

        co("O");
        a(BSOL0PIV.class, "BSOL0PIV");
        a(BSOR0PIV.class, "BSOR0PIV");
        co("O.Report");
        a(BSOR2PIV.class, "BSOR2PIV");
        a(BSOR3PIV.class, "BSOR3PIV");
        a(BSOR2PIVD.class, "BSOR2PIVD");
        a(BSOR3PIVD.class, "BSOR3PIVD");
        cc();
        cc();
        super.closeRole();
        super.openRole("BSAM0.role.finance.title", "BSAM0.role.finance.description");
        co("P.Qtt");
        a(BSPR0QTT.class, "BSPR0QTT");
        a(BSPR1QTT.class, "BSPR1QTT");
        cc();
        co("P.Inv");
        a(BSPR0INV.class, "BSPR0INV");
        a(BSPR1INV.class, "BSPR1INV");
        cc();
        co("P.Rcp");
        a(BSPR0RCP.class, "BSPR0RCP");
        a(BSPR1RCP.class, "BSPR1RCP");
        cc();
        co("P.Por");
        a(BSPR0POR.class, "BSPR0POR");
        a(BSPR1POR.class, "BSPR1POR");
        cc();
        co("P.Piv");
        a(BSPR0PIV.class, "BSPR0PIV");
        a(BSPR1PIV.class, "BSPR1PIV");
        cc();
        super.closeRole();
    }

    @Override
    public Class<? extends MenuForm> getMenuForm() {
        return BSAM1.class;
    }

    @Override
    public String getFormTitle() {
        return "BSAM0.title";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
