package com.example.booksportal.dao;

import com.example.booksportal.model.Book;
import java.util.List;import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DaoBook extends CrudRepository<Book, Long> {
    Book findById(long id);

    @Override
    Iterable<Book> findAll();

    List<Book> findBookByTitle (String title);


}
