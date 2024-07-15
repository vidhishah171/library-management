// package com.books.library_management_system.rest;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import org.junit.Test;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.junit.runner.RunWith;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.TestConfiguration;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.context.junit4.SpringRunner;
//
// import com.books.library_management_system.bo.GenericResponse;
// import com.books.library_management_system.entity.Book;
// import com.books.library_management_system.exception.BooksException;
// import com.books.library_management_system.service.BooksService;
//
// @RunWith(SpringRunner.class)
// @ExtendWith(SpringExtension.class)
// public class BooksControllerTest {
//
// @TestConfiguration
// static class BooksControllerTestContextConfiguration {
//
// @Bean
// public BooksController booksController() {
//
// return new BooksController();
// }
// }
//
// @Autowired
// private BooksController booksController;
//
// @MockBean
// private BooksService booksService;
//
// private final Book book = Book.builder()
// .author("Author")
// .title("Title")
// .publicationYear(2022)
// .build();
//
// @BeforeAll
// public static void beforeAll() {
//
// MockitoAnnotations.openMocks(BooksControllerTest.class);
// }
//
// @Test
// public void addBook_shouldAddBook_whenValidBookGiven() throws BooksException {
//
// when(booksService.addBook(any(Book.class))).thenReturn(book);
// ResponseEntity<GenericResponse> response = booksController.addBook(book);
// System.out.println(response);
// assertEquals("Operation Performed Successfully.", response.getBody()
// .getMessage());
// assertEquals(HttpStatus.CREATED, response.getStatusCode());
// verify(booksService, times(1)).addBook(any(Book.class));
// }
//
// @Test
// public void fetchBook_shouldReturnBook_whenValidIdGiven() throws BooksException {
//
// when(booksService.getBookById(anyString())).thenReturn(book);
// ResponseEntity<GenericResponse> response = booksController.fetchBookById(anyString());
// assertEquals(book, response.getBody()
// .getData());
// assertEquals(HttpStatus.OK, response.getStatusCode());
// verify(booksService, times(1)).getBookById(anyString());
// }
//
// @Test
// public void addBook_shouldThrowException_whenNullBookGiven() throws BooksException {
//
// when(booksService.addBook(null)).thenThrow(BooksException.class);
// assertThrows(BooksException.class, () -> {
// booksController.addBook(null);
// });
// }
//
// @Test
// public void fetchAllBooks_shouldReturnAllBooks() throws BooksException {
//
// List<Book> booksList = new ArrayList<>();
// booksList.add(book);
// when(booksService.listAllBooks()).thenReturn(booksList);
// ResponseEntity<GenericResponse> response = booksController.listAllBooks();
// assertEquals(booksList, response.getBody()
// .getData());
// assertEquals(HttpStatus.OK, response.getStatusCode());
// verify(booksService, times(1)).listAllBooks();
// }
//
// @Test
// public void updateBook_shouldUpdateBook_whenValidIdAndBookGiven() throws BooksException {
//
// when(booksService.updateBook(anyString(), any(Book.class))).thenReturn(book);
// ResponseEntity<GenericResponse> response = booksController.updateBook(anyString(), book);
// assertEquals(book, response.getBody()
// .getData());
// assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
// verify(booksService, times(1)).updateBook(anyLong(), any(Book.class));
// }
//
// @Test
// public void deleteBook_shouldDeleteBook_whenValidIdGiven() throws BooksException {
//
// when(booksService.deleteBook(anyLong())).thenReturn("Book deleted successfully.");
// ResponseEntity<GenericResponse> response = booksController.deleteBook(1L);
// assertEquals("Book deleted successfully.", response.getBody()
// .getMessage());
// assertEquals(HttpStatus.OK, response.getStatusCode());
// verify(booksService, times(1)).deleteBook(anyLong());
// }
// }
