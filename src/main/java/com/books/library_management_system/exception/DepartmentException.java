package com.books.library_management_system.exception;

public class DepartmentException extends Exception {

  private static final long serialVersionUID = 1L;

  public DepartmentException(String message) {

    super(message);
  }

  public DepartmentException(final Throwable cause) {

    super(cause);
  }

  public DepartmentException(String message, final Throwable cause) {

    super(message, cause);
  }
}
