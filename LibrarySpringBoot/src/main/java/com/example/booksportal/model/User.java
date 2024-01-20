package com.example.booksportal.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utenti")
public class User {
    @NonNull
    @Size(min=2, max=20)
    private String name;
    @NonNull
    @Size(min=2, max=20)
    private String surname;
    @NonNull
    @Size(min=2, max=20)
    private String username;
    @NonNull
    @Size(min=8, max=20)
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public User() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
