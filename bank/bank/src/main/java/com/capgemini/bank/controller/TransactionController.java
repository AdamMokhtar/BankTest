package com.capgemini.bank.controller;

import com.capgemini.bank.model.Account;
import com.capgemini.bank.model.Transaction;
import com.capgemini.bank.service.AccountService;
import com.capgemini.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> list()
    {
        return transactionService.getAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID id) {
        transactionService.DeleteTransaction(id);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Optional<Transaction> get(@PathVariable UUID id) {
        return transactionService.GetTransaction(id);
    }


    @PostMapping
    public Transaction create(@RequestBody final Transaction transaction)
    {
        return transactionService.PostTransaction(transaction);
    }
}
