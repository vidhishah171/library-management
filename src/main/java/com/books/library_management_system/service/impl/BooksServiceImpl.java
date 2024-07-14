package com.books.library_management_system.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.books.library_management_system.entity.Book;
import com.books.library_management_system.exception.BookNotFoundException;
import com.books.library_management_system.exception.BooksException;
import com.books.library_management_system.repo.BooksRepo;
import com.books.library_management_system.service.BooksService;
import com.books.library_management_system.util.LibraryUtil;
import com.mongodb.DuplicateKeyException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BooksServiceImpl implements BooksService {

  @Autowired
  private BooksRepo booksRepo;

  @Override
  public Book addBook(Book book) throws BooksException {

    if (Objects.nonNull(book)) {
      log.info(LibraryUtil.logFormat("Adding the book with title: {} to database.",
          new String[] {book.getTitle()}));
      try {
        book = this.booksRepo.save(book);
      } catch (DuplicateKeyException e) {
        throw new BooksException("Book with ISBN " + book.getIsbn() + " already exists.");
      }
      return book;
    } else {
      throw new BooksException("Book object must not be null.");
    }
  }

  @Override
  public Book addBook(String author, String title, int publicationYear) throws BooksException {

    // Assert.hasLength(author, "Book Author must not be null.");
    // Assert.hasLength(title, "Book Title must not be null.");
    // Assert.notNull(publicationYear, "Book publication year must not be null.");
    // log.info(
    // LibraryUtil.logFormat("Adding the book with title: {} to database.", new String[] {title}));
    // BookBuilder booksBuilder = Book.builder()
    // .author(author)
    // .title(title)
    // .publicationYear(publicationYear);
    // try {
    // Book book = booksBuilder.build();
    // book = this.booksRepo.save(book);
    // } catch(DuplicateKeyException e) {
    // throw new BooksException("Book with ISBN " + book.getIsbn() + " already exists.");
    //
    // }
    return null;
  }

  @Override
  public Book getBook(String id) throws BooksException {

    if (Objects.nonNull(id)) {
      log.info(LibraryUtil.logformat("Fetching the book with Id: {} from database."), id);
      Optional<Book> bookOptional = this.booksRepo.findById(id);
      if (bookOptional.isPresent()) {
        return bookOptional.get();
      } else {
        throw new BookNotFoundException("Unable to find book with Id: " + id + ".");
      }
    } else {
      throw new BooksException("Id must not be null for fetching books.");
    }
  }

  @Override
  public List<Book> findBookByTitle(String title) throws BooksException {

    if (StringUtils.isNotBlank(title)) {
      log.info(LibraryUtil.logformat("Fetching the book with Title: {} from database."), title);
      List<Book> books = this.booksRepo.findAllByTitleIgnoreCase(title);
      if (!CollectionUtils.isEmpty(books)) {
        return books;
      } else {
        throw new BookNotFoundException(
            "Book with title: " + title + " is not present in database.");
      }
    } else {
      throw new BooksException("Title must not be null for fetching books.");
    }
  }

  @Override
  public List<Book> findBookByAuthor(String author) throws BooksException {

    if (StringUtils.isNotBlank(author)) {
      log.info(LibraryUtil.logformat("Fetching the book with Author: {} from database."), author);
      List<Book> books = this.booksRepo.findAllByAuthorIgnoreCase(author);
      if (!CollectionUtils.isEmpty(books)) {
        return books;
      } else {
        throw new BookNotFoundException(
            "Book with author: " + author + " is not present in database.");
      }
    } else {
      throw new BooksException("Author must not be null for fetching books.");
    }
  }

  @Override
  public List<Book> listAvailableBooks() throws BooksException {

    log.info(LibraryUtil.logformat("Fetching the available books from database."));
    List<Book> books = this.booksRepo.findAllByAvailabilityTrue();
    if (!CollectionUtils.isEmpty(books)) {
      return books;
    } else {
      throw new BookNotFoundException("There are no available books present in database.");
    }
  }

  @Override
  public List<Book> listAllBooks() {

    log.info(LibraryUtil.logFormat("Fetching all the books from database.", null));
    return this.booksRepo.findAll();
  }

  @Override
  public Book updateBook(String id, Book book) throws BooksException {

    try {
      Book oldBook = this.getBook(id);
      log.info(
          LibraryUtil.logFormat("Updating the book with Id: {} to database.", new String[] {id}));
      oldBook.setAuthor(book.getAuthor());
      oldBook.setTitle(book.getTitle());
      oldBook.setPublicationYear(book.getPublicationYear());
      return this.booksRepo.save(oldBook);
    } catch (BooksException e) {
      throw new BooksException(
          "Unable to update book with Id: " + id + "due to reason: " + e.getMessage());
    }
  }

  @Override
  public String deleteBook(String id) throws BooksException {

    if (Objects.nonNull(id)) {
      if (this.booksRepo.existsById(id)) {
        this.booksRepo.deleteById(id);
        return "Book with Id: " + id + " deleted successfully.";
      } else {
        throw new BookNotFoundException("Unable to find book with Id: " + id + ".");
      }
    } else {
      throw new BooksException("Id must not be null for fetching books.");
    }
  }

  @Override
  public String removeBook(String isbn) throws BooksException {

    if (StringUtils.isNotBlank(isbn)) {
      if (this.booksRepo.existsByIsbn(isbn)) {
        this.booksRepo.deleteByIsbn(isbn);
        return "Book with ISBN: " + isbn + " deleted successfully.";
      } else {
        throw new BookNotFoundException("Unable to find book with ISBN: " + isbn + ".");
      }
    } else {
      throw new BooksException("ISBN must not be null for fetching books.");
    }
  }
}
