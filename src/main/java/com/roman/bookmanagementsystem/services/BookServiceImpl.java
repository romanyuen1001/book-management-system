package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> findByAuthorAndPublished(String author, boolean published) {
        return bookRepository.findByAuthorAndPublished(author, published);
    }

    @Override
    public void createBooks(List<BookDto> bookDto) {
        int batchSize = 100; // Adjustable

        List<List<Book>> batches = bookDto.stream()
                .map(dto -> {
                    Book book = new Book();
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
}
