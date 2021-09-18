/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.B;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.info.AboutApplicationForm;
import com.gqrsoft.g5.developer.info.AboutSystemPropertiesForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.pfa.AU.PFAL0USR;
import com.mysoftwarehouse.pfa.AA.PFAT0EULA;
import com.mysoftwarehouse.pfa.C.PFCL0ACCM;
import com.mysoftwarehouse.pfa.C.PFCL0ACCY;
import com.mysoftwarehouse.pfa.C.PFCL1ACCY;
import com.mysoftwarehouse.pfa.C.PFCL2ACCY;
import com.mysoftwarehouse.pfa.D.PFDL0TXNT;
import com.mysoftwarehouse.pfa.E.PFER0ACC;
import com.mysoftwarehouse.pfa.E.PFER0TXN;
import com.mysoftwarehouse.pfa.E.PFER0TXNE;
import com.mysoftwarehouse.pfa.E.PFER1ACC;
import com.mysoftwarehouse.pfa.F.PFFR0ACC;
import com.mysoftwarehouse.pfa.F.PFFR0ACCM;
import com.mysoftwarehouse.pfa.F.PFFR1ACC;
import com.mysoftwarehouse.pfa.F.PFFR2ACC;
import com.mysoftwarehouse.pfa.G.PFGE0ACC;
import com.mysoftwarehouse.pfa.G.PFGL0ACC;
import com.mysoftwarehouse.pfa.G.PFGL0ACCT;
import com.mysoftwarehouse.pfa.AU.PFAS1USR;
import com.mysoftwarehouse.pfa.C.PFCL0TXN;
import com.mysoftwarehouse.pfa.C.PFCL1TXN;
import com.mysoftwarehouse.pfa.C.PFCL3ACCY;
import com.mysoftwarehouse.pfa.D.PFDL0TXN;
import com.mysoftwarehouse.pfa.D.PFDL1TXN;
import com.mysoftwarehouse.pfa.D.PFDL2TXN;
import com.mysoftwarehouse.pfa.conf.RESOURCE;
import com.mysoftwarehouse.pfa.data.PFCFG;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.D.PFDL0TXNT2;
import java.net.MalformedURLException;
import java.net.URL;
import javax.jnlp.UnavailableServiceException;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFBM0 extends MenuForm {

    private void co(String n) {
        String title = "PFBM0." + n + ".title";
        String description = "PFBM0." + n + ".description";
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
        a(action, "PFBM0." + action);
    }

    private void a(String action, String n) {
        String title = n + ".title";
        String description = n + ".description";
        super.addApplication(action, title, description);
    }

    @Override
    public void build() {
        co("File");
        a(PFBE0CFG.class, "PFBE0CFG");
        super.addSeparator();
        a("closeProfile");
        super.addSeparator();
        a("closeApp");
        cc();

        co("View");
        for (PFCFG.MainListType m : PFCFG.MainListType.values()) {
            a(m.formClass, m.formCode);
        }
        cc();
        co("Transactions");
        a(PFDL0TXN.class, "PFDL0TXN", false);
        a(PFDL1TXN.class, "PFDL1TXN", false);
        a(PFDL2TXN.class, "PFDL2TXN", false);
        a(PFCL0TXN.class, "PFCL0TXN", false);
        a(PFCL1TXN.class, "PFCL1TXN", false);

        cc();

        co("Reports");
        a(PFER0ACC.class, "PFER0ACC");
        a(PFER1ACC.class, "PFER1ACC");
        a(PFER0TXNE.class, "PFER0TXNE");
        a(PFER0TXN.class, "PFER0TXN");
        cc();

        co("AnalysisReports");
        a(PFFR0ACC.class, "PFFR0ACC");
        a(PFFR0ACCM.class, "PFFR0ACCM");
        a(PFFR1ACC.class, "PFFR1ACC");
        a(PFFR2ACC.class, "PFFR2ACC");
        cc();

        co("Advance");
        a(PFGE0ACC.class, "PFGE0ACC", false); //renameAcc
        //a("recalcAcc", "PFGP0TXN");
        a(PFGL0ACC.class, "PFGL0ACC", false); //sample account
        a(PFGL0ACCT.class, "PFGL0ACCT", false); //import accounts
        super.addSeparator();
        a(PFDL0TXNT.class, "PFDL0TXNT", false); //import transactions
        a(PFDL0TXNT2.class, "PFDL0TXNT2", false); //import delete transactions
        cc();

        co("User");
        a(PFAS1USR.class, "PFAS1USR");
        cc();

        co("Help");
        a(AboutApplicationForm.class, "PFBM0.AboutApplication");
        a(PFAT0EULA.class, "PFAT0EULA");
        //a(PFHH0EULA.class, "PFHH0EULA");
        super.addSeparator();
        a("pfa.website");
        //a("pfa.whatnew");
        //a("pfa.forum");
        super.addSeparator();

        a(AboutSystemPropertiesForm.class, "PFBM0.AboutSystem");
        //Database Summary
        //a(PFHH0DB.class, "PFHH0DB");
        //a(PFHP0DBCHECK.class, "PFHP0DBCHECK");//database checking
        cc();
    }

    @Override
    public void afterFormExecute(Class<? extends UserFormInterface> form) {
        if (form == PFGL0ACC.class) {
            cmd.form.broadcastSignal(MenuSignal.REFRESH, true);
        } else if (form == PFCL0ACCY.class) {
            cmd.form.broadcastSignal(MenuSignal.TERMINATE);
        } else if (form == PFCL0ACCM.class) {
            cmd.form.broadcastSignal(MenuSignal.TERMINATE);
        } else if (form == PFCL1ACCY.class) {
            cmd.form.broadcastSignal(MenuSignal.TERMINATE);
        } else if (form == PFCL2ACCY.class) {
            cmd.form.broadcastSignal(MenuSignal.TERMINATE);
        } else if (form == PFCL3ACCY.class) {
            cmd.form.broadcastSignal(MenuSignal.TERMINATE);
        } else if (form == PFGE0ACC.class) { //rename account
            cmd.form.broadcastSignal(MenuSignal.REFRESH, true);
        } else if (form == PFGL0ACCT.class) { //import account
            cmd.form.broadcastSignal(MenuSignal.REFRESH, true);
        } else if (form == PFDL0TXNT.class) { //import transaction
            cmd.form.broadcastSignal(MenuSignal.REFRESH, true);
        } else if (form == PFDL0TXNT2.class) { //import delete transaction
            cmd.form.broadcastSignal(MenuSignal.REFRESH, true);
        }
    }

    @Override
    public void beforeFormExecute(Class<? extends UserFormInterface> form) {
        if (form == PFGE0ACC.class) { //rename account
            cmd.form.broadcastSignal(MenuSignal.SENDSELECTED, true); //kena blocking
            cmd.out.map.texts.put(ACC.FROMACC,
                    cmd.local.map.texts.get(ACC.FROMACC));
        } else if (form == PFDL0TXN.class ||
                form == PFDL1TXN.class ||
                form == PFDL2TXN.class 
                ) {
            cmd.form.broadcastSignal(MenuSignal.SENDSELECTED, true); //kena blocking
            cmd.out.map.texts.put(ACC.PFACC_ACC, 
                    cmd.local.map.texts.get(ACC.FROMACC));
        }
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if ("closeProfile".equals(name)) {
            cmd.form.closeAllWindowsButMe();
            cmd.form.executeInNewThread(new PFAL0USR());
            cmd.form.closeForm();
        } else if ("closeApp".equals(name)) {
            cmd.form.closeApplication();
        } else if ("pfa.website".equals(name)) {
            goUrl(RESOURCE.MENU_WEBSITE_URL);
//        } else if ("pfa.whatnew".equals(name)) {
//            goUrl(RESOURCE.MENU_WHATNEW_URL);
//        } else if ("pfa.forum".equals(name)) {
//            goUrl(RESOURCE.MENU_FORUM_URL);

//        } else if ("recalcAcc".equals(name)) {
//            cmd.form.broadcastSignal(1, true); //kena blocking
//            cmd.out.map.texts.clear();
//            cmd.out.map.texts.putAll(cmd.local.map.texts);
//            UserFormInterface f = cmd.form.create(PFGP0TXN.class);
//            cmd.form.execute(f);
        }
    }

    private void goUrl(String url) {
        try {
            cmd.jnlp.showWebDocument(new URL(url));
        } catch (UnavailableServiceException ex) {
            String title = "PFBM0.error";
            cmd.log.severe(title, ex);
            title = cmd.lang.getString(title);
            String message = "PFBM0.error.URL.{0}";
            message = cmd.lang.getString(message, url);
            cmd.common.showMessage(DialogMessageType.ERROR, title,
                    message);
        } catch (MalformedURLException ex) {
            String title = "PFBM0.error";
            cmd.log.severe(title, ex);
        }

    }

    @Override
    public String getFormTitle() {
        return "";
    }

    @Override
    public void onEscapeKeyPressed() {
    }
}
