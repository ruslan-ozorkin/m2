package com.ozorkin.service;

import com.ozorkin.model.Invoice;

import java.util.Comparator;

public class InvoiceAmountComparator implements Comparator<Invoice> {
    @Override
    public int compare(Invoice o1, Invoice o2) {
        return Integer.compare(o2.getItemsList().size(), o1.getItemsList().size());
    }
}
