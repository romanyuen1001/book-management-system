package com.roman.bookmanagementsystem.services;

import com.roman.bookmanagementsystem.dtos.BookDto;
import com.roman.bookmanagementsystem.models.Book;
import com.roman.bookmanagementsystem.repositories.BookRepository;
import com.roman.bookmanagementsystem.services.validate.ValidateServiceImpl;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private ValidateServiceImpl validateServiceImpl;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    private List<Book> mockBooks;

    private Book existingBook;

    @BeforeEach
    public void setUp() {
        // Initialize mock data
        mockBooks = new ArrayList<>();
        mockBooks.add(new Book(1L, "Book 1", "Author 1", true));
        mockBooks.add(new Book(2L, "Book 2", "Author 2", false));
        mockBooks.add(new Book(3L, "Book 3", "Author 1", true));

        existingBook = new Book(1L, "Book 1", "Author 1", true);
    }

    @Test
    public void testFindByAuthorAndPublished() {
        // Mock behavior of repository.findByAuthorAndPublished() method
        String author = "Author 1";
        boolean published = true;
        List<Book> expectedBooks = List.of(mockBooks.get(0), mockBooks.get(2));
        when(bookRepository.findByAuthorAndPublished(author, published)).thenReturn(expectedBooks);

        // Call the method under test
        List<Book> foundBooks = bookServiceImpl.findByAuthorAndPublished(author, published);

        // Verify that repository.findByAuthorAndPublished() was called once
        verify(bookRepository, times(1)).findByAuthorAndPublished(author, published);

        // Assert the returned list matches the expectedBooks list
        assertEquals(expectedBooks.size(), foundBooks.size(), "Number of books should match");
        assertEquals(expectedBooks, foundBooks, "Found books should match expected books");
    }

    @Test
    public void testDeleteBook_BookExists() {
        // Mock behavior of validateService.validateBookId() method for an existing book
        Long existingBookId = 1L;
        doNothing().when(validateServiceImpl).validateBookId(existingBookId);

        // Mock behavior of existById() method to verify that book exists
        when(bookRepository.existsById(existingBookId)).thenReturn(true);

        // Call the method under test
        assertDoesNotThrow(() -> bookServiceImpl.deleteBook(existingBookId));

        // Verify that bookRepository.deleteById() was called once with the correct book ID
        verify(bookRepository, times(1)).deleteById(existingBookId);
    }

    @Test
    public void testDeleteBook_BookExistsButNotInRepository() {
        // Mock behavior of validateService.validateBookId() method for an existing book
        Long existingBookId = 1L;
        doNothing().when(validateServiceImpl).validateBookId(existingBookId);

        // Mock behavior of existById() method to verify that book does not exist in repository
        doThrow(new ValidationException("Book Id: " + existingBookId + " does not exist")).when(bookRepository).existsById(existingBookId);

        // Call the method under test and expect ValidationException
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            bookServiceImpl.deleteBook(existingBookId);
        });

        // Verify that bookRepository.deleteById() was never called
        verify(bookRepository, never()).deleteById(any());

        // Assert the exception message
        assertEquals("Book Id: " + existingBookId + " does not exist", exception.getMessage(), "Exception message should match");
    }

    @Test
    public void testUpdateBookPublishedStatus_BookExists() {
        // Mock behavior of bookRepository.findById() method for an existing book
        Long existingBookId = 1L;
        when(bookRepository.findById(existingBookId)).thenReturn(Optional.of(existingBook));

        // Call the method under test
        Optional<Book> updatedBookOptional = bookServiceImpl.updateBookPublishedStatus(existingBookId, false);

        // Verify that bookRepository.save() was called once with the correct book
        verify(bookRepository, times(1)).save(existingBook);

        // Assert that the returned Optional contains the updated book
        assertTrue(updatedBookOptional.isPresent(), "Optional should be present");
        assertEquals(false, updatedBookOptional.get().getPublished(), "Published status should be updated to false");
    }

    @Test
    public void testUpdateBookPublishedStatus_BookNotExists() {
        // Mock behavior of bookRepository.findById() method for a non-existent book
        Long nonExistingBookId = 999L;
        when(bookRepository.findById(nonExistingBookId)).thenReturn(Optional.empty());

        // Call the method under test
        Optional<Book> updatedBookOptional = bookServiceImpl.updateBookPublishedStatus(nonExistingBookId, true);

        // Verify that bookRepository.save() was not called
        verify(bookRepository, never()).save(any());

        // Assert that the returned Optional is empty
        assertFalse(updatedBookOptional.isPresent(), "Optional should be empty");
    }

    @Test
    public void testCreateBooks() {
        // Test data
        List<BookDto> bookDtoList = Arrays.asList(
                new BookDto("John Doe", "Java Programming", true),
                new BookDto("Jane Smith", "Python Basics", true)
        );

        // Call the method under test
        List<Book> nonValidBooks = bookServiceImpl.createBooks(bookDtoList);

        // Assertions
        assertNotNull(nonValidBooks);
        assertEquals(0, nonValidBooks.size());

        // Verify interactions
        verify(bookRepository, times(1)).saveAll(anyList()); // Verify that saveAll was called once
    }
}