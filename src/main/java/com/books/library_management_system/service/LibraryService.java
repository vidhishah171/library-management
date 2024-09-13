package com.books.library_management_system.service;

import java.util.Map;

import com.books.library_management_system.bo.Email;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
public interface LibraryService {

  /**
   * @author Vidhi_s Method to get library menu.
   * 
   * @return
   */
  Map<String, String> getMenu();

  Email sendEmail(Email email);
}
