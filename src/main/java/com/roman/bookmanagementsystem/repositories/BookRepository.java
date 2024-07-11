package com.roman.bookmanagementsystem.repositories;

import com.roman.bookmanagementsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE (:author is null OR b.author = :author) AND (:published is null OR b.published = :published)")
    List<Book> findByAuthorAndPublished(@Param("author") String author, @Param("published") Boolean published);
}