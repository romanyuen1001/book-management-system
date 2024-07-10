package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    void createBook(BookDto bookDto);
    void createBooks(ArrayList<BookDto> bookDto);
}
