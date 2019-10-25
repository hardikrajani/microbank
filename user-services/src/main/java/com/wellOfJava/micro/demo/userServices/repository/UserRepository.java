package com.wellOfJava.micro.demo.userServices.repository;

import com.wellOfJava.micro.demo.userServices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
