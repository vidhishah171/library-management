package com.books.library_management_system.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.books.library_management_system.entity.Book;

@Repository
public interface BooksRepo extends MongoRepository<Book, String> {

  boolean existsByIsbn(@Param("isbn") String isbn);

  boolean deleteByIsbn(@Param("isbn") String isbn);

  List<Book> findAllByTitleIgnoreCase(@Param("title") String title);

  List<Book> findAllByAuthorIgnoreCase(@Param("author") String author);

  List<Book> findAllByAvailabilityTrue();
}
