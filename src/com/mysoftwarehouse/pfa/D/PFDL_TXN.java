/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.D;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.form.UserFormInterface;
import com.gqrsoft.g5.developer.publicobject.FormEnum.DialogMessageType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ColumnAutoResizeMode;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.ListActionType;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum.SelectionType;
import com.mysoftwarehouse.pfa.M.PFMI0TXN;
import com.mysoftwarehouse.pfa.db.ACC.ACC;
import com.mysoftwarehouse.pfa.db.DB.DataEnum;
import com.mysoftwarehouse.pfa.db.TXN.TXN;
import com.mysoftwarehouse.pfa.db.TXN.TXN2;
import com.mysoftwarehouse.pfa.db.USR.USR;
import java.math.BigDecimal;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author Ng Siak Hooi
 */
public abstract class PFDL_TXN extends ListForm {

    @Override
    public Component formatCell(Component cellComp, int row, int column) {
        String txnType = cmd.list.getString(row, "TxnTyp");
		
        if (txnType.equals("R")) {
	      //String dbtText=cmd.list.getString(row, "Dbt");
		  //cmd.log.info("Dbt: " + dbtText);
	      //String crtText=cmd.list.getString(row, "Crt");
		  //cmd.log.info("Crt: " + crtText);
  		  BigDecimal nDbt = (BigDecimal)cmd.list.getObject(row, "Dbt");
		  BigDecimal nCrt = (BigDecimal)cmd.list.getObject(row, "Crt");
		  BigDecimal i=new BigDecimal("0");
		  BigDecimal nZero =  new BigDecimal("0");
		  BigDecimal nOne =  new BigDecimal("1");
		  BigDecimal nTen =  new BigDecimal("10");
		  BigDecimal nTenTen =  new BigDecimal("100");
		  
		  i=(nDbt.compareTo(nZero)==0)?nCrt:nDbt;
		  if(i.compareTo(nZero)==0){
            cellComp.setBackground(new Color(0xd3, 0xff, 0xce));
			  //both zero
		  }else if (i.compareTo(nOne)==-1) {
            cellComp.setBackground(new Color(0xff, 0xff, 0x00));
		  }else if (i.compareTo(nTen)==-1) {
            cellComp.setBackground(new Color(0xff, 0xcc, 0x00));
		  }else if (i.compareTo(nTenTen)==-1) {
            cellComp.setBackground(new Color(0xff, 0x66, 0x00));
		  }else {
            cellComp.setBackground(new Color(0xff, 0x0, 0x0));
		  }
        }
        return cellComp;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }

    @Override
    public ColumnAutoResizeMode getColumnAutoResizeMode() {
        return ColumnAutoResizeMode.OFF;
    }

    @Override
    public Class<? extends UserFormInterface> getBottomForm() {
        return PFMI0TXN.class;
    }

    @Override
    protected void buildKeyList() {
    }

    @Override
    protected void buildDataList() {
        super.addCalendarField("TxnDte", "PFTXN.TxnDte");
        super.setFieldHelpMessage("PFTXN.TxnDte.help");
        super.setFieldFormat(true, true);
        super.setFieldListColumnWidth(150);

        super.addTextField("TxnId", "PFTXN.TxnId");
        super.setFieldHelpMessage("PFTXN.TxnId.help");
        super.setFieldListColumnWidth(20);

        super.addTextField("TxnTyp", "PFTXN.TxnTyp");
        super.setFieldHelpMessage("PFTXN.TxnTyp.help");
        super.setFieldListColumnWidth(15);

        super.addTextField("FrmAcc", "PFTXN.FrmAcc");
        super.setFieldHelpMessage("PFTXN.FrmAcc.help");
        super.setFieldListColumnWidth(100);

        super.addTextField("ToAcc", "PFTXN.ToAcc");
        super.setFieldHelpMessage("PFTXN.ToAcc.help");
        super.setFieldListColumnWidth(100);

        super.addTextField("RefNo", "PFTXN.RefNo");
        super.setFieldHelpMessage("PFTXN.RefNo.help");

        super.addTextField("Dsc", "PFTXN.Dsc");
        super.setFieldHelpMessage("PFTXN.Dsc.help");

        super.addNumericField("Dbt", "PFDL_TXN.Dbt");
        super.setFieldHelpMessage("PFDL_TXN.Dbt.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Crt", "PFDL_TXN.Crt");
        super.setFieldHelpMessage("PFDL_TXN.Crt.help");
        super.setFieldOutputFormat("#0.00");

        super.addNumericField("Amt", "PFDL_TXN.Amt");
        super.setFieldHelpMessage("PFDL_TXN.Amt.help");
        super.setFieldOutputFormat("#0.00");

//        super.addTextField("Remark", "PFTXN.Remark");
//        super.setFieldHelpMessage("PFTXN.Remark.help");
    }

