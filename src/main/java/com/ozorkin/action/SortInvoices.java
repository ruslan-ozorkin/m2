package com.ozorkin.action;

public class SortInvoices implements Action{

    @Override
    public void execute() throws Exception {
        dataAnalytics.sortInvoices(shopService.getAllInvoices().getAll()).forEach(System.out::println);
        System.out.println("Invoices sorted successfully");

    }
}
