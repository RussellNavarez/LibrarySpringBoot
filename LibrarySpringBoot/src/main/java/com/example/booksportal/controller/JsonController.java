package com.example.booksportal.controller;

import com.example.booksportal.dao.DaoBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/allbook")
@RestController
public class JsonController {

    @Autowired
    private DaoBook bookRepository;

    @GetMapping("/allbook")
    public String showJsonBooks() {
        return bookRepository.findAll().toString();
    }

    @GetMapping("/allbook/{title}")
    public String showBookByTitle(@PathVariable String title) {

        return bookRepository.findBookByTitle(title).toString();
    }





}
