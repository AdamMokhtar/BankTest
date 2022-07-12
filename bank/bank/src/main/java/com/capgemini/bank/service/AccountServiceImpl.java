package com.capgemini.bank.service;

import com.capgemini.bank.exception.TargetNotFoundException;
import com.capgemini.bank.model.Account;
import com.capgemini.bank.model.Customer;
import com.capgemini.bank.model.Transaction;
import com.capgemini.bank.repository.AccountRepository;
import com.capgemini.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> GetAccount(UUID id)
    {
        return Optional.ofNullable(accountRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(id)));
    }

    @Override
    public Set<Transaction> GetAccountTransactions(UUID id) {
        Account acc = accountRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(id));
        return acc.getTransactions();
    }

    @Override
    public void DeleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account PostAccount(UUID id, Account account) {
        account.setCustomerId(id);
        return accountRepository.saveAndFlush(account);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }


}
