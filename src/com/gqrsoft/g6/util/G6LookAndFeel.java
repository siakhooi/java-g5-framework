/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author SHNG
 */
public class G6LookAndFeel {

    public void updateWindows(JFrame jf) {
        SwingUtilities.updateComponentTreeUI(jf);
    }
    public void updateWindows(JDialog jd) {
        SwingUtilities.updateComponentTreeUI(jd);
    }

    public void useLookAndFeel(String platform) throws ClassNotFoundException, IllegalAccessException, InstantiationException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(platform);
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
