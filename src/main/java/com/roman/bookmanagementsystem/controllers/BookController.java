package com.roman.bookmanagementsystem.controllers;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.services.BookServiceImpl;
import jakarta.validation.ValidationException;
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
                               @RequestParam(required = true) Boolean published) {
        return ResponseEntity.ok(bookServiceImpl.findByAuthorAndPublished(author, published));
    }

    @PostMapping
    public ResponseEntity<?> createBooks(@RequestBody List<BookDto> bookDtoList) {
        List<Book> nonValidBooks = bookServiceImpl.createBooks(bookDtoList);

        if (!nonValidBooks.isEmpty()) {
            return ResponseEntity.badRequest().body("Some books failed validation: " + nonValidBooks.size());
        }

        return ResponseEntity.ok("All books added successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookServiceImpl.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully.");
        } catch (IllegalArgumentException | ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
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
