/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.AU;

import com.gqrsoft.g5.developer.publicobject.FormEnum.EnterType;
import com.mysoftwarehouse.pfa.AU.PFAS0USR;
import com.mysoftwarehouse.pfa.db.USR.USR;

/**
 *
 * @author Ng Siak Hooi
 */
public class PFAS1USR extends PFAS0USR {

    @Override
    public void onInEnter() {
        String id = cmd.global.texts.get(USR.PFUSR_ID);
        cmd.in.map.texts.put(USR.PFUSR_ID, id);
        cmd.in.formEnterType = EnterType.LISTVIEW;
    }

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
        super.buttonConfig.reload = null;
        super.buttonConfig.add = null;
        //super.buttonConfig.edit = null;
        super.buttonConfig.cancel = null;
        super.buttonConfig.back = null;
        super.buttonConfig.copy = null;
        super.buttonConfig.delete = null;
        super.buttonConfig.reset = null;
        super.buttonConfig.view = null;
    }

    @Override
    public String getFormTitle() {
        return "PFHS0USR.title";
    }

    @Override
    public void saveEdit() throws Exception {
        super.saveEdit();
    }
}
