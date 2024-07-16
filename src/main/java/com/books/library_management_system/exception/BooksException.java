package com.books.library_management_system.exception;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
public class BooksException extends Exception {

  private static final long serialVersionUID = 1L;

  public BooksException(String message) {

    super(message);
  }

  public BooksException(final Throwable cause) {

    super(cause);
  }

  public BooksException(String message, final Throwable cause) {

    super(message, cause);
  }
}
