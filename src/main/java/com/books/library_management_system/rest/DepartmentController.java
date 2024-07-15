package com.books.library_management_system.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.books.library_management_system.bo.GenericResponse;
import com.books.library_management_system.entity.Department;
import com.books.library_management_system.exception.DepartmentException;
import com.books.library_management_system.service.DepartmentService;
import com.books.library_management_system.util.LibraryUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  @Autowired
  private DepartmentService departmentService;

  @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<GenericResponse> addDepartment(@RequestBody @Valid Department bookObject)
      throws DepartmentException {

    return LibraryUtil.buildSuccessResponse(this.departmentService.addDepartment(bookObject),
        HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> fetchDepartmentById(@PathVariable String id)
      throws DepartmentException {

    return LibraryUtil.buildSuccessResponse(this.departmentService.getDepartmentById(id),
        HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> listAllDepartments() throws DepartmentException {

    return LibraryUtil.buildSuccessResponse(this.departmentService.listAllDepartments(),
        HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> updateDepartment(@PathVariable String id,
      @RequestBody @Valid Department bookObject) throws DepartmentException {

    return LibraryUtil.buildSuccessResponse(this.departmentService.updateDepartment(id, bookObject),
        HttpStatus.ACCEPTED);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> deleteDepartmentById(@PathVariable String id)
      throws DepartmentException {

    return LibraryUtil.buildSuccessResponse(this.departmentService.deleteDepartmentById(id),
        HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "remove/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> removeDepartment(@PathVariable String name)
      throws DepartmentException {

    return LibraryUtil.buildSuccessResponse(this.departmentService.removeDepartment(name),
        HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/find/byName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponse> findDepartmentByName(@PathVariable String name)
      throws DepartmentException {

    return LibraryUtil.buildSuccessResponse(this.departmentService.findDepartmentByName(name),
        HttpStatus.OK);
  }
}
