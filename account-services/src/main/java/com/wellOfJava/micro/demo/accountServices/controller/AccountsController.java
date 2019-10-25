package com.wellOfJava.micro.demo.accountServices.controller;

import com.wellOfJava.micro.demo.accountServices.exception.AccountNotFoundException;
import com.wellOfJava.micro.demo.accountServices.model.Account;
import com.wellOfJava.micro.demo.accountServices.repository.AccountsRepository;
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
@RequestMapping("/api/v1//accounts")
public class AccountsController {
    @Autowired
    AccountsRepository accountsRepository;

    @GetMapping("/")
    public List<Account> getAll() {
        return accountsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable long id) {
        Optional<Account> account = accountsRepository.findById(id);

        if(!account.isPresent())
            throw new AccountNotFoundException("accountId=" + id);

        return account.get();
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable long id) {
        accountsRepository.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
        Account savedAccount = accountsRepository.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAccount.getUserId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account, @PathVariable long id) {
        Optional<Account> accountOptional = accountsRepository.findById(id);

        if(!accountOptional.isPresent())
            throw new AccountNotFoundException("accountId=" + id);

        account.setAccountId(id);

        accountsRepository.save(account);

        return ResponseEntity.ok(account);
    }
}
