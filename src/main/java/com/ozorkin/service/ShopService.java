package com.ozorkin.service;

import com.ozorkin.model.*;

import com.ozorkin.repository.InvoiceListRepository;
import org.apache.commons.lang3.EnumUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ShopService {
    private final InvoiceListRepository invoiceRepository ;
    private static ShopService instance;
    private final Random random = new Random();

    public ShopService(final InvoiceListRepository repository) {
        this.invoiceRepository = repository;
    }

    public static ShopService getInstance() {
        if (instance == null) {
            instance = new ShopService(InvoiceListRepository.getInstance());
        }
        return instance;
    }


    public InvoiceListRepository getAllInvoices() {
        return invoiceRepository;
    }


    public void createInvoiceFromFile(final String path, final String delimeter) throws Exception {

        List<Items> itemsList = load(path, delimeter);
        List<Items> itemsListBound = new ArrayList<>();
        final int countOfItems = random.nextInt(3,5);
        for (int i = 0; i < countOfItems; i++) {
            itemsListBound.add(itemsList.get(random.nextInt(0,itemsList.size()-1)));
        }

        final Invoice invoice = new Invoice(itemsListBound, new PersonService().randomCustomer());
        invoiceRepository.save(invoice);
    }

    private  List<Items> load(final String path, final String delimeter) throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream(path);

        final List<String> rows = new BufferedReader(new InputStreamReader(Objects.requireNonNull(input))).lines().collect(Collectors.toList());

        final String[] columnsNames = rows.remove(0).split(delimeter);
        final List<Items> valuesList = new ArrayList<>();

        final Map<String, String> values = new HashMap<>();
        for (final String v : rows) {
            final String[] value = v.split(delimeter);
            for (int i = 0; i < value.length; i++) {
                if (value[i].isBlank()) {
                    try {
                        throw new FileReadException();
                    } catch (FileReadException e) {
                        System.out.println("Wrong arguments in file:\n" +
                               "key: " +  columnsNames[i] + "\n" +
                                "value: " + value[i] + "\n" +
                                 "row: " + v
                                );
                    }
                }
                values.put(columnsNames[i].trim(), value[i].trim());
            }
            valuesList.add(itemTypeChoiceAndCreate(values));
        }
        return valuesList;
    }

    private Items itemTypeChoiceAndCreate(final Map<String, String> map) {
        ItemsType itemsType = EnumUtils.getEnum(ItemsType.class, map.get("type"));

        if (itemsType == ItemsType.TELEPHONE) {
            return createPhone(map);

        }
        if (itemsType == ItemsType.TELEVISION) {
            createTV(map);
            return createTV(map);
        }
        return null;
    }

    private Telephone createPhone(final Map<String, String> map) {
        final Telephone telephone = (Telephone) createItem(ItemsType.TELEPHONE, map);
        telephone.setModel(map.get("model"));
        return telephone;
    }

    private Television createTV(final Map<String, String> map) {
        final Television television = (Television) createItem(ItemsType.TELEVISION, map);
        television.setCountry(map.get("country"));
        television.setDiagonal(Integer.parseInt(map.get("diagonal")));
        return television;
    }

    private Items createItem(final ItemsType itemsType, final Map<String, String> map) {
        Items items = null;
        if (itemsType == ItemsType.TELEPHONE) {
            items = new Telephone();
            items.setItemsType(ItemsType.TELEPHONE);
        } else if (itemsType == ItemsType.TELEVISION){
            items = new Television();
            items.setItemsType(ItemsType.TELEVISION);
        }
        items.setSeries(map.get("series"));
        items.setScreenType(map.get("screen type"));
        items.setPrice(Integer.parseInt(map.get("price")));
        return items;
    }


}















