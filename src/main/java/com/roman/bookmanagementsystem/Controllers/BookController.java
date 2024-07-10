package com.roman.bookmanagementsystem.Controllers;

import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private final BookService bookService;

    @GetMapping("/test")
    public String test() {
        return "Welcome to Book Management System API";
    }

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
