/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.frame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author Ng Siak Hooi
 */
public class WindowKeySetter {

    public static void setupEscapeKey(final FrameInterface frame, JComponent jf) {
        Action escAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getFormControl().panel.onEscapeKeyPressed();
            }
        };
//        Action enterAction=new AbstractAction(){
//            public void actionPerformed(ActionEvent e){
//                frame.getPanel().onEnterKeyPressed();
//            }
//        };
        jf.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "doEscapeKey");
        jf.getInputMap(JComponent.WHEN_FOCUSED).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "doEscapeKey");
        jf.getActionMap().put("doEscapeKey", escAction);
//        jf.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
//                put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "doEnterKey");
//        jf.getInputMap(JComponent.WHEN_FOCUSED).
//                put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "doEnterKey");
//        jf.getActionMap().put("doEnterKey", enterAction);
    }
}
