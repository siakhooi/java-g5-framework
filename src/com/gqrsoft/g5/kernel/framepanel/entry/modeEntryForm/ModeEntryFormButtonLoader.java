/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.modeEntryForm;

import com.gqrsoft.g5.developer_protected.tools.ButtonsBuilder;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum;
import com.gqrsoft.g5.developer_protected.tools.ButtonConfig;
import com.gqrsoft.g5.kernel.framepanel.button.G5JButton;

/**
 *
 * @author Ng Siak Hooi
 */
public class ModeEntryFormButtonLoader {

    public static void load(ButtonsBuilder sb, ButtonConfig conf) {
//        CONSOLE.println("modeEntryFormButtonLoader.ButtonConfiguration: " + conf.name());
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Search, conf.search);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Add, conf.add);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Copy, conf.copy);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Edit, conf.edit);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.View, conf.view);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.OK, conf.ok);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Cancel, conf.cancel);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Reload, conf.reload);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Reset, conf.reset);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Delete, conf.delete);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Back, conf.back);
        setEnabled(sb, ModeEntryFormEnum.ModeSystemButton.Close, conf.close);
    }

    private static void setEnabled(ButtonsBuilder sb,
            ModeEntryFormEnum.ModeSystemButton msb, ModeEntryFormEnum.ModeAction ma) {
//        CONSOLE.println(msb.name);
//        try {
//            CONSOLE.println(msb.name + ":" + ma.name());
//        } catch (Exception e) {
//            CONSOLE.println("error: " + e.getLocalizedMessage());
//        }
        G5JButton c = sb.allButtonsByName.get(msb.name);
        boolean enabled = false;
        if (ma != null) {
            if (ma != ModeEntryFormEnum.ModeAction.None) {
                enabled = true;
            }
        }

        c.setEnabled(enabled);
        c.setModeAction(ma);
    }
}
