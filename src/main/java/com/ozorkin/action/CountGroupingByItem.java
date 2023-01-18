package com.ozorkin.action;

public class CountGroupingByItem implements Action{

    @Override
    public void execute() throws Exception {
        System.out.println(dataAnalytics.countGroupingByItem(shopService.getAllInvoices().getAll()));


    }
}
