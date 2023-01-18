package com.ozorkin.service;

import com.ozorkin.model.Customer;

import java.util.Random;
import java.util.UUID;

public class PersonService {
    private final Random random = new Random();

    public Customer randomCustomer() {
        Customer customer = new Customer();
        customer.setId(getUniqueId());
        customer.setEmail(generateRandomEmail());
        customer.setAge(random.nextInt(14, 100));

        return customer;
    }


    private String generateRandomEmail() {
        return String.format("%s@%s", getUniqueId(), "a-level.com.ua");
    }

    private String getUniqueId() {
        return String.format("%s_%s", UUID.randomUUID().toString().substring(0, 5), System.currentTimeMillis() / 1000);
    }
}
