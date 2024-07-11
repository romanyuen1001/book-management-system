package com.roman.bookmanagementsystem.controllers;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.services.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private final BookServiceImpl bookServiceImpl;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String author,
                               @RequestParam(required = false) Boolean published) {
        return ResponseEntity.ok(bookServiceImpl.findByAuthorAndPublished(author, published));
    }

    @PostMapping
    public ResponseEntity<Void> createBooks(@RequestBody List<BookDto> bookDtoList) {
        // TODO: Add validation
        bookServiceImpl.createBooks(bookDtoList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        // TODO: Consider check exist for error handle
        bookServiceImpl.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<Book> updateBookPublishedStatus(
            @PathVariable Long id,
            @RequestParam boolean published) {
        Optional<Book> updatedBook = bookServiceImpl.updateBookPublishedStatus(id, published);

        if (updatedBook.isPresent()) {
            return ResponseEntity.ok(updatedBook.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
