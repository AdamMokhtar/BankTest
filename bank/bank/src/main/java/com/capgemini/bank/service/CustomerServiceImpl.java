package com.capgemini.bank.service;

import com.capgemini.bank.exception.TargetNotFoundException;
import com.capgemini.bank.model.Account;
import com.capgemini.bank.model.Customer;
import com.capgemini.bank.repository.AccountRepository;
import com.capgemini.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;



    @Override
    public Optional<Customer> GetCustomer(UUID id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer PostCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer newCus, UUID id) {
        return customerRepository.findById(id)
        .map(cus -> {
            cus.setName(newCus.getName());
            cus.setAddress(newCus.getAddress());
            cus.setSurname(newCus.getSurname());
            cus.setDob(newCus.getDob());
            return customerRepository.save(cus);
        })
        .orElseGet(() -> {
            return customerRepository.save(newCus);
        });
    }

    @Override
    public Customer updateCustomerName(Customer newCus, UUID id) {
        return customerRepository.findById(id)
                .map(cus -> {
                    cus.setName(newCus.getName());
                    return customerRepository.save(cus);
                })
                .orElseGet(() -> {
                    return customerRepository.save(newCus);
                });
    }

    @Override
    public Set<Account> GetCustomerAccounts(UUID id) {
        Customer cus = customerRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(id));
        return cus.getAccounts();
    }

}
