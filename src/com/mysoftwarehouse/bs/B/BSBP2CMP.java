/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.bs.B;

import com.gqrsoft.g5.developer.form.ProcessForm;
import com.gqrsoft.g5.developer.publicobject.ProcessException;
import com.mysoftwarehouse.bs.conf.MAP;
import com.mysoftwarehouse.bs.db.CFG.CFG;
import com.mysoftwarehouse.bs.db.CFG.CFGI;
import com.mysoftwarehouse.bs.db.CMP.CMP;
import com.mysoftwarehouse.bs.db.CMP.CMPA;
import com.mysoftwarehouse.bs.db.CMP.CMPC;
import com.mysoftwarehouse.bs.db.CUS.CUS;
import com.mysoftwarehouse.bs.db.CUS.CUSA;
import com.mysoftwarehouse.bs.db.CUS.CUSC;
import com.mysoftwarehouse.bs.db.INV.INV;
import com.mysoftwarehouse.bs.db.INV.INVA;
import com.mysoftwarehouse.bs.db.INV.INVC;
import com.mysoftwarehouse.bs.db.INV.INVD;
import com.mysoftwarehouse.bs.db.INV.INVE;
import com.mysoftwarehouse.bs.db.INV.INVJ;
import com.mysoftwarehouse.bs.db.INV.INVR;
import com.mysoftwarehouse.bs.db.PI.PI;
import com.mysoftwarehouse.bs.db.PI.PID;
import com.mysoftwarehouse.bs.db.PI.PIP;
import com.mysoftwarehouse.bs.db.PY.PY;
import com.mysoftwarehouse.bs.db.PIV.PIV;
import com.mysoftwarehouse.bs.db.PIV.PIVA;
import com.mysoftwarehouse.bs.db.PIV.PIVC;
import com.mysoftwarehouse.bs.db.PIV.PIVD;
import com.mysoftwarehouse.bs.db.PIV.PIVE;
import com.mysoftwarehouse.bs.db.POR.POR;
import com.mysoftwarehouse.bs.db.POR.PORA;
import com.mysoftwarehouse.bs.db.POR.PORC;
import com.mysoftwarehouse.bs.db.POR.PORD;
import com.mysoftwarehouse.bs.db.POR.PORE;
import com.mysoftwarehouse.bs.db.POR.PORS;
import com.mysoftwarehouse.bs.db.PSI.PSI;
import com.mysoftwarehouse.bs.db.PSI.PSID;
import com.mysoftwarehouse.bs.db.QTT.QTT;
import com.mysoftwarehouse.bs.db.QTT.QTTA;
import com.mysoftwarehouse.bs.db.QTT.QTTC;
import com.mysoftwarehouse.bs.db.QTT.QTTD;
import com.mysoftwarehouse.bs.db.QTT.QTTE;
import com.mysoftwarehouse.bs.db.QTT.QTTJ;
import com.mysoftwarehouse.bs.db.QTT.QTTR;
import com.mysoftwarehouse.bs.db.RCP.RCP;
import com.mysoftwarehouse.bs.db.RCP.RCPA;
import com.mysoftwarehouse.bs.db.RCP.RCPC;
import com.mysoftwarehouse.bs.db.RCP.RCPD;
import com.mysoftwarehouse.bs.db.RCP.RCPE;
import com.mysoftwarehouse.bs.db.RCP.RCPJ;
import com.mysoftwarehouse.bs.db.RCP.RCPR;
import com.mysoftwarehouse.bs.db.SA.SA;
import com.mysoftwarehouse.bs.db.SI.SI;
import com.mysoftwarehouse.bs.db.SI.SID;
import com.mysoftwarehouse.bs.db.SY.SY;
import com.mysoftwarehouse.bs.db.ST.ST;
import com.mysoftwarehouse.bs.db.ST.STD;
import com.mysoftwarehouse.bs.db.SUP.SUP;
import com.mysoftwarehouse.bs.db.SUP.SUPA;
import com.mysoftwarehouse.bs.db.SUP.SUPC;
import java.sql.SQLException;

/**
 *
 * @author Ng Siak Hooi
 */
