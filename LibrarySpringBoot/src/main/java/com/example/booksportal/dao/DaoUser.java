package com.example.booksportal.dao;

import com.example.booksportal.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DaoUser extends CrudRepository <User, Long> {

    @Query(value = "select s from User s where username= :username and password = :password")
    public User login(String username, String password);
}