    @Override
    protected void buildGeneral() {
        super.setActionEnable(ListActionType.ADD, true);
        super.setActionEnable(ListActionType.EDIT, true);
        super.setActionEnable(ListActionType.COPY, true);
        super.setActionEnable(ListActionType.VIEW, true);
        super.setActionEnable(ListActionType.DELETE, true);
        super.setActionEnable(ListActionType.RELOAD, true);
        //super.setActionEnable(ListActionType.SELECT, true);
        super.setActionEnable(ListActionType.SAVE_ALL_TO_CSV, true);
        super.setActionEnable(ListActionType.SAVE_SELECTED_TO_CSV, true);
        super.setActionEnable(ListActionType.CLOSE, true);

        super.setAddForm(PFDS0TXN.class);
        super.setCopyForm(PFDS0TXN.class);
        super.setEditForm(PFDS0TXN.class);
        super.setViewForm(PFDS0TXN.class);
    }

    @Override
    public void executeAdd(int selectedRow, int[] selectedRows) throws Exception {
        String Acc = cmd.in.map.texts.get(ACC.PFACC_ACC);
        cmd.out.map.texts.put(ACC.PFACC_ACC, Acc);
    }

    @Override
    public void executeCopy(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeEdit(int selectedRow, int[] selectedRows) throws Exception {
        executeView(selectedRow, selectedRows);
    }

    @Override
    public void executeView(int selectedRow, int[] selectedRows) throws Exception {
        String TxnTyp = cmd.list.getString(selectedRow, "TxnTyp");
        if (TxnTyp.equals(DataEnum.TxnTyp.B.code)) {
            String title = "PFDL_TXN.WrongTxnTyp.title";
            String message = "PFDL_TXN.WrongTxnTyp.message";
            cmd.common.showMessage(DialogMessageType.ERROR, title, message);
            throw new Exception("PFDL_TXN.WrongTxnTyp");
        }
        Integer txnId = (Integer) cmd.list.getObject(selectedRow, "TxnId");
        cmd.out.map.integers.put(TXN.PFTXN_TXNID, txnId);
    }

    @Override
    public void verifyDelete(int selectedRow, int[] selectedRows) throws Exception {
        String TxnTyp = cmd.list.getString(selectedRow, "TxnTyp");
        if (TxnTyp.equals(DataEnum.TxnTyp.B.code)) {
            String title = "PFDL_TXN.WrongTxnTyp.title";
            String message = "PFDL_TXN.WrongTxnTyp.message";
            cmd.common.showMessage(DialogMessageType.ERROR, title, message);
            throw new Exception("PFDL_TXN.WrongTxnTyp");
        }
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
        Integer txnId = (Integer) cmd.list.getObject(selectedRow, "TxnId");

        String id = cmd.global.texts.get(USR.PFUSR_ID);
        try {
            cmd.db.begin();
            TXN2.delete(this, id, txnId.intValue());
            cmd.db.commit();
        } catch (SQLException e) {
            cmd.db.rollback();
            throw e;
        }
        cmd.list.refreshList();
    }

    @Override
    public void executeSelect(int arg0, int[] arg1) throws Exception {
    }
}
