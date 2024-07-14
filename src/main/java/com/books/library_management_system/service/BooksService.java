package com.books.library_management_system.service;

import java.util.List;

import com.books.library_management_system.entity.Book;
import com.books.library_management_system.exception.BooksException;

public interface BooksService {

  /**
   * @author Vidhi_s Method to add new book.
   * 
   * @param book
   * @return
   * @throws BooksException
   */
  Book addBook(Book book) throws BooksException;

  /**
   * @author Vidhi_s Method to add new book by parameters.
   * 
   * @param author
   * @param title
   * @param publicationYear
   * @return
   * @throws BooksException
   */
  Book addBook(String author, String title, int publicationYear) throws BooksException;

  /**
   * @author Vidhi_s Method to get book by id.
   * 
   * @param id
   * @return
   * @throws BooksException
   */
  Book getBook(String id) throws BooksException;

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
  String deleteBook(String id) throws BooksException;

  /**
   * @author Vidhi_s Method to fetch all the books.
   * 
   * @return
   */
  List<Book> listAllBooks();

  /**
   * @param isbn
   * @return
   * @throws BooksException
   */
  String removeBook(String isbn) throws BooksException;

  List<Book> findBookByTitle(String title) throws BooksException;

  List<Book> findBookByAuthor(String author) throws BooksException;

  List<Book> listAvailableBooks() throws BooksException;
}
