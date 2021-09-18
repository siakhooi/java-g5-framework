/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.developer_protected.commands.util;

import com.gqrsoft.g5.developer_secret.commands.AbstractCommandComponent;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ng Siak Hooi
 */
public class LookAndFeelCommands extends AbstractCommandComponent {

    public void useLookAndFeel(String platform) throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel(platform);
        getFormControl().core.win.LookAndFeelChanged();
    }

    public void useCrossPlatform() throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {
        useLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }

    public void useSystem() throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {
        useLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    public void useWindows() throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {
        String plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        useLookAndFeel(plaf);
    }

    public void useWindowsClassic() throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {
        String plaf = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
        useLookAndFeel(plaf);
    }

    public void useMetal() throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {
        String plaf = "javax.swing.plaf.metal.MetalLookAndFeel";
        useLookAndFeel(plaf);

    }

    public void useMotif() throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {
        String plaf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        useLookAndFeel(plaf);
    }
}
