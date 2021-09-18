/*
 * Copyright 2007-2011 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.gqrsoft.g6.util;

import FTsafe.Rockey4nd;

/**
 *
 * @author SHNG
 */
public class Rockey {

    Rockey4nd jr;
    private short function; //16 bits
    private short[] handle = new short[1]; //16 bits
    private int[] lp1 = new int[1]; //32 bits
    private int[] lp2 = new int[1];//32 bits
    private short[] p1 = new short[1];  //16 bits
    private short[] p2 = new short[1]; //16 bits
    private short[] p3 = new short[1];  //16 bits
    private short[] p4 = new short[1]; //16 bits
    private byte[] buf = new byte[1024]; //8 bits array
    private short ret;

    public void init() {
        jr = new Rockey4nd();
    }

    private void query() {
        ret = jr.Rockey4nd(function, handle, lp1, lp2,
                p1, p2, p3, p4, buf);
    }

    public boolean find(short pwd1, short pwd2) {
        return find(pwd1, pwd2, (short) 0, (short) 0);
    }

    public boolean find(short pwd1, short pwd2, short pwd3, short pwd4) {
        function = jr.RY_FIND;
        p1[0] = pwd1;
        p2[0] = pwd2;
        p3[0] = pwd3;
        p4[0] = pwd4;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean findNext() {
        function = jr.RY_FIND_NEXT;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean open() {
        function = jr.RY_OPEN;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean close() {
        function = jr.RY_CLOSE;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean read(short offsetOfUDZ, short length) {
        p1[0] = offsetOfUDZ;
        p2[0] = length;
        buf = new byte[length];
        function = jr.RY_READ;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean write(short offsetOfUDZ, short length, byte[] data) {
        p1[0] = offsetOfUDZ;
        p2[0] = length;
        buf = data;
        function = jr.RY_WRITE;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean random() {
        function = jr.RY_RANDOM;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean seed(int seedCode) {
        function = jr.RY_SEED;
        lp2[0] = seedCode;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean writeUserId(int userId) {
        function = jr.RY_WRITE_USERID;
        lp1[0] = userId;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean readUserId() {
        function = jr.RY_READ_USERID;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean writeModule(short moduleNumber, short moduleUnitValue, short decreaseAllow) {
        function = jr.RY_SET_MODULE;
        p1[0] = moduleNumber;
        p2[0] = moduleUnitValue;
        p3[0] = decreaseAllow;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean checkModule(short moduleNumber) {
        function = jr.RY_CHECK_MODULE;
        p1[0] = moduleNumber;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public short getModuleZeroValueAttribute() {
        return p2[0];
    }

    public short getModuleDecrementAttribute() {
        return p3[0];
    }

    public boolean decreaseModule(short moduleNumber) {
        function = jr.RY_DECREASE;
        p1[0] = moduleNumber;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean writeArithmetic(short positionInUAZ, byte[] data) {
        function = jr.RY_WRITE_ARITHMETIC;
        p1[0] = positionInUAZ;
        buf = data;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean calculate1(int startPointOfUAZ, int moduleNumber,
            short input1, short input2, short input3, short input4) {
        function = jr.RY_CALCULATE1;
        lp1[0] = startPointOfUAZ;
        lp2[0] = moduleNumber;
        p1[0] = input1;
        p2[0] = input2;
        p3[0] = input3;
        p4[0] = input4;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean calculate2(int startPointOfUAZ, int seedCode,
            short input1, short input2, short input3, short input4) {
        function = jr.RY_CALCULATE2;
        lp1[0] = startPointOfUAZ;
        lp2[0] = seedCode;
        p1[0] = input1;
        p2[0] = input2;
        p3[0] = input3;
        p4[0] = input4;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public boolean calculate3(int startPointOfUAZ, int moduleNumber,
            short input1, short input2, short input3, short input4) {
        function = jr.RY_CALCULATE3;
        lp1[0] = startPointOfUAZ;
        lp2[0] = moduleNumber;
        p1[0] = input1;
        p2[0] = input2;
        p3[0] = input3;
        p4[0] = input4;
        query();
        return ret == jr.ERR_SUCCESS;
    }

    public short getCalculateValue1() {
        return p1[0];
    }

    public short getCalculateValue2() {
        return p2[0];
    }

    public short getCalculateValue3() {
        return p3[0];
    }

    public short getCalculateValue4() {
        return p4[0];
    }

    public int getUserId() {
        return lp1[0];
    }

    public int getHardwareID() {
        return lp1[0];
    }

    public short getHandle() {
        return handle[0];
    }

    public int getRockeyType() {
        return lp2[0];
    }

    public byte[] getReadData() {
        return buf;
    }

    public short getRandomValue() {
        return p1[0];
    }

    public short getSeedValue1() {
        return p1[0];
    }

    public short getSeedValue2() {
        return p2[0];
    }

    public short getSeedValue3() {
        return p3[0];
    }

    public short getSeedValue4() {
        return p4[0];
    }

    public short getReturnValue() {
        return ret;
    }

    public String getReturnMessage() {
        if (ret == jr.ERR_SUCCESS) {
            return "Success";
        } else if (ret == jr.ERR_NO_PARALLEL_PORT) {
            return "No parallel port on the computer";
        } else if (ret == jr.ERR_NO_DRIVER) {
            return "No driver installed";
        } else if (ret == jr.ERR_NO_ROCKEY) {
            return "No Rockey4 dongle";
        } else if (ret == jr.ERR_INVALID_PASSWORD) {
            return "Found Rockey4 dongle, but base password is wrong";
        } else if (ret == jr.ERR_INVALID_PASSWORD_OR_ID) {
            return "Wrong password or Rockey4 HID";
        } else if (ret == jr.ERR_SETID) {
            return "Set Rockey4 HID wrong";
        } else if (ret == jr.ERR_INVALID_ADDR_OR_SIZE) {
            return "Read/Write address or length is wrong";
        } else if (ret == jr.ERR_UNKNOWN_COMMAND) {
            return "No such command";
        } else if (ret == jr.ERR_NOTBELEVEL3) {
            return "Inside error";
        } else if (ret == jr.ERR_READ) {
            return "Read error";
        } else if (ret == jr.ERR_WRITE) {
            return "Write error";
        } else if (ret == jr.ERR_RANDOM) {
            return "Random error";
        } else if (ret == jr.ERR_SEED) {
            return "Seed Code error";
        } else if (ret == jr.ERR_CALCULATE) {
            return "Calculate error";
        } else if (ret == jr.ERR_NO_OPEN) {
            return "No open dongle before operating dongle";
        } else if (ret == jr.ERR_OPEN_OVERFLOW) {
            return "Too many open dongles(>16)";
        } else if (ret == jr.ERR_NOMORE) {
            return "No more dongle";
        } else if (ret == jr.ERR_NEED_FIND) {
            return "No Find before FindNext";
        } else if (ret == jr.ERR_DECREASE) {
            return "Decrease error";
        } else if (ret == jr.ERR_AR_BADCOMMAND) {
            return "Arithmetic instruction error";
        } else if (ret == jr.ERR_AR_UNKNOWN_OPCODE) {
            return "Arithmetic operator error";
        } else if (ret == jr.ERR_AR_WRONGBEGIN) {
            return "Const number can't use on first arithmetic instruction";
        } else if (ret == jr.ERR_AR_WRONG_END) {
            return "Const number can't use on last arithmetic instruction";
        } else if (ret == jr.ERR_AR_VALUEOVERFLOW) {
            return "Const number > 63";
        } else if (ret == jr.ERR_RECEIVE_NULL) {
            return "Receive null";
        } else if (ret == jr.ERR_PRNPORT_BUSY) {
            return "Parallel busy";
        } else if (ret == jr.ERR_UNKNOWN) {
            return "Unknown error";
        }
        return "";
    }
}
