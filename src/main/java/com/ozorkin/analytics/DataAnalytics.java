package com.ozorkin.analytics;

import com.ozorkin.model.Invoice;
import com.ozorkin.model.InvoiceType;
import com.ozorkin.model.Items;
import com.ozorkin.model.ItemsType;
import com.ozorkin.service.InvoiceAgeComparator;
import com.ozorkin.service.InvoiceAmountComparator;
import com.ozorkin.service.InvoicePriceComparator;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataAnalytics {
    private static DataAnalytics instance;
    private static final InvoiceAgeComparator INVOICE_AGE_COMPARATOR = new InvoiceAgeComparator();
    private static final InvoiceAmountComparator INVOICE_AMOUNT_COMPARATOR = new InvoiceAmountComparator();
    private static final InvoicePriceComparator INVOICE_PRICE_COMPARATOR = new InvoicePriceComparator();

    public static DataAnalytics getInstance() {
        if (instance == null) {
            instance = new DataAnalytics();
        }
        return instance;
    }

    public Map<ItemsType, Long> countGroupingByItem(final @NotNull List<Invoice> invoiceList) {
        return invoiceList.stream()
                .flatMap(x -> x.getItemsList().stream())
                .collect(Collectors.groupingBy(Items::getItemsType, Collectors.counting()));
    }

    public int lowestInvoice(final @NotNull List<Invoice> invoiceList) {
        return invoiceList.stream()
                .min(Comparator.comparingInt(x -> x.getItemsList().stream()
                        .mapToInt(Items::getPrice)
                        .sum())).stream()
                .peek(invoice -> System.out.println(invoice.getCustomer()))
                .flatMap(x -> x.getItemsList().stream())
                .mapToInt(Items::getPrice)
                .sum();
    }

    public int allInvoiceSum(final @NotNull List<Invoice> invoiceList) {
        return invoiceList.stream()
                .flatMap(x -> x.getItemsList().stream())
                .mapToInt(Items::getPrice)
                .sum();
    }

    public int countRetailInvoices(final @NotNull List<Invoice> invoiceList) {
        return (int) invoiceList.stream()
                .filter(x -> x.getInvoiceType().equals(InvoiceType.RETAIL))
                .count();
    }

    public List<Invoice> distinctItemInvoice(final @NotNull List<Invoice> invoiceList) {
        return invoiceList.stream()
                .filter(x -> 1 == x.getItemsList().stream()
                .map(Items::getItemsType)
                .distinct()
                .toArray().length)
                .collect(Collectors.toList());
    }

    public List<Invoice> firstThreeInvoices(final @NotNull List<Invoice> invoiceList) {
        return invoiceList.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Invoice> lessThanEighteenInvoice(final @NotNull List<Invoice> invoiceList) {
        return invoiceList.stream()
                .filter(x -> x.getCustomer().getAge() < 18)
                .peek(x -> x.setInvoiceType(InvoiceType.LOW_AGE))
                .collect(Collectors.toList());
    }

    public List<Invoice> sortInvoices(final @NotNull List<Invoice> invoiceList) {
        return invoiceList.stream()
                .sorted(INVOICE_AGE_COMPARATOR
                        .thenComparing(INVOICE_AMOUNT_COMPARATOR)
                        .thenComparing(INVOICE_PRICE_COMPARATOR))
                        .collect(Collectors.toList());
    }
}
