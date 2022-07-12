package com.capgemini.bank.service;

import com.capgemini.bank.model.Account;
import com.capgemini.bank.model.Customer;
import com.capgemini.bank.model.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public interface AccountService {
    public Optional<Account> GetAccount(UUID id);
    public Set<Transaction> GetAccountTransactions(UUID id);
    public void DeleteAccount(UUID id);
    public Account PostAccount(UUID id, Account account);
    public List<Account> getAll();

}
