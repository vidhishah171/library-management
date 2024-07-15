package com.books.library_management_system.service;

import java.util.List;

import com.books.library_management_system.entity.Department;
import com.books.library_management_system.exception.DepartmentException;

public interface DepartmentService {

  /**
   * @author Vidhi_s Method to add new department.
   * 
   * @param book
   * @return
   * @throws DepartmentException
   */
  Department addDepartment(Department department) throws DepartmentException;
  // /**
  // * @author Vidhi_s Method to add new book by parameters.
  // *
  // * @param author
  // * @param title
  // * @param publicationYear
  // * @return
  // * @throws DepartmentException
  // */
  // Department addBook(String author, String title, int publicationYear) throws
  // DepartmentException;

  /**
   * @author Vidhi_s Method to get department by id.
   * 
   * @param id
   * @return
   * @throws DepartmentException
   */
  Department getDepartmentById(String id) throws DepartmentException;

  /**
   * @author Vidhi_s Method to update department by id.
   * 
   * @param id
   * @param book
   * @return
   * @throws DepartmentException
   */
  Department updateDepartment(String id, Department department) throws DepartmentException;

  /**
   * @author Vidhi_s Method to delete department by id.
   * 
   * @param id
   * @return
   * @throws DepartmentException
   */
  String deleteDepartmentById(String id) throws DepartmentException;

  /**
   * @author Vidhi_s Method to fetch all the departments in library.
   * 
   * @return
   */
  List<Department> listAllDepartments();

  /**
   * @param name
   * @return
   * @throws DepartmentException
   */
  String removeDepartment(String name) throws DepartmentException;

  List<Department> findDepartmentByName(String name) throws DepartmentException;
}
