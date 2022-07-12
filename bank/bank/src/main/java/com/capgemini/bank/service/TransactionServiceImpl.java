package com.capgemini.bank.service;

import com.capgemini.bank.exception.TargetNotFoundException;
import com.capgemini.bank.model.Account;
import com.capgemini.bank.model.Transaction;
import com.capgemini.bank.repository.AccountRepository;
import com.capgemini.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Optional<Transaction> GetTransaction(UUID id) {
        return Optional.ofNullable(transactionRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(id)));
    }

    @Override
    public void DeleteTransaction(UUID id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction PostTransaction(Transaction transaction) {
        //Subtract from the sender and add to receiver
        Account sender = accountRepository.findById(transaction.getAccountId()).
                orElseThrow(() -> new TargetNotFoundException(transaction.getAccountId()));

        Account receiver = accountRepository.findById(transaction.getReceiverId()).
                orElseThrow(() -> new TargetNotFoundException(transaction.getReceiverId()));

        if(sender.SubtractBalance(transaction.getAmount())){

            receiver.AddToBalance(transaction.getAmount());
            transaction.setReceiverId(transaction.getReceiverId());

            accountRepository.saveAndFlush(sender);
            accountRepository.saveAndFlush(receiver);
            return transactionRepository.saveAndFlush(transaction);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not Enough Balance");
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
}
