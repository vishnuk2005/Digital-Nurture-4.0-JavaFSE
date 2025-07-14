package com.library;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");

        bookService.displayAllBooks();

        System.out.println("\nAdding a new book...\n");
        bookService.registerNewBook("Java Concurrency in Practice");

        System.out.println("\nUpdated book list:");
        bookService.displayAllBooks();
    }
}
