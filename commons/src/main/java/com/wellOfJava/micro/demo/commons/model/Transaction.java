package com.wellOfJava.micro.demo.commons.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    private TransactionType type;
    private long accountId;
    private double amount;
}

