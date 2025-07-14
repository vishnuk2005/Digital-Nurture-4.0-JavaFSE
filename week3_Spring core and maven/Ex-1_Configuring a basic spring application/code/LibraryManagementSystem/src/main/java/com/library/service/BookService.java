package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayBooks() {
        System.out.println("Books in Library:");
        for (String book : bookRepository.findAllBooks()) {
            System.out.println("- " + book);
        }
    }

    public void issueBook(String bookName) {
        System.out.println("Issuing book: " + bookName);
    }

        public void addNewBook(String bookName) {
            bookRepository.addBook(bookName);
        }

        public void deleteBook(String bookName) {
            bookRepository.deleteBook(bookName);
        }

    }

