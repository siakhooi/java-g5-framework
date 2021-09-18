/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.publicobject.FormEnum.EnterType;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.data.GET;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBS1CMP extends BSBS0CMP {

    @Override
    public void onInEnter() {
        String cmp = GET.Cmp(this);
        cmd.in.map.texts.put(MAP.BSCMP.CMP, cmp);
        cmd.in.formEnterType = EnterType.LISTVIEW;
    }

    @Override
    public void initModeAction() {
        super.buttonConfig.search = null;
        super.buttonConfig.reload = null;
        super.buttonConfig.add = null;
        super.buttonConfig.back = null;
        super.buttonConfig.copy = null;
        super.buttonConfig.delete = null;
        super.buttonConfig.reset = null;
        super.buttonConfig.view = null;
    }

    @Override
    public String getFormTitle() {
        return "BSBS1CMP.title";
    }

    @Override
    public void saveEdit() throws Exception {
        super.saveEdit();
        String CurCde = cmd.entry.getString("CurCde");
        cmd.global.texts.put(MAP.BSCMP.CURCDE, CurCde);
    }
}
