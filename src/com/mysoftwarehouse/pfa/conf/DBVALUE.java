/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.conf;

/**
 *
 * @author Ng Siak Hooi
 */
public class DBVALUE {

    public static int NO_TXNID = -1;
    //
    public static int SAMPLE_ACCOUNT_MAX_TRY = 1000;
    public static String SAMPLE_ACCOUNT_SEPARATOR = "_";
    //
    public static String DEFAULT_MAIN_ACCOUNT_LIST_TYPE = "A";
    //
    public static String DEFAULT_CAPITAL_ACCOUNT = "CAPITAL_00";
    public static String DEFAULT_CAPITAL_ACCOUNT_NAME = "Account Balance B/F";
    //
    public static String DEFAULT_INCOME_ACCOUNT = "INCOME_00";
    public static String DEFAULT_INCOME_ACCOUNT_NAME = "Income Of Current Period";
    //
    public static String DEFAULT_RETAINED_INCOME_ACCOUNT = "RETAINED_INCOME_00";
    public static String DEFAULT_RETAINED_INCOME_ACCOUNT_NAME = "Incomes of Previous Period";
    //
    public static String DEFAULT_DEFAULT_RECON_ACCOUNT = "EXP-MISC-RECON";
    public static String DEFAULT_DEFAULT_RECON_ACCOUNT_NAME = "Misc Expenses (Recon)";
    //
    public static String ACCOUNT_BAL_TXN_DESC = "Account Balance B/F";
    //
    public static boolean DEFAULT_SHOW_HELP = true;
}
