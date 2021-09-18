/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.tools;

import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum.ModeAction;

/**
 *
 * @author Ng Siak Hooi
 */
public class ButtonConfig {

    public ModeAction search;
    public ModeAction add;
    public ModeAction copy;
    public ModeAction edit;
    public ModeAction view;
    public ModeAction ok;
    //
    public ModeAction cancel;
    public ModeAction reload;
    public ModeAction reset;
    public ModeAction delete;
    public ModeAction back;
    public ModeAction close;
    // 
    public ModeAction escapeKey;

    public void load(ModeEntryFormEnum.ButtonConfiguration bc) {
        search = bc.x().search;
        add = bc.x().add;
        copy = bc.x().copy;
        edit = bc.x().edit;
        view = bc.x().view;
        ok = bc.x().ok;
        cancel = bc.x().cancel;
        reload = bc.x().reload;
        reset = bc.x().reset;
        delete = bc.x().delete;
        back = bc.x().back;
        close = bc.x().close;
        escapeKey=bc.x().escapeKey;
    }
}
