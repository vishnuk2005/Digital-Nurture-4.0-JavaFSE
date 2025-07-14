
package com.library.main;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.displayBooks();
        bookService.issueBook("1984");
        bookService.addNewBook("Brave New World");
        bookService.displayBooks();
    }
}
