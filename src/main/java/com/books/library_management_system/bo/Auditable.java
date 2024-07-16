package com.books.library_management_system.bo;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @EntityListeners(value = {AuditingEventListener.class, MongoAuditingBeanDefinitionParser.class})
public abstract class Auditable {

  /**
   * @param createdDate
   * @param createdBy
   */
  public Auditable(final String id, final Date createdDate, final String createdBy) {

    super();
    this.id = id;
    this.createdDate = createdDate;
  }

  @Id
  private String id;

  @CreatedDate
  private Date createdDate;

  @LastModifiedDate
  private Date updatedDate;
}
