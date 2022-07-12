package com.capgemini.bank.controller;

import com.capgemini.bank.model.Account;
import com.capgemini.bank.model.Customer;
import com.capgemini.bank.repository.CustomerRepository;
import com.capgemini.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> list()
    {
        return customerService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Optional<Customer> get(@PathVariable UUID id) {
        return customerService.GetCustomer(id);
    }

    @GetMapping
    @RequestMapping("{id}/account")
    public Set<Account> getAccounts(@PathVariable UUID id) {
        return customerService.GetCustomerAccounts(id);
    }

    @PostMapping
    public Customer create(@RequestBody final Customer customer)
    {
        return customerService.PostCustomer(customer);
    }

    @PutMapping("{id}")
    public Customer updateCustomer(@RequestBody Customer newCus, @PathVariable UUID id) {
        return customerService.updateCustomer(newCus,id);}

    @PutMapping("name/{id}")
    public Customer updateName(@RequestBody Customer newCus, @PathVariable UUID id) {
        return customerService.updateCustomerName(newCus,id);}
}
