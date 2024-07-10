package com.roman.bookmanagementsystem.Controllers;

import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.services.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/test")
    public String test() {
        return "Welcome to Book Management System API";
    }

    @RequestMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
