package com.ozorkin.action;

import lombok.Getter;

@Getter
public enum Actions {
    CREATE_INVOICE("Create one invoice from file", new CreateAction()),
    SHOW_ALL_INVOICES("Show all invoices", new ShowAllInvoices()),
    CREATE_FIFTEEN_INVOICE("Create 15 invoices", new CreateFifteenInvoices()),
    COUNT_GROUPING_BY_ITEM("Count grouping by item", new CountGroupingByItem()),
    FIRST_THREE_INVOICES("Find three invoices", new FirstThreeInvoices()),
    LESS_THAN_EIGHTEEN_CUSTOMER_INVOICE("Invoices less than 18 customer", new LessThanEighteenCustomerInvoice()),
    LOWEST_INVOICE("THE LOWEST INVOICE", new LowestInvoice()),
    SORT_INVOICES("Sort invoices ",new SortInvoices()),
    EXIT("Finish program", new ExitAction());

    private final String name;
    private final Action action;

    Actions(final String name, final Action action) {
        this.name = name;
        this.action = action;
    }

    public void execute() throws Exception {
        action.execute();
    }


}
