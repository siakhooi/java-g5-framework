/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.framepanel.button;

import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Ng Siak Hooi
 */
public class G5JButton extends JButton {

    private ModeEntryFormEnum.ModeAction modeAction;
//    private String label;
//    private Icon icon;
    public G5JButton(String label, Icon icon) {
        super(label, icon);
        super.setVerticalTextPosition(JButton.BOTTOM);
        super.setHorizontalTextPosition(JButton.CENTER);
//        this.label=label;
//        this.icon=icon;
    }
//    public void setEnability(boolean value){
//        if(value){
//            super.setText(label);
//            super.setIcon(icon);
//        }else{
//            super.setText("");
//            super.setIcon(null);
//        }
//        super.setEnabled(value);
//    }
    public void setModeAction(ModeEntryFormEnum.ModeAction ma) {
        this.modeAction = ma;
    }

    public ModeEntryFormEnum.ModeAction getModeAction() {
        return this.modeAction;
    }
}
