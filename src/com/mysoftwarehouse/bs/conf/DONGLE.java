/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.conf;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.mysoftwarehouse.rockey.DongleControl;

/**
 * @author Ng Siak Hooi
 */
public class DONGLE {
//    public static boolean aisDemoMode(UserFormInterface form) {
//        return false;
//    }
    public static boolean isDemoMode(UserFormInterface form) {
        try {
            return !DongleControl.hasValidDongle(form);
        } catch (NoClassDefFoundError ex2) {
            form.cmd().log.sysSevere("DONGLE", ex2);
            return true;
        } catch (UnsatisfiedLinkError ex) {
            form.cmd().log.sysSevere("DONGLE", ex);
            return true;
        } catch (Exception e) {
            form.cmd().log.sysSevere("DONGLE", e);
            return true;
        }
    }
}
