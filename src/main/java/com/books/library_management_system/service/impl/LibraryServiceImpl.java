package com.books.library_management_system.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.books.library_management_system.bo.Email;
import com.books.library_management_system.service.LibraryService;
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

  public Email sendEmail(Email email) {

    System.out.println("To: " + email.getTo());
    System.out.println("Subject: " + email.getSubject());
    System.out.println("Message: " + email.getMessage());
    synchronized (email) {
      try {
        email.wait(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return email;
  }
}
