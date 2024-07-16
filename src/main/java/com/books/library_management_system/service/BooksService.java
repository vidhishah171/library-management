package com.books.library_management_system.service;

import java.util.List;

import com.books.library_management_system.entity.Book;
import com.books.library_management_system.exception.BooksException;

import jakarta.validation.Valid;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
public interface BooksService {

  /**
   * @author Vidhi_s Method to add new book.
   * 
   * @param book
   * @return
   * @throws BooksException
   */
  Book addBook(@Valid Book book) throws BooksException;

  /**
   * @author Vidhi_s Method to get book by id.
   * 
   * @param id
   * @return
   * @throws BooksException
   */
  Book getBookById(String id) throws BooksException;

  /**
   * @author Vidhi_s Method to update book by id.
   * 
   * @param id
   * @param book
   * @return
   * @throws BooksException
   */
  Book updateBook(String id, Book book) throws BooksException;

  /**
   * @author Vidhi_s Method to delete book by id.
   * 
   * @param id
   * @return
   * @throws BooksException
   */
  String deleteBookById(String id) throws BooksException;

  /**
   * @author Vidhi_s Method to fetch all the books.
   * 
   * @return
   */
  List<Book> listAllBooks();

  /**
   * @author Vidhi_s Method to remove book by isbn number.
   * 
   * @param isbn
   * @return
   * @throws BooksException
   */
  String removeBook(String isbn) throws BooksException;

  /**
   * @author Vidhi_s Method to find book by title.
   * 
   * @param title
   * @return
   * @throws BooksException
   */
  List<Book> findBookByTitle(String title) throws BooksException;

  /**
   * @author Vidhi_s Method to find book by author.
   * 
   * @param author
   * @return
   * @throws BooksException
   */
  List<Book> findBookByAuthor(String author) throws BooksException;

  /**
   * @author Vidhi_s Method to list all the available books.
   * 
   * @return
   * @throws BooksException
   */
  List<Book> listAvailableBooks() throws BooksException;
}
