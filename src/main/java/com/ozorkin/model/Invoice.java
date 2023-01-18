package com.ozorkin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class Invoice {
    private final List<Items> itemsList ;
    private final Customer customer;
    private  InvoiceType invoiceType;
    private String dateTime;
    private String id;
    private static final int PRICE_LIMIT = 1000;

    public Invoice(List<Items> itemsList, Customer customer) {
        this.itemsList = itemsList;
        this.customer = customer;
        this.invoiceType = choosingInvoiceTypeByPrice();
        this.dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
                .format(LocalDateTime.now());
        this.id = UUID.randomUUID().toString();
    }


    public List<Items> getItemsList() {
        return itemsList;
    }

    private InvoiceType choosingInvoiceTypeByPrice() {
        return getItemsList()
                .stream()
                .mapToInt(Items::getPrice)
                .sum() <= PRICE_LIMIT ? InvoiceType.RETAIL: InvoiceType.WHOLESALE;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "itemsList=" + itemsList +
                ", customer=" + customer +
                ", invoiceType=" + invoiceType +
                ", dateTime='" + dateTime + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
