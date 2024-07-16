package com.books.library_management_system.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.books.library_management_system.bo.GenericResponse;
import com.books.library_management_system.entity.Book;
import com.books.library_management_system.exception.BooksException;
import com.books.library_management_system.service.BooksService;
import com.books.library_management_system.util.LibraryUtil;

import jakarta.validation.Valid;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
@RestController
@RequestMapping("/books")
public class BooksController {

  @Autowired
  private BooksService booksService;

  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<GenericResponse> addBook(@RequestBody @Valid Book bookObject)
      throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.addBook(bookObject),
        HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> fetchBookById(@PathVariable String id)
      throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.getBookById(id), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> listAllBooks() throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.listAllBooks(), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> updateBook(@PathVariable String id,
      @RequestBody @Valid Book bookObject) throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.updateBook(id, bookObject),
        HttpStatus.ACCEPTED);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> deleteBookById(@PathVariable String id)
      throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.deleteBookById(id), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "remove/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> removeBook(@PathVariable String isbn)
      throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.removeBook(isbn), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/find/byTitle/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> findBookByTitle(@PathVariable String title)
      throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.findBookByTitle(title),
        HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/find/byAuthor/{author}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> findBookByAuthor(@PathVariable String author)
      throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.findBookByAuthor(author),
        HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/list/available", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> listAvailableBooks() throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.listAvailableBooks(), HttpStatus.OK);
  }
}
