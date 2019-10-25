package com.wellOfJava.micro.demo.accountServices.repository;

import com.wellOfJava.micro.demo.accountServices.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
}
