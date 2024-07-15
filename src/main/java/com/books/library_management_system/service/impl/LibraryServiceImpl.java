package com.books.library_management_system.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.books.library_management_system.service.LibraryService;
import com.books.library_management_system.util.LibraryUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LibraryServiceImpl implements LibraryService {

  @Override
  public Map<String, String> getMenu() {

    log.info(LibraryUtil.logformat("Fetching the library menu."));
    Map<String, String> menu = new LinkedHashMap<String, String>();
    menu.put("add", "APIs to add the departments and books in the library.");
    menu.put("remove",
        "Remove Departments: By Id or By Name. Remove Books: By Id or By ISBN number.");
    menu.put("search",
        "Search Departments: By Id, By Name. Search Books: By Id, By Title, By Author, By availability.");
    menu.put("list", "List Departments and Books stored in the library.");
    menu.put("exit", "Exit from the library.");
    return menu;
  }
}
