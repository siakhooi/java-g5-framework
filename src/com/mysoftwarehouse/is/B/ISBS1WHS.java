/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.is.B;

import com.gqrsoft.g5.developer.publicobject.FormEnum.EnterType;
import com.mysoftwarehouse.is.data.MAP;
import com.mysoftwarehouse.is.data.GET;

/**
 *
 * @author Ng Siak Hooi
 */
public class ISBS1WHS extends ISBS0WHS {

    @Override
    public void onInEnter() {
        String whs = GET.Whs(this);
        cmd.in.map.texts.put(MAP.ISWHS.WHS, whs);
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
        return "ISBS1WHS.title";
    }

    @Override
    public void saveEdit() throws Exception {
        super.saveEdit();
        String CstTyp = cmd.entry.getString("CstTyp");
        cmd.global.texts.put(MAP.ISWHS.CSTTYP, CstTyp);
    }
}
