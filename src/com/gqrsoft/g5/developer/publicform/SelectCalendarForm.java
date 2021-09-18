/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer.publicform;

import com.gqrsoft.g5.developer.form.BlankForm;
import com.gqrsoft.g5.kernel.config.MenuSignal;
import java.awt.BorderLayout;
import java.util.Calendar;
import javax.swing.JPanel;
import org.freixas.jcalendar.JCalendar;

/**
 * usage: 
 * set cmd.out.calendarValue<BR>
 * return from cmd.out.calendarValue
 * @author Ng Siak Hooi
 */
public class SelectCalendarForm extends BlankForm {

    private JCalendar calendarChooser;

    @Override
    public final String getFormI18nTitle() {
        if (cmd.in.i18nTitle == null) {
            String title = "SelectCalendarForm.title";
            return cmd.lang.getSystemString(title);
        } else {
            return cmd.in.i18nTitle;
        }
    }

//    @Override
//    public String getFormLevelLocaleBaseName() {
//        return cmd.parent.getFormLevelLocaleBaseName();
//    }
    @Override
    public void buildBlankForm(JPanel parent) {
        calendarChooser =
                new JCalendar(
                JCalendar.DISPLAY_DATE |
                JCalendar.DISPLAY_TIME,
                true);
        Calendar initValue = cmd.in.calendarValue;
        if (initValue == null) {
            calendarChooser.setDate(cmd.cal.getNow().getTime());
        } else {
            calendarChooser.setDate(initValue.getTime());
        }

        ActionForm_OKCancel af = new ActionForm_OKCancel();
        JPanel a1 = cmd.form.createEmbedded(af);
        parent.setLayout(new BorderLayout());
        parent.add(calendarChooser, BorderLayout.CENTER);
        parent.add(a1, BorderLayout.PAGE_END);

//        String title;
//        title = "SelectCalendarForm.button.OK";
//        title = cmd.lang.getSystemString(title);
//        super.buttons.addI18nButton("OK", title);
//        title = "SelectCalendarForm.button.Cancel";
//        title = cmd.lang.getSystemString(title);
//        super.buttons.addI18nButton("Cancel", title);
    }

    @Override
    public void receiveSystemSignal(int signalNumber, String signalCode) {
        if (signalNumber == MenuSignal.BUTTON) {
            if (signalCode.equals(ActionForm_OKCancel.BUTTON_OK)) {
                cmd.in.calendarValue = calendarChooser.getCalendar();
                cmd.form.closeForm();
            } else if (signalCode.equals(ActionForm_OKCancel.BUTTON_CANCEL)) {
                cmd.in.calendarValue = null;
                cmd.form.closeForm();
            }
        }
    }

//    @Override
//    public void buttonClick(String name) {
//        if (name.equals("OK")) {
//            cmd.in.calendarValue = calendarChooser.getCalendar();
//        } else {
//            cmd.in.calendarValue = null;
//        }
//        cmd.form.closeForm();
//    }
    @Override
    public void onEscapeKeyPressed() {
        cmd.in.calendarValue = null;
        cmd.form.closeForm();
    }

    @Override
    public String getFormTitle() {
        return null;
    }
}
