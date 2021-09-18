/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.A;

import com.gqrsoft.g5.developer.form.MenuForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.info.AboutApplicationForm;
import com.gqrsoft.g5.developer.info.AboutSystemPropertiesForm;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.mysoftwarehouse.is.B.ISBL0WHS;
import com.mysoftwarehouse.is.B.ISBR0WHS;
import com.mysoftwarehouse.is.B.ISBS1WHS;
import com.mysoftwarehouse.is.conf.INI;
import com.mysoftwarehouse.is.conf.RESOURCE;
import java.net.MalformedURLException;
import java.net.URL;
import javax.jnlp.UnavailableServiceException;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISAM1 extends MenuForm {

    private void co(String n) {
        String title = "ISAM1." + n + ".title";
        String description = "ISAM1." + n + ".description";
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
        a(action, "ISAM1." + action);
    }

    private void a(String action, String n) {
        String title = n + ".title";
        String description = n + ".description";
        super.addApplication(action, title, description);
    }

    @Override
    public void build() {
        co("File");
        a(ISBS1WHS.class, "ISBS1WHS");
        super.addSeparator();
        a("changeLanguage");
        super.addSeparator();
        a("closeWarehouse");
        super.addSeparator();
        a("closeApp");
        cc();
        co("Report");
        a(ISBR0WHS.class, "ISBR0WHS");
        cc();
        co("Help");
        a(AboutApplicationForm.class, "ISAM1.AboutApplication");
        a(AboutSystemPropertiesForm.class, "ISAM1.AboutSystem");
        a(ISAT0EULA.class, "ISAT0EULA");
//        super.addSeparator();
//        a(BSAE0REG.class, "BSAE0REG");
        super.addSeparator();
        a("is.website");
        a("is.whatnew");
        a("is.forum");
        cc();
    }

    @Override
    public void afterFormExecute(Class<? extends UserFormInterface> form) {
//        if (form == PFGL0ACC.class) {
//            cmd.form.broadcastSignal(MenuSignal.REFRESH, true);
//        } else if (form == PFCL0ACCY.class) {
//            cmd.form.broadcastSignal(MenuSignal.TERMINATE);
//        } else if (form == PFCL0ACCM.class) {
//            cmd.form.broadcastSignal(MenuSignal.TERMINATE);
//        } else if (form == PFCL1ACCY.class) {
//            cmd.form.broadcastSignal(MenuSignal.TERMINATE);
//        } else if (form == PFCL2ACCY.class) {
//            cmd.form.broadcastSignal(MenuSignal.TERMINATE);
//        } else if (form == PFGE0ACC.class) { //rename account
//            cmd.form.broadcastSignal(MenuSignal.REFRESH, true);
//        } else if (form == PFGL0ACCT.class) { //import account
//            cmd.form.broadcastSignal(MenuSignal.REFRESH, true);
//        } else if (form == PFDL0TXNT.class) { //import transaction
//            cmd.form.broadcastSignal(MenuSignal.REFRESH, true);
//        }
    }

    @Override
    public void beforeFormExecute(Class<? extends UserFormInterface> form) {
//        if (form == PFGE0ACC.class) { //rename account
//            cmd.form.broadcastSignal(MenuSignal.SENDSELECTED, true); //kena blocking
//            cmd.out.map.texts.put(ACC.FROMACC,
//                    cmd.local.map.texts.get(ACC.FROMACC));
//        }
    }

    @Override
    public void commandActivated(String name, String title, String description) {
        if ("closeWarehouse".equals(name)) {
            cmd.form.closeAllWindowsButMe();
            cmd.form.executeInNewThread(new ISBL0WHS());
            cmd.form.closeForm();
        } else if ("changeLanguage".equals(name)) {
            cmd.lang.selectLanguage();
            String s = cmd.lang.getCurrentLanguageCode();
            INI.writeLanguage(this, s);
            UserFormInterface f = cmd.form.create(ISAM0.class);
            cmd.form.executeInNewThread(f);
            cmd.form.closeForm();
        } else if ("closeApp".equals(name)) {
            cmd.form.closeApplication();
        } else if ("is.website".equals(name)) {
            goUrl(RESOURCE.MENU_WEBSITE_URL);
        } else if ("is.whatnew".equals(name)) {
            goUrl(RESOURCE.MENU_WHATNEW_URL);
        } else if ("is.forum".equals(name)) {
            goUrl(RESOURCE.MENU_FORUM_URL);

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
            String title = "ISAM1.error";
            cmd.log.severe(title, ex);
            title = cmd.lang.getString(title);
            String message = "ISAM1.error.URL.{0}";
            message = cmd.lang.getString(message, url);
            cmd.common.showMessage(DialogMessageType.ERROR, title,
                    message);
        } catch (MalformedURLException ex) {
            String title = "ISAM1.error";
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
