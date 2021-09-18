/*
 * Copyright 2007 GQR Solutions. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mysoftwarehouse.pfa.G;

import com.gqrsoft.g5.developer.form.UserFormInterface;
import java.util.Vector;

/**
 *
 * @author Ng Siak Hooi
 */
public class SampleAccountResource {

    private Vector<Vector<Object>> all;
    private UserFormInterface form;

    public Vector<Vector<Object>> getAll() {
        return all;
    }

    public void init(UserFormInterface form) {
        all = new Vector<Vector<Object>>();
        this.form = form;
    }

    private void a(String accType, String accCode, String title) {
        String accName = "sample." + title + ".name";
        String accRemark = "sample." + title + ".remark";
        accName = form.cmd().lang.getString(accName);
        accRemark = form.cmd().lang.getString(accRemark);
        Vector<Object> vo=new Vector<Object>();
        vo.add(accCode);
        vo.add(accName);
        vo.add(accType);
        vo.add(accRemark);
        all.add(vo);
    }

    public void addAsset() {
        a("A", "AB", "asset.bank");
        a("A", "ABC", "asset.bank.current");
        a("A", "ABS", "asset.bank.saving");
        a("A", "ABF", "asset.bank.fixed");
        a("A", "AD", "asset.debitcard");
        a("A", "AC", "asset.cash");
        a("A", "AI", "asset.invest");
        a("A", "AIF", "asset.invest.fund");
        a("A", "AIS", "asset.invest.share");
        a("A", "AAR", "asset.account.receiveable");
        a("A", "AF", "asset.fixed");
        a("A", "AM", "asset.misc");
        a("A", "AME", "asset.misc.epf");
    }

    public void addLiability() {
        a("L", "LC", "liability.creditcard");
        a("L", "LL", "liability.loan");
        a("L", "LLV", "liability.loan.car");
        a("L", "LLH", "liability.loan.house");
        a("L", "LLE", "liability.loan.personal");
        a("L", "LAP", "liability.account.payable");
    }

    public void addIncome() {
        a("I", "IE", "income.employee");
        a("I", "IEW", "income.employee.wages");
        a("I", "IEO", "income.employee.overtime");
        a("I", "IEB", "income.employee.bonus");
        a("I", "IEC", "income.employee.commission");
        a("I", "IEES", "income.employee.epf.self");
        a("I", "IEEC", "income.employee.epf.company");
        a("I", "IES", "income.employee.stockoption");
        a("I", "II", "income.investment");
        a("I", "III", "income.investment.interest");
        a("I", "IIG", "income.investment.capitalgain");
        a("I", "IID", "income.investment.dividend");
        a("I", "IM", "income.misc");
        a("I", "IMBI", "income.misc.bank.interest");
        a("I", "IMG", "income.misc.gifts");
        a("I", "IML", "income.misc.lotteries");
    }

    public void addExpenses() {
        a("E", "EF", "expenses.food");
        //a("E", "EFM", "expenses.food.meal");
        a("E", "EFL", "expenses.food.luxury");
        a("E", "EFG", "expenses.food.groceries");

        a("E", "EV", "expenses.vehicle");
        a("E", "EVP", "expenses.vehicle.petrol");
        a("E", "EVT", "expenses.vehicle.toll");
        a("E", "EVA", "expenses.vehicle.parking");
        a("E", "EVW", "expenses.vehicle.wash");
        a("E", "EVM", "expenses.vehicle.maintenance");
        a("E", "EVR", "expenses.vehicle.roadtax");
        a("E", "EVI", "expenses.vehicle.insurance");

        a("E", "EB", "expenses.bill");
        a("E", "EBP", "expenses.bill.phone");
        a("E", "EBE", "expenses.bill.electricity");
        a("E", "EBI", "expenses.bill.internet");
        a("E", "EBG", "expenses.bill.garbage");
        a("E", "EBW", "expenses.bill.water");
        a("E", "EBT", "expenses.bill.watertreatment");

        a("E", "EE", "expenses.education");
        a("E", "EET", "expenses.education.tuition");
        a("E", "EEB", "expenses.education.book");
        a("E", "EEF", "expenses.education.fee");

        a("E", "EC", "expenses.healthcare");
        a("E", "ECD", "expenses.healthcare.dental");
        a("E", "ECE", "expenses.healthcare.eyecare");
        a("E", "ECM", "expenses.healthcare.medical");

        a("E", "EH", "expenses.house");
        a("E", "EHR", "expenses.house.renovation");
        a("E", "EHC", "expenses.house.cleaning");
        a("E", "EHF", "expenses.house.furniture");
        a("E", "EHE", "expenses.house.electronics");
        a("E", "EHT", "expenses.house.rent");

        a("E", "EL", "expenses.leisure");
        a("E", "ELS", "expenses.leisure.sports");
        a("E", "ELR", "expenses.leisure.recreation");
        a("E", "ELN", "expenses.leisure.newspaper");
        a("E", "ELB", "expenses.leisure.book");
        a("E", "ELH", "expenses.leisure.membership");
        a("E", "ELZ", "expenses.leisure.magazine");
        a("E", "ELT", "expenses.leisure.tapencd");
        a("E", "ELO", "expenses.leisure.toyngame");
        a("E", "ELG", "expenses.leisure.photo");
        a("E", "ELM", "expenses.leisure.movie");
        a("E", "ELP", "expenses.leisure.pet");

        a("E", "ET", "expenses.vacation");
        a("E", "ETL", "expenses.vacation.lodging");
        a("E", "ETT", "expenses.vacation.travel");
        a("E", "ETA", "expenses.vacation.laundry");

        a("E", "EP", "expenses.personal");
        a("E", "EPC", "expenses.personal.care");
        a("E", "EPH", "expenses.personal.clothing");
        a("E", "EPL", "expenses.personal.laundry");

        a("E", "ER", "expenses.transportation");
        a("E", "ERB", "expenses.transportation.bus");
        a("E", "ERR", "expenses.transportation.rail");
        a("E", "ERA", "expenses.transportation.air");
        a("E", "ERS", "expenses.transportation.sea");

        a("E", "EK", "expenses.bank");
        a("E", "EKS", "expenses.bank.servicecharges");
        a("E", "EKI", "expenses.bank.interest");

        a("E", "EM", "expenses.misc");
        a("E", "EMD", "expenses.misc.donation");
        a("E", "EMC", "expenses.misc.charity");
        a("E", "EMT", "expenses.misc.tax");
        a("E", "EML", "expenses.misc.lotteries");
        a("E", "EMG", "expenses.misc.gifts");
    }
}
