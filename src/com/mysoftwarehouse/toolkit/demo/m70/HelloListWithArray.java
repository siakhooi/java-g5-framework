/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m70;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum;
import com.mysoftwarehouse.toolkit.demo.m20.blank.HelloWorld;
import java.awt.Color;
import java.awt.Component;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloListWithArray extends ListForm {

    @Override
    protected void buildKeyList() {
        super.addTextField("test3", "helloList 3");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("test2", "helloList 2");
        super.addTextField("test1", "helloList 1");
    }

    @Override
    protected void buildGeneral() {
        super.setDeleteForm(HelloWorld.class);
    }

    @Override
    public ListFormEnum.DataType getDataType() {
        return ListFormEnum.DataType.ARRAY;
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows)  {
        Object[][] array1 = {
            {"A1", "B", "C"},
            {"A2", "B2", "C2"}};
        super.useArray(array1);
    }

    @Override
    public Component formatCell(Component cellComp, int row, int column) {
        if(row==1 && column==1){
            cellComp.setBackground(Color.RED);
        }
        return cellComp;
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception {
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows) throws Exception {
    }

    @Override
    public String getFormTitle() {
        return "HelloListWithArray.title";
    }
}
