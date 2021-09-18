/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.A;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.is.C.ISCL0ITM;
import com.mysoftwarehouse.is.C.ISCR0ITM;
import com.mysoftwarehouse.is.C.ISCR0ITMB;
import com.mysoftwarehouse.is.C.ISCR1ITMB;
import com.mysoftwarehouse.is.D.ISDL0UOM;
import com.mysoftwarehouse.is.D.ISDR0UOM;
import com.mysoftwarehouse.is.E.ISEL0LOC;
import com.mysoftwarehouse.is.E.ISER0LOC;
import com.mysoftwarehouse.is.F.ISFL0TCD;
import com.mysoftwarehouse.is.F.ISFR0TCD;
import com.mysoftwarehouse.is.G.ISGE0TXN;
import com.mysoftwarehouse.is.G.ISGE1TXN;
import com.mysoftwarehouse.is.G.ISGE2TXN;
import com.mysoftwarehouse.is.G.ISGE3TXN;
import com.mysoftwarehouse.is.G.ISGR0TXN;
import com.mysoftwarehouse.is.G.ISGR0TXNA;
import com.mysoftwarehouse.is.G.ISGR1TXN;
import com.mysoftwarehouse.is.H.ISHL0ITM;
import com.mysoftwarehouse.is.H.ISHR0ITM;
import com.mysoftwarehouse.is.I.ISIL0ITM;
import com.mysoftwarehouse.is.I.ISIR0ITM;
import com.mysoftwarehouse.is.conf.DONGLE;
import com.mysoftwarehouse.is.db.DB.LICENSE;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISAM0 extends MenuForm {

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return ISAI1.class;
    }

    private void co(String n) {
        String title = "ISAM0." + n + ".title";
        String description = "ISAM0." + n + ".description";
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
        a(action, "ISAM0." + action);
    }

    private void a(String action, String n) {
        String title = n + ".title";
        String description = n + ".description";
        super.addApplication(action, title, description);
    }

    @Override
    public void build() {
        //super.openRole("ISAM0.role.master.title", "ISAM0.role.masterr.description");
        co("CF");
        a(ISCL0ITM.class, "ISCL0ITM"); //customer info
        a(ISDL0UOM.class, "ISDL0UOM");
        a(ISEL0LOC.class, "ISEL0LOC");
        a(ISFL0TCD.class, "ISFL0TCD");
        co("CF.Report");
        a(ISCR0ITM.class, "ISCR0ITM");
        a(ISDR0UOM.class, "ISDR0UOM");
        a(ISER0LOC.class, "ISER0LOC");
        a(ISFR0TCD.class, "ISFR0TCD");
        cc();
        cc();

        co("G");
        a(ISGE0TXN.class, "ISGE0TXN");
        a(ISGE1TXN.class, "ISGE1TXN");
        a(ISGE2TXN.class, "ISGE2TXN");
        a(ISGE3TXN.class, "ISGE3TXN");
        co("G.Report");
        a(ISCR0ITMB.class, "ISCR0ITMB");
        a(ISCR1ITMB.class, "ISCR1ITMB");
        a(ISGR0TXN.class, "ISGR0TXN");
        a(ISGR1TXN.class, "ISGR1TXN");
        a(ISGR0TXNA.class, "ISGR0TXNA");
        cc();
        cc();

        co("H");
        a(ISHL0ITM.class, "ISHL0ITM");
        co("H.Report");
        a(ISHR0ITM.class, "ISHR0ITM");
        cc();
        cc();

        co("I");
        a(ISIL0ITM.class, "ISIL0ITM");
        //a(ISIP0ITM.class, "ISIP0ITM");
        co("I.Report");
        a(ISIR0ITM.class, "ISIR0ITM");
        cc();
        cc();

    //super.closeRole();
    }

    @Override
    public void beforeFormExecute(Class<? extends UserFormInterface> arg0) {
        if (DONGLE.isDemoMode(this)) {
            LICENSE.check(this);
        }
    }

    @Override
    public Class<? extends MenuForm> getMenuForm() {
        return ISAM1.class;
    }

    @Override
    public String getFormTitle() {
        return "ISAM0.title";
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
