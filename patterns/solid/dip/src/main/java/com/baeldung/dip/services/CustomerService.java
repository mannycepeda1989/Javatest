package com.baeldung.dip.services;

import com.baeldung.dip.daointerfaces.CustomerDao;
import com.baeldung.dip.entities.Customer;
import java.util.Map;
import java.util.Optional;

public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Optional<Customer> findById(int id) {
        return customerDao.findById(id);
    }

    public Map<Integer, Customer> findAll() {
        return customerDao.findAll();
    }
}
