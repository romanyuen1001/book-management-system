package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findByAuthorAndPublished(String author, boolean published);
    List<Book> createBooks(List<BookDto> bookDto);
    void deleteBook(Long id) throws IllegalArgumentException;
    Optional<Book> updateBookPublishedStatus(Long id, boolean published);
}
