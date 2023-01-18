package com.ozorkin.action;

public class CreateFifteenInvoices implements Action{
    @Override
    public void execute() throws Exception {
        int countInvoices = 15;
        for (int i = 0; i < countInvoices; i++) {
            shopService.createInvoiceFromFile("order.csv",";");
        }
        System.out.println("15 invoices has been created");

    }
}
