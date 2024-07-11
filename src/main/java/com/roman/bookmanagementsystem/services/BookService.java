package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findByAuthorAndPublished(String author, boolean published);
    void createBooks(List<BookDto> bookDto);
    void deleteBook(Long id);
    Optional<Book> updateBookPublishedStatus(Long id, boolean published);
}
