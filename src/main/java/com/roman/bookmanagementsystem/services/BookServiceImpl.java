package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.repositories.BookRepository;
import com.roman.bookmanagementsystem.services.validate.ValidateService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    private final ValidateService validateService;

    private final int batchSize = 100; // Adjustable

    @Override
    public List<Book> findByAuthorAndPublished(String author, boolean published) {
        return bookRepository.findByAuthorAndPublished(author, published);
    }

    @Override
    public List<Book> createBooks(List<BookDto> bookDtoList) {
        List<Book> validBooks = new ArrayList<>();
        List<Book> nonValidBooks = new ArrayList<>();

        for (BookDto dto : bookDtoList) {
            try {
                validateService.validateAuthor(dto.getAuthor());
                validateService.validateTitle(dto.getTitle());

                Book book = new Book();
                book.setAuthor(dto.getAuthor());
                book.setTitle(dto.getTitle());
                book.setPublished(dto.getPublished());

                validBooks.add(book);
            } catch (IllegalArgumentException ex) {
                // Handle invalid record (e.g., log or notify)
                System.out.println("Invalid book: " + dto.getTitle() + " - " + ex.getMessage());

                // Optionally, you can create a Book object representing the invalid record
                // and add it to the nonValidBooks list
                nonValidBooks.add(createInvalidBook(dto, ex.getMessage()));
            }
        }

        // Divide valid books into batches for insertion
        List<List<Book>> batches = partitionList(validBooks, batchSize);

        // Insert batches into the database
        for (List<Book> batch : batches) {
            try {
                bookRepository.saveAll(batch);
            } catch (Exception ex) {
                // Handle exception during insertion (if needed)
                throw new RuntimeException("Failed to save batch of books", ex);
            }
        }

        // Return nonValidBooks if needed
        return nonValidBooks;
    }

    // Utility method to partition a list into batches
    private <T> List<List<T>> partitionList(List<T> list, int batchSize) {
        return list.stream()
                .collect(Collectors.groupingBy(index -> list.indexOf(index) / batchSize))
                .values().stream()
                .collect(Collectors.toList());
    }

    // Helper method to create an invalid Book object for logging or returning in response
    private Book createInvalidBook(BookDto dto, String errorMessage) {
        Book book = new Book();
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setPublished(dto.getPublished());
        return book;
    }

    @Override
    public void deleteBook(Long id) throws IllegalArgumentException {
        validateService.validateBookId(id);
        existById(id);
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> updateBookPublishedStatus(Long id, boolean published) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setPublished(published);
            bookRepository.save(book);
            return Optional.of(book);
        } else {
            return Optional.empty();
        }
    }

    private void existById(long id){
        if (!bookRepository.existsById(id)) throw new ValidationException("Book Id: " + id + " does not exist");
    }
}
