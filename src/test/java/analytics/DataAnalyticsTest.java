package analytics;

import com.ozorkin.analytics.DataAnalytics;
import com.ozorkin.model.*;
import com.ozorkin.repository.InvoiceListRepository;
import com.ozorkin.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class DataAnalyticsTest {

    private DataAnalytics dataAnalytics;

    private List<Invoice> invoiceList;
    private static Invoice invoice1;
    private static Invoice invoice2;
    private static Invoice invoice3;
    private static Invoice invoice4;
    private static Invoice invoice5;


    @BeforeEach
    void setUp() {
        dataAnalytics = DataAnalytics.getInstance();
        PersonService personService = new PersonService();
        List<Items> itemsList1 = new ArrayList<>();
        List<Items> itemsList2 = new ArrayList<>();
        InvoiceListRepository repository = new InvoiceListRepository();

        Items item1 = new Television("SERIES1", "TYPE1", 1000, 50, "UA");
        Items item2 = new Television("SERIES2", "TYPE2", 2000, 20, "UA");
        Items item3 = new Television("SERIES3", "TYPE3", 3000, 10, "UA");
        Items item4 = new Telephone("SERIES4", "TYPE4", 100, "SAMSUNG");
        Items item5 = new Telephone("SERIES5", "TYPE5", 200, "NOKIA");
        itemsList1.add(item1);
        itemsList1.add(item2);
        itemsList1.add(item3);
        itemsList2.add(item4);
        itemsList2.add(item5);
        invoice1 = new Invoice(itemsList1, personService.randomCustomer());
        invoice2 = new Invoice(itemsList1, personService.randomCustomer());
        invoice3 = new Invoice(itemsList1, personService.randomCustomer());
        invoice4 = new Invoice(itemsList2, personService.randomCustomer());
        invoice5 = new Invoice(itemsList2, personService.randomCustomer());

        repository.save(invoice1);
        repository.save(invoice2);
        repository.save(invoice3);
        repository.save(invoice4);
        repository.save(invoice5);
        invoiceList = repository.getAll();

    }

    @Test
    void lowestInvoice() {
        int expected = 300;
        Assertions.assertEquals(expected, dataAnalytics.lowestInvoice(invoiceList));
    }

    @Test
    void allInvoiceSum() {
        int expected = 18600;
        Assertions.assertEquals(expected, dataAnalytics.allInvoiceSum(invoiceList));
    }

    @Test
    void allInvoiceSumNegative() {
        int expected = 6000;
        Assertions.assertNotEquals(expected, dataAnalytics.allInvoiceSum(invoiceList));
    }

    @Test
    void countRetailInvoices() {
        int expected = 2;
        Assertions.assertEquals(expected, dataAnalytics.countRetailInvoices(invoiceList));
    }

    @Test
    void countRetailInvoicesNegative() {
        int expected = 4;
        Assertions.assertNotEquals(expected, dataAnalytics.countRetailInvoices(invoiceList));
    }


    @Test
    void firstThreeInvoices() {
        List<Invoice> expected = new ArrayList<>();
        expected.add(invoice1);
        expected.add(invoice2);
        expected.add(invoice3);

        Assertions.assertEquals(expected, dataAnalytics.firstThreeInvoices(invoiceList));
    }

    @Test
    void firstThreeInvoicesNegative() {
        List<Invoice> expected = new ArrayList<>();
        expected.add(invoice1);
        expected.add(invoice2);
        expected.add(invoice5);
        Assertions.assertNotEquals(expected, dataAnalytics.firstThreeInvoices(invoiceList));
    }


    @Test
    void lessThanEighteenInvoice() {
        invoice1.getCustomer().setAge(20);
        invoice2.getCustomer().setAge(30);
        invoice3.getCustomer().setAge(14);
        invoice4.getCustomer().setAge(80);
        invoice5.getCustomer().setAge(16);
        List<Invoice> expected = new ArrayList<>();
        expected.add(invoice3);
        expected.add(invoice5);

        Assertions.assertEquals(expected, dataAnalytics.lessThanEighteenInvoice(invoiceList));
    }

    @Test
    void lessThanEighteenInvoiceNegative() {
        invoice1.getCustomer().setAge(20);
        invoice2.getCustomer().setAge(30);
        invoice3.getCustomer().setAge(14);
        invoice4.getCustomer().setAge(80);
        invoice5.getCustomer().setAge(16);
        List<Invoice> expected = new ArrayList<>();
        expected.add(invoice1);
        expected.add(invoice2);
        expected.add(invoice3);
        expected.add(invoice4);
        expected.add(invoice5);

        Assertions.assertNotEquals(expected, dataAnalytics.lessThanEighteenInvoice(invoiceList));
    }

    @Test
    void distinctItemInvoice() {
        List<Invoice> expected = new ArrayList<>();
        expected.add(invoice1);
        expected.add(invoice2);
        expected.add(invoice3);
        expected.add(invoice4);
        expected.add(invoice5);
        Assertions.assertEquals(expected, dataAnalytics.distinctItemInvoice(invoiceList));
    }
    @Test
    void distinctItemInvoiceNegative() {
        List<Invoice> expected = new ArrayList<>();
        invoice1.getItemsList().get(1).setItemsType(ItemsType.TELEVISION);
        expected.add(invoice1);
        expected.add(invoice2);
        expected.add(invoice3);
        expected.add(invoice4);
        expected.add(invoice5);
        Assertions.assertNotEquals(expected, dataAnalytics.distinctItemInvoice(invoiceList));
    }


}
