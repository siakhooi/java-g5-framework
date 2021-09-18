/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g5.experimental.reportdesign.g5public;

/**
 *
 * @author Ng Siak Hooi
 */
public class BarcodeEnum {

    public enum Type1 {

        Code2of7(1),
        Bookland(3),
        Codabar(4),
        Code128(5),
        Code128A(6),
        Code128B(7),
        Code128C(8),
        EAN128(10),
        EAN13(11),
        GlobalTradeItemNumber(12),
        Monarch(14),
        NW7(15),
        PDF417(16),
        SCC14ShippingCode(17),
        ShipmentIdentificationNumber(18),
        UPCA(22),
        USD4(24),
        USPS(25);
        public int type = 0;

        Type1(int type) {
            this.type = type;
        }
    }

    public enum Type2 {

        Code3of9(2),
        Code39(9),
        Int2of5(13),
        UCCEAN128_SSCC_18_AI(19),
        Std2of5(20),
        USD3(23),
        Code39Barcode(26);
        public int type = 0;

        Type2(int type) {
            this.type = type;
        }
    }

    public enum Type3 {

        UCCEAN128(21);
        public int type = 0;

        Type3(int type) {
            this.type = type;
        }
    }
}
