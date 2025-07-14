package com.library.service;

import com.library.repository.BookRepository;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    // Setter for Spring DI
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayAllBooks() {
        List<String> books = bookRepository.findAllBooks();
        System.out.println("Books in the library:");
        books.forEach(book -> System.out.println("- " + book));
    }

    public void registerNewBook(String title) {
        bookRepository.addBook(title);
    }
}

