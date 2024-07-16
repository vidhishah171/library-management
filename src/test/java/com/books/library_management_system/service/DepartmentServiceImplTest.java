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

import com.books.library_management_system.entity.Department;
import com.books.library_management_system.exception.DepartmentException;
import com.books.library_management_system.repo.DepartmentRepo;
import com.books.library_management_system.service.impl.DepartmentServiceImpl;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
public class DepartmentServiceImplTest {

  @Mock
  private DepartmentRepo departmentRepo;

  @InjectMocks
  private DepartmentServiceImpl departmentService;

  private Department department;

  @Before
  public void setUp() {

    MockitoAnnotations.openMocks(this);
    department = new Department();
    department.setId("1");
    department.setName("General");
    department.setDescription("General Department");
  }

  @Test
  public void addDepartment_ShouldAddDepartment() throws DepartmentException {

    when(departmentRepo.save(any(Department.class))).thenReturn(department);
    Department addedDept = departmentService.addDepartment(department);
    assertEquals("General", addedDept.getName());
  }

  @Test
  public void addDepartment_ShouldThrowExceptionWhenDepartmentIsNull() {

    DepartmentException exception =
        assertThrows(DepartmentException.class, () -> departmentService.addDepartment(null));
    assertEquals("Department object must not be null.", exception.getMessage());
  }

  @Test
  public void getDepartmentById_ShouldReturnDepartment() throws DepartmentException {

    when(departmentRepo.findById(anyString())).thenReturn(Optional.of(department));
    Department foundDepartment = departmentService.getDepartmentById("1");
    assertEquals("General", foundDepartment.getName());
  }

  @Test
  public void getDepartmentById_ShouldThrowExceptionWhenIdIsNull() {

    DepartmentException exception =
        assertThrows(DepartmentException.class, () -> departmentService.getDepartmentById(null));
    assertEquals("Id must not be null for fetching department.", exception.getMessage());
  }

  @Test
  public void getDepartmentById_ShouldThrowExceptionWhenDepartmentNotFound() {

    when(departmentRepo.findById(anyString())).thenReturn(Optional.empty());
    DepartmentException exception =
        assertThrows(DepartmentException.class, () -> departmentService.getDepartmentById("1"));
    assertEquals("Unable to find department with Id: 1.", exception.getMessage());
  }

  @Test
  public void findDepartmentByName_ShouldReturnDepartments() throws DepartmentException {

    when(departmentRepo.findAllByNameIgnoreCase(anyString()))
        .thenReturn(Collections.singletonList(department));
    List<Department> departments = departmentService.findDepartmentByName("general");
    assertFalse(departments.isEmpty());
    assertEquals("General", departments.get(0)
        .getName());
  }

  @Test
  public void findDepartmentByName_ShouldThrowExceptionWhenNameIsNull() {

    DepartmentException exception =
        assertThrows(DepartmentException.class, () -> departmentService.findDepartmentByName(null));
    assertEquals("Name must not be null for fetching departments.", exception.getMessage());
  }

  @Test
  public void listAllDepartments_ShouldReturnDepartments() {

    when(departmentRepo.findAll()).thenReturn(Collections.singletonList(department));
    List<Department> departments = departmentService.listAllDepartments();
    assertFalse(departments.isEmpty());
  }

  @Test
  public void updateDepartment_ShouldUpdateDepartment() throws DepartmentException {

    when(departmentRepo.findById(anyString())).thenReturn(Optional.of(department));
    when(departmentRepo.save(any(Department.class))).thenReturn(department);
    Department updatedDepartment = departmentService.updateDepartment("1", department);
    assertEquals("General", updatedDepartment.getName());
  }

  @Test
  public void updateDepartment_ShouldThrowExceptionWhenDepartmentNotFound() {

    when(departmentRepo.findById(anyString())).thenReturn(Optional.empty());
    DepartmentException exception = assertThrows(DepartmentException.class,
        () -> departmentService.updateDepartment("1", department));
    assertEquals(
        "Unable to update department with Id: 1 due to reason: Unable to find department with Id: 1.",
        exception.getMessage());
  }

  @Test
  public void deleteDepartmentById_ShouldDeleteDepartment() throws DepartmentException {

    when(departmentRepo.existsById(anyString())).thenReturn(true);
    doNothing().when(departmentRepo)
        .deleteById(anyString());
    String result = departmentService.deleteDepartmentById("1");
    assertEquals("Department with Id: 1 deleted successfully.", result);
  }

  @Test
  public void deleteDepartmentById_ShouldThrowExceptionWhenIdIsNull() {

    DepartmentException exception =
        assertThrows(DepartmentException.class, () -> departmentService.deleteDepartmentById(null));
    assertEquals("Id must not be null for fetching departments.", exception.getMessage());
  }

  @Test
  public void deleteDepartmentById_ShouldThrowExceptionWhenDepartmentNotFound() {

    when(departmentRepo.existsById(anyString())).thenReturn(false);
    DepartmentException exception =
        assertThrows(DepartmentException.class, () -> departmentService.deleteDepartmentById("1"));
    assertEquals("Unable to find department with Id: 1.", exception.getMessage());
  }

  @Test
  public void removeDepartment_ShouldDeleteDepartmentByName() throws DepartmentException {

    when(departmentRepo.existsByName(anyString())).thenReturn(true);
    doNothing().when(departmentRepo)
        .deleteByName(anyString());
    String result = departmentService.removeDepartment("General");
    assertEquals("Department with name: General deleted successfully.", result);
  }

  @Test
  public void removeDepartment_ShouldThrowExceptionWhenNameIsNull() {

    DepartmentException exception =
        assertThrows(DepartmentException.class, () -> departmentService.removeDepartment(null));
    assertEquals("Name must not be null for fetching department.", exception.getMessage());
  }

  @Test
  public void removeDepartment_ShouldThrowExceptionWhenDepartmentNotFound() {

    when(departmentRepo.existsByName(anyString())).thenReturn(false);
    DepartmentException exception = assertThrows(DepartmentException.class,
        () -> departmentService.removeDepartment("General"));
    assertEquals("Unable to find department with name: General.", exception.getMessage());
  }
}
