package com.books.library_management_system.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.books.library_management_system.bo.GenericResponse;
import com.books.library_management_system.exception.DepartmentException;
import com.books.library_management_system.service.LibraryService;
import com.books.library_management_system.util.LibraryUtil;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
@RestController
@RequestMapping("/")
public class LibraryController {

  @Autowired
  private LibraryService libraryService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> getMenu() throws DepartmentException {

    return LibraryUtil.buildSuccessResponse(this.libraryService.getMenu(), HttpStatus.OK);
  }
}
