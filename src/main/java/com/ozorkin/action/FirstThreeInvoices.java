package com.ozorkin.action;

public class FirstThreeInvoices implements Action{
    @Override
    public void execute() throws Exception {
        dataAnalytics.firstThreeInvoices(shopService.getAllInvoices().getAll()).forEach(System.out::println);

    }
}
