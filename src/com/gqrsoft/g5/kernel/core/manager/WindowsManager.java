/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.kernel.core.manager;

import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.kernel.core.AbstractCoreComponent;
import com.gqrsoft.g5.kernel.frame.FrameInterface;
import java.util.Vector;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ng Siak Hooi
 */
public class WindowsManager extends AbstractCoreComponent {

    private int number_of_windows = 0;
    private Vector<FrameInterface> allFrame = new Vector<FrameInterface>();

    public void LookAndFeelChanged() {
        for (FrameInterface f : allFrame) {
            if (f.isJFrame()) {
                SwingUtilities.updateComponentTreeUI(f.getJFrame());
            } else if (f.isJDialog()) {
                SwingUtilities.updateComponentTreeUI(f.getJDialog());
            }
        }
    }
    private boolean checkLastWindows = true;

    public void closeAllWindowsButMe(FrameInterface frame) {
        FrameInterface rootFrame = frame.getFormControl().threadControl.root;
        while (allFrame.size() > 1) {
            FrameInterface f = allFrame.firstElement();
            if (f == rootFrame) {
                f = allFrame.elementAt(1);
            }
            f.close();
        }
    }

    public void setLastWindowsCheck(boolean checkLastWindows) {
        this.checkLastWindows = checkLastWindows;
    }

    public void addFrame(FrameInterface frame) {
        number_of_windows++;
        allFrame.add(frame);
    }

    public void closeFrame(FrameInterface frame) {
        number_of_windows--;
        allFrame.remove(frame);
        if (checkLastWindows && number_of_windows == 0) {
            core().exit();
        } //no more frame;
    }

    public boolean confirmExitFrame(FrameInterface frame) {
        if (checkLastWindows && number_of_windows == 1) {
            String i18nTitle = "WindowsManager.ExitConfirmation.Title";
            i18nTitle = core().lang.getSystemString(i18nTitle);
            String message = "WindowsManager.ExitConfirmation.Message";
            message = core().lang.getSystemString(message);
            boolean i = frame.getFormControl().cmd.common.showI18nConfirmation(
                    DialogMessageType.WARNING, i18nTitle, message);
            return i;
        }
        return true;
    }
}
