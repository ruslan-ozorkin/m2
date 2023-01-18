package com.ozorkin.action;

public class LessThanEighteenCustomerInvoice implements Action{
    @Override
    public void execute() throws Exception {
       dataAnalytics.lessThanEighteenInvoice(shopService.getAllInvoices().getAll()).forEach(System.out::println);

    }
}
