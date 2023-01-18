package com.ozorkin.action;

public class ShowAllInvoices implements Action{
    @Override
    public void execute() throws Exception {
        shopService.getAllInvoices().getAll().forEach(System.out::println);
    }
}
