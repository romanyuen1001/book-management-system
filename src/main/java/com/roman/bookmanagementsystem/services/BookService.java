package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookService {
    List<Book> findByAuthorAndPublished(String author, boolean published);
    List<Book> findByAuthor(String author);
    List<Book> findByPublished(boolean published);
    List<Book> findAll();
    void createBooks(ArrayList<BookDto> bookDto);
    void deleteBook(Long id);
}
