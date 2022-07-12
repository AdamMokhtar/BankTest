package com.capgemini.bank.controller;

import com.capgemini.bank.model.Account;
import com.capgemini.bank.model.Transaction;
import com.capgemini.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> list()
    {
        return accountService.getAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID id) {
        accountService.DeleteAccount(id);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Optional<Account> get(@PathVariable UUID id) {
        return accountService.GetAccount(id);
    }

    @GetMapping
    @RequestMapping("{id}/transaction")
    public Set<Transaction> getAccounts(@PathVariable UUID id) {
        return accountService.GetAccountTransactions(id);
    }


    @PostMapping(value = "{id}")
    public Account create(@PathVariable final UUID id, @RequestBody final Account account)
    {
        return accountService.PostAccount(id,account);
    }


}
