package com.library.repository;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final List<String> books = new ArrayList<>();

    public BookRepository() {
        books.add("Clean Architecture");
        books.add("Domain-Driven Design");
        books.add("The Pragmatic Programmer");
    }

    public List<String> findAllBooks() {
        return books;
    }

    public void addBook(String title) {
        books.add(title);
        System.out.println("Book added: " + title);
    }
}
