package com.example.booksportal.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Size;

@Entity
@Table(name = "libri")
public class Book {

    @NonNull
    private String title;

    @NonNull
    private String author;

    @NonNull
    @Size(min=2, max=25)
    private String releaseYear;

    @NonNull
    private String publisher;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public Book() {
    }

    public Book(@NonNull String title, @NonNull String author, @NonNull String releaseYear, @NonNull String publisher) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
        this.publisher = publisher;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull String author) {
        this.author = author;
    }

    @NonNull
    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(@NonNull String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @NonNull
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(@NonNull String publisher) {
        this.publisher = publisher;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", publisher='" + publisher + '\'' +
                ", id=" + id +
                '}';
    }
}
