
package com.library.repository;


import java.util.*;

public class BookRepository {
    private final List<String> books = new ArrayList<>();

    public BookRepository() {
        // Pre-populate with some books
        books.add("The Alchemist");
        books.add("1984");
        books.add("To Kill a Mockingbird");
    }

    public List<String> findAllBooks() {
        return books; // no longer hardcoded
    }


    public void addBook(String title) {
        books.add(title);
        System.out.println("Book added: " + title);
    }

    public void deleteBook(String title) {
        if (books.remove(title)) {
            System.out.println("Book removed: " + title);
        } else {
            System.out.println("Book not found: " + title);
        }
    }
}
