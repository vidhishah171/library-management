package com.books.library_management_system.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
@Builder
public class Book {

  private String id;

  private String title;

  private String author;

  @NotNull(message = "ISBN number must not be blank.")
  @Indexed(unique = true)
  private String isbn;

  private String genre;

  private int publicationYear;

  private String department;

  private boolean availability;
}
