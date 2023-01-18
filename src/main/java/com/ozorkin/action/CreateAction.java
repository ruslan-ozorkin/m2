package com.ozorkin.action;

import com.ozorkin.model.Invoice;

public class CreateAction implements Action{

    @Override
    public void execute() throws Exception {
        shopService.createInvoiceFromFile("order.csv",";");
        System.out.println("Created invoices: " + shopService.getAllInvoices().getAll());
    }
}
