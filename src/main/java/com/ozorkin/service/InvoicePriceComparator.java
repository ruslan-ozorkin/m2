package com.ozorkin.service;

import com.ozorkin.model.Invoice;
import com.ozorkin.model.Items;

import java.util.Comparator;

public class InvoicePriceComparator implements Comparator<Invoice> {
    @Override
    public int compare(Invoice o1, Invoice o2) {
        return Integer.compare(o2.getItemsList().stream().mapToInt(Items::getPrice).sum(),
                o1.getItemsList().stream().mapToInt(Items::getPrice).sum());
    }
}
