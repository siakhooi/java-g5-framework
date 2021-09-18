/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.toolkit.demo.m70;

import com.gqrsoft.g5.developer.form.ListForm;
import com.gqrsoft.g5.developer.publicobject.ListFormEnum;
import java.math.BigDecimal;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class HelloListWithVector extends ListForm {

    @Override
    protected void buildKeyList() {
        super.addTextField("test3", "helloList 3");
    }

    @Override
    protected void buildDataList() {
        super.addTextField("test2", "helloList 2");
        super.addTextField("test1", "helloList 1");
        super.setFieldVisible(false);
        
        super.addNumericField("num1", "number1 (4)");
        super.setFieldOutputFormat("#, ##0.0000");
        
        super.addNumericField("num2", "number2 (1)");
        super.setFieldOutputFormat("#, ##0.0");
    }
    private Vector<Vector<Object>> v;

    @Override
    protected void buildGeneral() {
        v = new Vector<Vector<Object>>();
        for (int i = 0; i < 100; i++) {
            Vector<Object> v1 = new Vector<Object>();
            v1.add("A" + i);
            v1.add("B" + i);
            v1.add("C" + i);
            v1.add(new BigDecimal(i * 100 + i));
            v1.add(new BigDecimal(i * 100 + i));

            v.add(v1);
        }
    }

    @Override
    public void afterReload() {
        cmd.list.selectRow(10);
    }

    @Override
    public ListFormEnum.DataType getDataType() {
        return ListFormEnum.DataType.VECTOR;
    }

    @Override
    public void executeReload(int selectedRow, int[] selectedRows) {
        super.useVector(v);
    }

    @Override
    public void executeDelete(int selectedRow, int[] selectedRows) throws Exception{
    }

    @Override
    public void executeSelect(int selectedRow, int[] selectedRows)throws Exception {
    }

    @Override
    public String getFormTitle() {
        return "HelloListWithVector.title";
    }
}