public class BSBP2CMP extends ProcessForm {

    @Override
    public void init() {
        super.setUserAllowCancel(false);
        super.setUserManualClose(false);
        super.setUserManualStart(false);
        super.setLogListVisible(false);

        addProcess("BSBP2CMP.Begin", 1);
        addProcess("BSBP2CMP.DeleteBSCMP", 1);
        addProcess("BSBP2CMP.DeleteBSCMPA", 1);
        addProcess("BSBP2CMP.DeleteBSCMPC", 1);
        addProcess("BSBP2CMP.DeleteBSCFG", 1);
        addProcess("BSBP2CMP.DeleteBSCFGI", 1);

        addProcess("BSBP2CMP.DeleteBSCUS", 1);
        addProcess("BSBP2CMP.DeleteBSCUSA", 1);
        addProcess("BSBP2CMP.DeleteBSCUSC", 1);

        addProcess("BSBP2CMP.DeleteBSSUP", 1);
        addProcess("BSBP2CMP.DeleteBSSUPA", 1);
        addProcess("BSBP2CMP.DeleteBSSUPC", 1);

        addProcess("BSBP2CMP.DeleteBSSA", 1);
        addProcess("BSBP2CMP.DeleteBSSI", 1);
        addProcess("BSBP2CMP.DeleteBSSID", 1);
        addProcess("BSBP2CMP.DeleteBSST", 1);
        addProcess("BSBP2CMP.DeleteBSSTD", 1);
        addProcess("BSBP2CMP.DeleteBSSY", 1);

        addProcess("BSBP2CMP.DeleteBSPI", 1);
        addProcess("BSBP2CMP.DeleteBSPID", 1);
        addProcess("BSBP2CMP.DeleteBSPIP", 1);
        addProcess("BSBP2CMP.DeleteBSPY", 1);
        addProcess("BSBP2CMP.DeleteBSPSI", 1);
        addProcess("BSBP2CMP.DeleteBSPSID", 1);

        addProcess("BSBP2CMP.DeleteBSQTT", 1);
        addProcess("BSBP2CMP.DeleteBSQTTD", 1);
        addProcess("BSBP2CMP.DeleteBSQTTE", 1);
        addProcess("BSBP2CMP.DeleteBSQTTA", 1);
        addProcess("BSBP2CMP.DeleteBSQTTC", 1);
        addProcess("BSBP2CMP.DeleteBSQTTJ", 1);
        addProcess("BSBP2CMP.DeleteBSQTTR", 1);

        addProcess("BSBP2CMP.DeleteBSINV", 1);
        addProcess("BSBP2CMP.DeleteBSINVD", 1);
        addProcess("BSBP2CMP.DeleteBSINVE", 1);
        addProcess("BSBP2CMP.DeleteBSINVA", 1);
        addProcess("BSBP2CMP.DeleteBSINVC", 1);
        addProcess("BSBP2CMP.DeleteBSINVJ", 1);
        addProcess("BSBP2CMP.DeleteBSINVR", 1);

        addProcess("BSBP2CMP.DeleteBSRCP", 1);
        addProcess("BSBP2CMP.DeleteBSRCPD", 1);
        addProcess("BSBP2CMP.DeleteBSRCPE", 1);
        addProcess("BSBP2CMP.DeleteBSRCPA", 1);
        addProcess("BSBP2CMP.DeleteBSRCPC", 1);
        addProcess("BSBP2CMP.DeleteBSRCPJ", 1);
        addProcess("BSBP2CMP.DeleteBSRCPR", 1);

        addProcess("BSBP2CMP.DeleteBSPOR", 1);
        addProcess("BSBP2CMP.DeleteBSPORD", 1);
        addProcess("BSBP2CMP.DeleteBSPORE", 1);
        addProcess("BSBP2CMP.DeleteBSPORA", 1);
        addProcess("BSBP2CMP.DeleteBSPORC", 1);
        addProcess("BSBP2CMP.DeleteBSPORS", 1);

        addProcess("BSBP2CMP.DeleteBSPIV", 1);
        addProcess("BSBP2CMP.DeleteBSPIVD", 1);
        addProcess("BSBP2CMP.DeleteBSPIVE", 1);
        addProcess("BSBP2CMP.DeleteBSPIVA", 1);
        addProcess("BSBP2CMP.DeleteBSPIVC", 1);
        addProcess("BSBP2CMP.Commit", 1);
    }

