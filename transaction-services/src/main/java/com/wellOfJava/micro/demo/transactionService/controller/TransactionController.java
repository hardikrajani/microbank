package com.wellOfJava.micro.demo.transactionService.controller;

import com.wellOfJava.micro.demo.transactionService.exception.TransactionNotFoundException;
import com.wellOfJava.micro.demo.transactionService.model.Transaction;
import com.wellOfJava.micro.demo.transactionService.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

/*
    // Get All method is not needed for transactions.
    @GetMapping("/")
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }*/

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);

        if(!transaction.isPresent())
            throw new TransactionNotFoundException("transactionId="+id);

        return transaction.get();
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable long id) {
        transactionRepository.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTransaction.getTransactionId()).toUri();

        return ResponseEntity.created(location).build();

    }
/*
    // Update method is not needed for transactions.
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction, @PathVariable long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        if(!optionalTransaction.isPresent())
            throw new TransactionNotFoundException("transactionId="+id);


        transaction.setTransactionId(id);

        transactionRepository.save(transaction);

        return ResponseEntity.ok(transaction);
    }*/
}
