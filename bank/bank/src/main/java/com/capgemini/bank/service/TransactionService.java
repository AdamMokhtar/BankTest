package com.capgemini.bank.service;

import com.capgemini.bank.model.Account;
import com.capgemini.bank.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface TransactionService {
    public Optional<Transaction> GetTransaction(UUID id);
    public void DeleteTransaction(UUID id);
    public Transaction PostTransaction(Transaction transaction);
    public List<Transaction> getAll();
}
