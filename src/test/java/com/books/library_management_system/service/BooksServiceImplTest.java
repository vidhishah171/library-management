package com.books.library_management_system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.books.library_management_system.entity.Book;
import com.books.library_management_system.exception.BookNotFoundException;
import com.books.library_management_system.exception.BooksException;
import com.books.library_management_system.repo.BooksRepo;
import com.books.library_management_system.repo.DepartmentRepo;
import com.books.library_management_system.service.impl.BooksServiceImpl;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
public class BooksServiceImplTest {

  @Mock
  private BooksRepo booksRepo;

  @Mock
  private DepartmentRepo departmentRepo;

  @InjectMocks
  private BooksServiceImpl booksService;

  private Book book;

  @Before
  public void setUp() {

    MockitoAnnotations.openMocks(this);
    book = new Book();
    book.setId("1");
    book.setTitle("Test Book");
    book.setAuthor("Test Author");
    book.setIsbn("1234567890");
    book.setDepartment("Science");
    book.setPublicationYear(2020);
    book.setAvailability(true);
  }

  @Test
  public void addBook_ShouldAddBook() throws BooksException {

    when(departmentRepo.existsByName("Science")).thenReturn(true);
    when(booksRepo.save(any(Book.class))).thenReturn(book);
    Book addedBook = booksService.addBook(book);
    assertEquals("Test Book", addedBook.getTitle());
  }

  @Test
  public void addBook_ShouldThrowExceptionWhenBookIsNull() {

    BooksException exception = assertThrows(BooksException.class, () -> booksService.addBook(null));
    assertEquals("Book object must not be null.", exception.getMessage());
  }

  @Test
  public void getBookById_ShouldReturnBook() throws BooksException {

    when(booksRepo.findById(anyString())).thenReturn(Optional.of(book));
    Book foundBook = booksService.getBookById("1");
    assertEquals("Test Book", foundBook.getTitle());
  }

  @Test
  public void getBookById_ShouldThrowExceptionWhenIdIsNull() {

    BooksException exception =
        assertThrows(BooksException.class, () -> booksService.getBookById(null));
    assertEquals("Id must not be null for fetching books.", exception.getMessage());
  }

  @Test
  public void getBookById_ShouldThrowExceptionWhenBookNotFound() {

    when(booksRepo.findById(anyString())).thenReturn(Optional.empty());
    BookNotFoundException exception =
        assertThrows(BookNotFoundException.class, () -> booksService.getBookById("1"));
    assertEquals("Unable to find book with Id: 1.", exception.getMessage());
  }

  @Test
  public void findBookByTitle_ShouldReturnBooks() throws BooksException {

    when(booksRepo.findAllByTitleIgnoreCase(anyString()))
        .thenReturn(Collections.singletonList(book));
    List<Book> books = booksService.findBookByTitle("Test Book");
    assertFalse(books.isEmpty());
    assertEquals("Test Book", books.get(0)
        .getTitle());
  }

  @Test
  public void findBookByTitle_ShouldThrowExceptionWhenTitleIsNull() {

    BooksException exception =
        assertThrows(BooksException.class, () -> booksService.findBookByTitle(null));
    assertEquals("Title must not be null for fetching books.", exception.getMessage());
  }

  @Test
  public void findBookByAuthor_ShouldReturnBooks() throws BooksException {

    when(booksRepo.findAllByAuthorIgnoreCase(anyString()))
        .thenReturn(Collections.singletonList(book));
    List<Book> books = booksService.findBookByAuthor("Test Author");
    assertFalse(books.isEmpty());
    assertEquals("Test Author", books.get(0)
        .getAuthor());
  }

  @Test
  public void findBookByAuthor_ShouldThrowExceptionWhenAuthorIsNull() {

    BooksException exception =
        assertThrows(BooksException.class, () -> booksService.findBookByAuthor(null));
    assertEquals("Author must not be null for fetching books.", exception.getMessage());
  }

  @Test
  public void listAvailableBooks_ShouldReturnBooks() throws BooksException {

    when(booksRepo.findAllByAvailabilityTrue()).thenReturn(Collections.singletonList(book));
    List<Book> books = booksService.listAvailableBooks();
    assertFalse(books.isEmpty());
  }

  @Test
  public void listAvailableBooks_ShouldThrowExceptionWhenNoBooksFound() {

    when(booksRepo.findAllByAvailabilityTrue()).thenReturn(Collections.emptyList());
    BookNotFoundException exception =
        assertThrows(BookNotFoundException.class, () -> booksService.listAvailableBooks());
    assertEquals("There are no available books present in database.", exception.getMessage());
  }

  @Test
  public void listAllBooks_ShouldReturnBooks() {

    when(booksRepo.findAll()).thenReturn(Collections.singletonList(book));
    List<Book> books = booksService.listAllBooks();
    assertFalse(books.isEmpty());
  }

  @Test
  public void updateBook_ShouldUpdateBook() throws BooksException {

    when(booksRepo.findById(anyString())).thenReturn(Optional.of(book));
    when(booksRepo.save(any(Book.class))).thenReturn(book);
    Book updatedBook = booksService.updateBook("1", book);
    assertEquals("Test Book", updatedBook.getTitle());
  }

  @Test
  public void updateBook_ShouldThrowExceptionWhenBookNotFound() {

    when(booksRepo.findById(anyString())).thenReturn(Optional.empty());
    BooksException exception =
        assertThrows(BooksException.class, () -> booksService.updateBook("1", book));
    assertEquals("Unable to update book with Id: 1 due to reason: Unable to find book with Id: 1.",
        exception.getMessage());
  }

  @Test
  public void deleteBookById_ShouldDeleteBook() throws BooksException {

    when(booksRepo.existsById(anyString())).thenReturn(true);
    doNothing().when(booksRepo)
        .deleteById(anyString());
    String result = booksService.deleteBookById("1");
    assertEquals("Book with Id: 1 deleted successfully.", result);
  }

  @Test
  public void deleteBookById_ShouldThrowExceptionWhenIdIsNull() {

    BooksException exception =
        assertThrows(BooksException.class, () -> booksService.deleteBookById(null));
    assertEquals("Id must not be null for fetching books.", exception.getMessage());
  }

  @Test
  public void deleteBookById_ShouldThrowExceptionWhenBookNotFound() {

    when(booksRepo.existsById(anyString())).thenReturn(false);
    BookNotFoundException exception =
        assertThrows(BookNotFoundException.class, () -> booksService.deleteBookById("1"));
    assertEquals("Unable to find book with Id: 1.", exception.getMessage());
  }

  @Test
  public void removeBook_ShouldDeleteBookByIsbn() throws BooksException {

    when(booksRepo.existsByIsbn(anyString())).thenReturn(true);
    doNothing().when(booksRepo)
        .deleteByIsbn(anyString());
    String result = booksService.removeBook("1234567890");
    assertEquals("Book with ISBN: 1234567890 deleted successfully.", result);
  }

  @Test
  public void removeBook_ShouldThrowExceptionWhenIsbnIsNull() {

    BooksException exception =
        assertThrows(BooksException.class, () -> booksService.removeBook(null));
    assertEquals("ISBN must not be null for fetching books.", exception.getMessage());
  }

  @Test
  public void removeBook_ShouldThrowExceptionWhenBookNotFound() {

    when(booksRepo.existsByIsbn(anyString())).thenReturn(false);
    BookNotFoundException exception =
        assertThrows(BookNotFoundException.class, () -> booksService.removeBook("1234567890"));
    assertEquals("Unable to find book with ISBN: 1234567890.", exception.getMessage());
  }
}
