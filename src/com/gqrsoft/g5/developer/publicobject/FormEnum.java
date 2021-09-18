/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicobject;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ng Siak Hooi
 */
public class FormEnum {

    public enum EnterType {

        INIT,
        LISTADD, LISTCOPY, LISTDELETE, LISTEDIT, LISTVIEW,
        FIELDSELECT, FIELDVIEW,
        REPORTVIEW, REPORTSAVEASPDF,
        REPORTQUICKPRINT, REPORTPRINT;
    }

    public enum ButtonPageLocation {

        TOP(BorderLayout.PAGE_START),
        BOTTOM(BorderLayout.PAGE_END);
        public String pageLayoutValue = BorderLayout.PAGE_END;

        ButtonPageLocation(String v) {
            this.pageLayoutValue = v;
        }
    }

    public enum ButtonHorizontalLocation {

        LEFT(FlowLayout.LEADING),
        RIGHT(FlowLayout.TRAILING),
        CENTER(FlowLayout.CENTER);
        public int flowLayoutValue = FlowLayout.CENTER;

        ButtonHorizontalLocation(int v) {
            this.flowLayoutValue = v;
        }
    }

    public enum DialogMessageType {

        ERROR(JOptionPane.ERROR_MESSAGE),
        INFORMATION(JOptionPane.INFORMATION_MESSAGE),
        WARNING(JOptionPane.WARNING_MESSAGE),
        QUESTION(JOptionPane.QUESTION_MESSAGE),
        PLAIN(JOptionPane.PLAIN_MESSAGE);
        public int messageType = JOptionPane.INFORMATION_MESSAGE;

        DialogMessageType(int messageType) {
            this.messageType = messageType;
        }
    }

    public enum DialogOptionType {

        DEFAULT(JOptionPane.DEFAULT_OPTION),
        YES_NO(JOptionPane.YES_NO_OPTION),
        YES_NO_CANCEL(JOptionPane.YES_NO_CANCEL_OPTION),
        OK_CANCEL(JOptionPane.OK_CANCEL_OPTION);
        public int optionType = JOptionPane.DEFAULT_OPTION;

        DialogOptionType(int optionType) {
            this.optionType = optionType;
        }
    }

    public enum FileSelectType {

        FILES_ONLY(JFileChooser.FILES_ONLY),
        FILES_AND_DIRECTORIES(JFileChooser.FILES_AND_DIRECTORIES),
        DIRECTORIES_ONLY(JFileChooser.DIRECTORIES_ONLY);
        public int fileSelectType = JFileChooser.FILES_ONLY;

        FileSelectType(int type) {
            this.fileSelectType = type;
        }
    }

    public enum StyledTextType {

        HTML("text/html"),
        Plain("text/plain"),
        RTF("text/rtf");
        public String styledTextType = "text/plain";

        StyledTextType(String type) {
            this.styledTextType = type;
        }
    }

    public enum FrameState {

        NORMAL(JFrame.NORMAL),
        ICONIFIED(JFrame.ICONIFIED),
        MAXIMIZED_HORIZ(JFrame.MAXIMIZED_HORIZ),
        MAXIMIZED_VERT(JFrame.MAXIMIZED_VERT),
        MAXIMIZED_BOTH(JFrame.MAXIMIZED_BOTH);
        public int frameState;

        FrameState(int frameState) {
            this.frameState = frameState;
        }
    }
}
