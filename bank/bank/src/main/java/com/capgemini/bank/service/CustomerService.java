package com.capgemini.bank.service;

import com.capgemini.bank.model.Account;
import com.capgemini.bank.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public interface CustomerService {
    public Optional<Customer> GetCustomer(UUID id);
    public Customer PostCustomer(Customer customer);
    public List<Customer> getAll();
    public Customer updateCustomer(Customer newCus,  UUID id);

    public Customer updateCustomerName(Customer newCus,  UUID id);

    public Set<Account> GetCustomerAccounts(UUID id);
}
