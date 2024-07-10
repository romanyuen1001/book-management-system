package com.roman.bookmanagementsystem.Controllers;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.services.BookServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private final BookServiceImpl bookServiceImpl;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String author,
                               @RequestParam(required = false) Boolean published) {
        List<Book> books;

        if (author != null && published != null) {
            books = bookServiceImpl.findByAuthorAndPublished(author, published);
        } else if (author != null) {
            books = bookServiceImpl.findByAuthor(author);
        } else if (published != null) {
            books = bookServiceImpl.findByPublished(published);
        } else {
            books = bookServiceImpl.findAll();
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> createBooks(@RequestBody ArrayList<BookDto> bookDtoArrayList) {
        bookServiceImpl.createBooks(bookDtoArrayList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookServiceImpl.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
