package com.capgemini.bank.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Entity(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", unique=true)
    private UUID id;

    @Column(name = "customer_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private UUID customerId;

    @Column(name = "balance")
    private float balance;


    @OneToMany(targetEntity = Transaction.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @JsonIgnore
    //@OnDelete(action= OnDeleteAction.CASCADE)
    private Set<Transaction> transactions = new HashSet<>();


    public Account(){}


    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    public boolean SubtractBalance(float balance) {
        if( 0 <= (this.balance - balance)){
            this.balance =this.balance - balance;
            return true;
        }
        return false;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void AddToBalance(float balance) {
      this.balance += balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + id +
//                ", customer=" + CustomerAccount +
                ", Balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
