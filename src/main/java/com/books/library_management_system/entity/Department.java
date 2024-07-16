package com.books.library_management_system.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.books.library_management_system.bo.Auditable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "departments")
@Builder
public class Department extends Auditable {

  @NotBlank(message = "Department name must not be blank.")
  @Indexed(unique = true)
  private String name;

  private String description;
}
