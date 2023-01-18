package com.ozorkin.repository;

import com.ozorkin.model.Invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class InvoiceListRepository implements Repository<Invoice>{
    private final  List<Invoice> invoiceArrayList = new ArrayList<>();
    private static InvoiceListRepository InvoiceListRepository;
    private static final BiPredicate<Invoice, String> CHECK_ID =
            (invoice, id) -> invoice.getId().equals(id);

    public InvoiceListRepository() {
    }

    public static InvoiceListRepository getInstance() {
        if (InvoiceListRepository == null) {
            InvoiceListRepository = new InvoiceListRepository();
        }
        return InvoiceListRepository;
    }


    @Override
    public void save(Invoice invoice) {
        invoiceArrayList.add(invoice);
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceArrayList;
    }

    @Override
    public Optional<Invoice> getById(final String id) {
        return invoiceArrayList.stream()
                .filter(invoice -> CHECK_ID.test(invoice, id))
                .findAny();
    }

    @Override
    public void delete(String id) {
        invoiceArrayList.removeIf(invoice -> CHECK_ID.test(invoice, id));
    }
}

