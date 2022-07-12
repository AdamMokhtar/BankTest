package com.capgemini.bank.repository;

import com.capgemini.bank.model.Customer;
import com.capgemini.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
