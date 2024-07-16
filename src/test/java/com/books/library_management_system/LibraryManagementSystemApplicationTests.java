package com.books.library_management_system;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

// import com.books.library_management_system.rest.BooksControllerTest;
import com.books.library_management_system.service.BooksServiceImplTest;
import com.books.library_management_system.service.DepartmentServiceImplTest;

@SpringBootTest
@RunWith(Suite.class)
@SuiteClasses({BooksServiceImplTest.class, DepartmentServiceImplTest.class})
class LibraryManagementSystemApplicationTests {

  @Test
  void contextLoads() {}
}
