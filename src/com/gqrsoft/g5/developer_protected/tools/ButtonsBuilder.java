/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.tools;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum;
import com.gqrsoft.g5.developer.publicobject.FunctionKeyEnum;
import com.gqrsoft.g5.developer.publicobject.ModeEntryFormEnum;
import com.gqrsoft.g5.developer_secret.tools.AbstractButtonsBuilder;
import com.gqrsoft.g5.kernel.framepanel.button.G5JButton;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 *
 * @author Ng Siak Hooi
 */
public class ButtonsBuilder extends AbstractButtonsBuilder {

    private UserFormInterface form;

    public ButtonsBuilder(UserFormInterface form) {
        this.form = form;
    }

    public final void clearAll() {
        allComponents.clear();
        allButtonsByName.clear();
        currentButton = null;
        defaultButton = null;
    }

    /**
     * currently not working due to <code>addGlue()</code>
     * @param t
     * @deprecated 
     */
    @Deprecated
    public final void setButtonLocation(
            FormEnum.ButtonHorizontalLocation t) {
        this.horizontalLocation = t;
    }

    public final void setButtonLocation(
            FormEnum.ButtonPageLocation t) {
        this.verticalLocation = t;
    }

    public final void addButton(String name, String label) {
        //name = form.cmd().lang.getString(name);
        label = form.cmd().lang.getString(label);
        addI18nButton(name, label);
    }

    public final void addI18nButton(String name, String label) {
        addI18nButton(name, label, null, false);
    }

    public final void addButton(String name, String label, boolean defaultButton) {
        //name = form.cmd().lang.getString(name);
        label = form.cmd().lang.getString(label);
        addI18nButton(name, label, defaultButton);
    }

    public final void addI18nButton(String name, String label, boolean defaultButton) {
        addI18nButton(name, label, null, defaultButton);
    }

    public final void addButton(String name, String label, Icon icon, boolean defaultButton) {
        //name = form.cmd().lang.getString(name);
        label = form.cmd().lang.getString(label);
        addI18nButton(name, label, icon, defaultButton);
    }

    public final void addI18nButton(String name, String label, Icon icon, boolean defaultButton) {
        G5JButton jb = new G5JButton(label, icon);
        jb.setName(name);
        allComponents.add(jb);
        allButtonsByName.put(name, jb);
        if (defaultButton) {
            this.defaultButton = jb;
        }
        currentButton = jb;
    }

    @Deprecated
    public final void addGlue() {
        allComponents.add(Box.createHorizontalGlue());
    }

    public final void addSpace(int width) {
        allComponents.add(Box.createHorizontalStrut(width));
    }

    public final void addRigidArea(int width, int height) {
        allComponents.add(
                Box.createRigidArea(new Dimension(width, height)));
    }
    //vertical
    public final void addSeparator() {
        allComponents.add(new JSeparator(SwingConstants.VERTICAL));
    }

    public final void setMnemonic(int mnemonic) {
        currentButton.setMnemonic(mnemonic);
    }

    public final void setToolTipText(String text) {
        text = form.cmd().lang.getString(text);
        setI18nToolTipText(text);
    }

    public final void setI18nToolTipText(String i18nText) {
        currentButton.setToolTipText(i18nText);
    }

    public final void setFocusable(boolean focusable) {
        currentButton.setFocusable(focusable);
    }

    public final void setEnabled(String name, boolean value) {
        allButtonsByName.get(name).setEnabled(value);
    }

    public final void setFunctionKey(FunctionKeyEnum.FunctionKey b) {
        setFunctionKey(b.keyCode, b.modifiers);
    }

    public final void setFunctionKey(int keyCode, int modifiers) {
        currentButton.getInputMap(
                JComponent.WHEN_FOCUSED).put(
                KeyStroke.getKeyStroke(
                keyCode, modifiers),
                currentButton.getName());
        currentButton.getInputMap(
                JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(
                keyCode, modifiers),
                currentButton.getName());
    //actionMap done in ButtonPanelGenerator
    }

    public final void setModeAction(ModeEntryFormEnum.ModeAction ma) {
        currentButton.setModeAction(ma);
    }

    public final void setDisabledIcon(Icon disabledIcon) {
        currentButton.setDisabledIcon(disabledIcon);
    }
//    protected final void setMnemonic(int mnemonic, int index) {
//        currentButton.setMnemonic(mnemonic);
//        currentButton.setDisplayedMnemonicIndex(index);
//    }
// void setHorizontalAlignment(int alignment) 
//  void setHorizontalTextPosition(int textPosition)  
//  void setIcon(Icon defaultIcon)  
//   void setIconTextGap(int iconTextGap) 
//   void setPressedIcon(Icon pressedIcon)  
//   void setRolloverEnabled(boolean b)  
//    void setRolloverIcon(Icon rolloverIcon) 
//     void setRolloverSelectedIcon(Icon rolloverSelectedIcon) 
//      void setVerticalAlignment(int alignment) 
// void setVerticalTextPosition(int textPosition)      
}
