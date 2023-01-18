package com.ozorkin.repository;

import com.ozorkin.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface Repository <T> {
    void save(final T invoice);

    List<Invoice> getAll();

    Optional<Invoice> getById(final String id);

    void delete(final String id);

}
