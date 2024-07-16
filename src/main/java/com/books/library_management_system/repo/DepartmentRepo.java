package com.books.library_management_system.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.books.library_management_system.entity.Department;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
@Repository
public interface DepartmentRepo extends MongoRepository<Department, String> {

  /**
   * @param name
   * @return
   */
  boolean existsByName(@Param("name") String name);

  /**
   * @param name
   */
  void deleteByName(@Param("name") String name);

  /**
   * @param name
   * @return
   */
  List<Department> findAllByNameIgnoreCase(@Param("name") String name);
}
