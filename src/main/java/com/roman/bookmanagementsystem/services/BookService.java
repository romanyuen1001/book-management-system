package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    void createBooks(ArrayList<BookDto> bookDto);
}
