package com.books.library_management_system.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.books.library_management_system.entity.Department;
import com.books.library_management_system.exception.DepartmentException;
import com.books.library_management_system.repo.DepartmentRepo;
import com.books.library_management_system.service.DepartmentService;
import com.books.library_management_system.util.LibraryUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired
  private DepartmentRepo departmentRepo;

  @Override
  public Department addDepartment(Department department) throws DepartmentException {

    if (Objects.nonNull(department)) {
      log.info(LibraryUtil.logFormat("Adding the department with name: {} to database.",
          new String[] {department.getName()}));
      try {
        department = this.departmentRepo.save(department);
      } catch (DuplicateKeyException e) {
        throw new DepartmentException(
            "Department with name: " + department.getName() + " already exists.");
      }
      return department;
    } else {
      throw new DepartmentException("Department object must not be null.");
    }
  }

  @Override
  public Department getDepartmentById(String id) throws DepartmentException {

    if (Objects.nonNull(id)) {
      log.info(LibraryUtil.logformat("Fetching the department with Id: {} from database."), id);
      Optional<Department> departmentOptional = this.departmentRepo.findById(id);
      if (departmentOptional.isPresent()) {
        return departmentOptional.get();
      } else {
        throw new DepartmentException("Unable to find department with Id: " + id + ".");
      }
    } else {
      throw new DepartmentException("Id must not be null for fetching department.");
    }
  }

  @Override
  public List<Department> findDepartmentByName(String name) throws DepartmentException {

    if (StringUtils.isNotBlank(name)) {
      log.info(LibraryUtil.logformat("Fetching the department with Name: {} from database."), name);
      List<Department> departments = this.departmentRepo.findAllByNameIgnoreCase(name);
      if (!CollectionUtils.isEmpty(departments)) {
        return departments;
      } else {
        throw new DepartmentException(
            "Department with name: " + name + " is not present in database.");
      }
    } else {
      throw new DepartmentException("Name must not be null for fetching departments.");
    }
  }

  @Override
  public List<Department> listAllDepartments() {

    log.info(LibraryUtil.logformat("Fetching all the departments from database."));
    return this.departmentRepo.findAll();
  }

  @Override
  public Department updateDepartment(String id, Department department) throws DepartmentException {

    try {
      Department oldDept = this.getDepartmentById(id);
      log.info(LibraryUtil.logFormat("Updating the department with Id: {} to database.",
          new String[] {id}));
      oldDept.setName(department.getName());
      oldDept.setDescription(department.getDescription());
      return this.departmentRepo.save(oldDept);
    } catch (DepartmentException e) {
      throw new DepartmentException(
          "Unable to update department with Id: " + id + " due to reason: " + e.getMessage());
    }
  }

  @Override
  public String deleteDepartmentById(String id) throws DepartmentException {

    if (Objects.nonNull(id)) {
      if (this.departmentRepo.existsById(id)) {
        this.departmentRepo.deleteById(id);
        return "Department with Id: " + id + " deleted successfully.";
      } else {
        throw new DepartmentException("Unable to find department with Id: " + id + ".");
      }
    } else {
      throw new DepartmentException("Id must not be null for fetching departments.");
    }
  }

  @Override
  public String removeDepartment(String name) throws DepartmentException {

    if (StringUtils.isNotBlank(name)) {
      if (this.departmentRepo.existsByName(name)) {
        this.departmentRepo.deleteByName(name);
        return "Department with name: " + name + " deleted successfully.";
      } else {
        throw new DepartmentException("Unable to find department with name: " + name + ".");
      }
    } else {
      throw new DepartmentException("Name must not be null for fetching department.");
    }
  }
}
