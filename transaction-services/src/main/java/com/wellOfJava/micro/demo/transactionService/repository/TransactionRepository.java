package com.wellOfJava.micro.demo.transactionService.repository;

import com.wellOfJava.micro.demo.commons.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
