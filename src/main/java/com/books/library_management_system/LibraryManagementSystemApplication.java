package com.books.library_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
@SpringBootApplication
@EnableMongoAuditing
public class LibraryManagementSystemApplication {

  public static void main(String[] args) {

    SpringApplication.run(LibraryManagementSystemApplication.class, args);
  }
}
