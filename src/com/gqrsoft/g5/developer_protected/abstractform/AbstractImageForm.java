/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.abstractform;

import com.gqrsoft.g5.developer.form.ButtonForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Ng Siak Hooi
 */
@Deprecated
public abstract class AbstractImageForm extends ButtonForm {

    private JLabel lblImage = new JLabel();
    private JScrollPane sp;

    /**
     * image to be display in this form.
     * @return
     */
    public abstract Image getImage();

    public abstract Color getBackgroundColor();

    protected abstract boolean hasAction();

    protected abstract void userAction();

    protected boolean useScrollPane() {
        return false;
    }

    protected final void setImage(Image img) {
        if (img == null) {
            lblImage.setIcon(null);
        } else {
            lblImage.setIcon(new ImageIcon(img));
        }
    }

    @Override
    public final void buildButtonForm(JPanel parent) {
        setImage(getImage());
        Color c = getBackgroundColor();
        if (c != null) {
            lblImage.setBackground(c);
            lblImage.setOpaque(true);
        }
        parent.setLayout(new BorderLayout());
        if (useScrollPane()) {
            sp = new JScrollPane(lblImage);
            parent.add(sp, BorderLayout.CENTER);
        } else {
            parent.add(lblImage, BorderLayout.CENTER);
        }
        Icon icon = cmd.icon.getCloseIcon(cmd.icon.getDefaultHeight());
        String title = "ImageForm.button.Close.label";
        title = cmd.lang.getSystemString(title);
        super.buttons.addI18nButton("Close", title, icon, true);
        if (hasAction()) {
            lblImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            lblImage.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    userAction();
                }
            });
        }
    }

    @Override
    public void buttonClick(String name) {
        if ("Close".equals(name)) {
            cmd.form.closeForm();
        }
    }

    @Override
    public void onEscapeKeyPressed() {
        cmd.form.closeForm();
    }
}
