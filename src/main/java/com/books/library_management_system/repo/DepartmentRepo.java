package com.books.library_management_system.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.books.library_management_system.entity.Department;

@Repository
public interface DepartmentRepo extends MongoRepository<Department, String> {

  boolean existsByName(@Param("name") String name);

  void deleteByName(@Param("name") String name);

  List<Department> findAllByNameIgnoreCase(@Param("name") String name);
}
