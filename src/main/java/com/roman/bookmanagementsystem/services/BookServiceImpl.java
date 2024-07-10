package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void createBooks(ArrayList<BookDto> bookDto) {
        int batchSize = 100; // Adjustable

        List<List<Book>> batches = bookDto.stream()
                .map(dto -> {
                    Book book = new Book();
                    book.setId(UUID.randomUUID().toString());
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

}
