package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> findByAuthorAndPublished(String author, boolean published) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> findByPublished(boolean published) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        genBook();
        return bookRepository.findAll();
    }

    @Override
    public void createBooks(ArrayList<BookDto> bookDto) {
        int batchSize = 100; // Adjustable

        List<List<Book>> batches = bookDto.stream()
                .map(dto -> {
                    Book book = new Book();
                    book.setId(uuidToLong(UUID.randomUUID()));
                    book.setAuthor(dto.author);
                    book.setTitle(dto.title);
                    book.setPublished(dto.published);
                    return book;
                })
                .collect(Collectors.partitioningBy(
                        b -> bookDto.indexOf(b) % batchSize == 0,
                        Collectors.toList()
                ))
                .values()
                .stream()
                .collect(Collectors.toList());

        for (List<Book> batch : batches) {
            // TODO: Check exist by author and title, consider id is generated
            bookRepository.saveAll(batch);
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Temp func for testing
    private void genBook() {
        Book book = new Book();
        book.setId(uuidToLong(UUID.randomUUID()));
        book.setTitle("Test Title");
        book.setAuthor("Test Author");
        book.setPublished(false);
        bookRepository.save(book);
    }

    private long uuidToLong(UUID uuid) {
        long longValue = uuid.getMostSignificantBits();
        longValue <<= 32;
        longValue |= uuid.getLeastSignificantBits();
        return longValue;
    }
}
