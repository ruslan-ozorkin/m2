package com.ozorkin.action;

public class LowestInvoice implements Action {

    @Override
    public void execute() throws Exception {
        System.out.println(dataAnalytics.lowestInvoice(shopService.getAllInvoices().getAll()));

    }
}