    @Override
    public void run() throws ProcessException {
        try {
            cmd.db.begin();
            String cmp = cmd.in.map.texts.get(MAP.BSCMP.CMP);
            super.completed();
            CMP.delete(this, cmp);
            super.completed();
            CMPA.delete(this, cmp);
            super.completed();
            CMPC.delete(this, cmp);
            super.completed();
            CFG.delete(this, cmp);
            super.completed();
            CFGI.delete(this, cmp);
            super.completed();

            CUS.delete(this, cmp);
            super.completed();
            CUSA.delete(this, cmp);
            super.completed();
            CUSC.delete(this, cmp);
            super.completed();

            SUP.delete(this, cmp);
            super.completed();
            SUPA.delete(this, cmp);
            super.completed();
            SUPC.delete(this, cmp);
            super.completed();

            SA.delete(this, cmp);
            super.completed();
            SI.delete(this, cmp);
            super.completed();
            SID.delete(this, cmp);
            super.completed();
            ST.delete(this, cmp);
            super.completed();
            STD.delete(this, cmp);
            super.completed();
            SY.delete(this, cmp);
            super.completed();

            PI.delete(this, cmp);
            super.completed();
            PID.delete(this, cmp);
            super.completed();
            PIP.delete(this, cmp);
            super.completed();
            PY.delete(this, cmp);
            super.completed();
            PSI.delete(this, cmp);
            super.completed();
            PSID.delete(this, cmp);
            super.completed();

            QTT.delete(this, cmp);
            super.completed();
            QTTD.delete(this, cmp);
            super.completed();
            QTTE.delete(this, cmp);
            super.completed();
            QTTA.delete(this, cmp);
            super.completed();
            QTTC.delete(this, cmp);
            super.completed();
            QTTJ.delete(this, cmp);
            super.completed();
            QTTR.delete(this, cmp);
            super.completed();

            INV.delete(this, cmp);
            super.completed();
            INVD.delete(this, cmp);
            super.completed();
            INVE.delete(this, cmp);
            super.completed();
            INVA.delete(this, cmp);
            super.completed();
            INVC.delete(this, cmp);
            super.completed();
            INVJ.delete(this, cmp);
            super.completed();
            INVR.delete(this, cmp);
            super.completed();

            RCP.delete(this, cmp);
            super.completed();
            RCPD.delete(this, cmp);
            super.completed();
            RCPE.delete(this, cmp);
            super.completed();
            RCPA.delete(this, cmp);
            super.completed();
            RCPC.delete(this, cmp);
            super.completed();
            RCPJ.delete(this, cmp);
            super.completed();
            RCPR.delete(this, cmp);
            super.completed();

            POR.delete(this, cmp);
            super.completed();
            PORD.delete(this, cmp);
            super.completed();
            PORE.delete(this, cmp);
            super.completed();
            PORA.delete(this, cmp);
            super.completed();
            PORC.delete(this, cmp);
            super.completed();
            PORS.delete(this, cmp);
            super.completed();

            PIV.delete(this, cmp);
            super.completed();
            PIVD.delete(this, cmp);
            super.completed();
            PIVE.delete(this, cmp);
            super.completed();
            PIVA.delete(this, cmp);
            super.completed();
            PIVC.delete(this, cmp);
            super.completed();

            cmd.db.commit();
            super.completed();
        } catch (SQLException ex) {
            cmd.log.severe("BSBP2CMP.error", ex);
            String msg = "BSBP2CMP.error.{0}";
            msg = cmd.lang.getString(msg, ex.getLocalizedMessage());
            super.setI18nMessage(msg);
            try {
                cmd.db.rollback();
            } catch (Exception e) {
            }
            super.cancelNow();
        }
    }

    @Override
    public String getFormTitle() {
        return "BSBP2CMP.title";
    }
}
