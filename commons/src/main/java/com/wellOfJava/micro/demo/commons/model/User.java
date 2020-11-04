package com.wellOfJava.micro.demo.commons.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column
    private String userName;
}
