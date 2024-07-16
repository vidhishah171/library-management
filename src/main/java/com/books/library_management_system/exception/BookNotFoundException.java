package com.books.library_management_system.exception;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
public class BookNotFoundException extends BooksException {

  private static final long serialVersionUID = 1L;

  public BookNotFoundException(String message) {

    super(message);
  }

  public BookNotFoundException(final Throwable cause) {

    super(cause);
  }

  public BookNotFoundException(String message, final Throwable cause) {

    super(message, cause);
  }
}
