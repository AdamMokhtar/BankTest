package com.capgemini.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.rmi.server.UID;
import java.util.*;

@Entity(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @Column(name = "customer_id", unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_surname")
    private String surname;
    @Column(name = "customer_address")
    private String address;
    @Column(name = "customer_dob")
    private Date dob;

    @OneToMany(targetEntity = Account.class) //, fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonIgnore
    //@OnDelete(action= OnDeleteAction.CASCADE)
    private Set<Account> accounts = new HashSet<>();

    public Customer() {}


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAccounts(Set<Account> accounts) {
        accounts = accounts;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Date getDob() {
        return dob;
    }



    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", DOB=" + dob +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
