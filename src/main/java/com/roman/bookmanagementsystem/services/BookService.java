package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;

import java.util.List;

public interface BookService {
    List<Book> findByAuthorAndPublished(String author, boolean published);
    void createBooks(List<BookDto> bookDto);
    void deleteBook(Long id);
}
