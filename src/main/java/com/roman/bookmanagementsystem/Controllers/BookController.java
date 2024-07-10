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
import java.util.Optional;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private final BookServiceImpl bookServiceImpl;

    // TODO: Filter by author and published status
    @GetMapping()
    public List<Book> getAllBooks() {
        return bookServiceImpl.getAllBooks();
    }

    @GetMapping()
    @ResponseBody
    public Optional<Book> getBookById(@RequestParam Long id) { // TODO: dto in controller
        return bookServiceImpl.getBookById(id);
        // TODO: Err msg handle for frontend
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<Object> createBooks(@RequestBody ArrayList<BookDto> bookDtoArrayList) {
        bookServiceImpl.createBooks(bookDtoArrayList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // TODO: Delete by id
}
