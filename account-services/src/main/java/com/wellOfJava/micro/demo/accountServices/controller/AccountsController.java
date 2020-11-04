package com.wellOfJava.micro.demo.accountServices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.netflix.discovery.EurekaClient;
import com.wellOfJava.micro.demo.accountServices.exception.AccountNotFoundException;
import com.wellOfJava.micro.demo.accountServices.repository.AccountsRepository;
import com.wellOfJava.micro.demo.commons.constants.CommonConstants;
import com.wellOfJava.micro.demo.commons.model.Account;
import com.wellOfJava.micro.demo.commons.model.Transaction;
import com.wellOfJava.micro.demo.commons.model.TransactionType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private EurekaClient eurekaClient;
    
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${service.transaction.id}")
    private String transactionServiceId;

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

        Transaction transaction = new Transaction();
        transaction.setAccountId(savedAccount.getAccountId());
        transaction.setAmount(account.getBalance());
        transaction.setType(TransactionType.DEPOSIT);

        
		List<ServiceInstance> instances = discoveryClient.getInstances(transactionServiceId);
		ServiceInstance serviceInstance = instances.get(0);

//        Application application = eurekaClient.getApplication(transactionServiceId);
//        InstanceInfo instanceInfo = application.getInstances().get(0);
//        instanceInfo.get
//        String url = serviceInstance.getUri() + "/transaction-services/api/v1/transactions/";
        String url = serviceInstance.getUri() + "/api/v1/transactions/";
        System.out.println("URL" + url);
        ResponseEntity<Object> response = restTemplate.postForEntity(url, transaction, Object.class);
        System.out.println("RESPONSE " + response);

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
