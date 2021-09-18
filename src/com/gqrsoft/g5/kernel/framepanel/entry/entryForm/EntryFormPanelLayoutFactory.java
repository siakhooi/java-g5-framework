/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.entry.entryForm;

import com.gqrsoft.g5.developer.form.EntryForm;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Ng Siak Hooi
 */
public class EntryFormPanelLayoutFactory {

    public static void execute(JPanel parent, JTabbedPane jtp, EntryForm form) {
        if (parent == null) {
            return;
        }
        //parent.setBorder(new LineBorder(Color.RED, 2));
        parent.setLayout(new BorderLayout());
        parent.add(form.keyEntryFieldGroup.generatedPanel, BorderLayout.PAGE_START);
        if (jtp == null) {
            return;
        }
        for (String name : form.allTab.keySet()) {
            EntryFieldGroup efg = form.allTab.get(name);
            jtp.addTab(efg.tabI18nDescription, efg.generatedPanel);
        }
        parent.add(jtp, BorderLayout.CENTER);
    }
}
