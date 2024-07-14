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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.books.library_management_system.bo.GenericResponse;
import com.books.library_management_system.entity.Book;
import com.books.library_management_system.exception.BooksException;
import com.books.library_management_system.service.BooksService;
import com.books.library_management_system.util.LibraryUtil;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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

  @PostMapping(value = "/add/by-parameters", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<GenericResponse> addBookByParameters(@NotNull @RequestParam String title,
      @NotNull @RequestParam String author, @NotNull @RequestParam int publicationYear)
      throws BooksException {

    return LibraryUtil.buildSuccessResponse(
        this.booksService.addBook(author, title, publicationYear), HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> fetchBook(@PathVariable String id) throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.getBook(id), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> fetchAllBooks() throws BooksException {

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
  public ResponseEntity<GenericResponse> deleteBook(@PathVariable String id) throws BooksException {

    return LibraryUtil.buildSuccessResponse(this.booksService.deleteBook(id), HttpStatus.OK);
  }
}
