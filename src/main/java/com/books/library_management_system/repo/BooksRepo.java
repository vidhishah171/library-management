package com.books.library_management_system.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.books.library_management_system.entity.Book;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
@Repository
public interface BooksRepo extends MongoRepository<Book, String> {

  /**
   * @param isbn
   * @return
   */
  boolean existsByIsbn(@Param("isbn") String isbn);

  /**
   * @param isbn
   */
  void deleteByIsbn(@Param("isbn") String isbn);

  /**
   * @param title
   * @return
   */
  List<Book> findAllByTitleIgnoreCase(@Param("title") String title);

  /**
   * @param author
   * @return
   */
  List<Book> findAllByAuthorIgnoreCase(@Param("author") String author);

  /**
   * @return
   */
  List<Book> findAllByAvailabilityTrue();
}
