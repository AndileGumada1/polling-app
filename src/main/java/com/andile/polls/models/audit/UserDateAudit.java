package com.andile.polls.models.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * This class is used to achieve user date auditing
 * */
@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy"},
        allowGetters = true
)
@Data
public class UserDateAudit extends DateAudit{
    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;
    @LastModifiedBy
    private Long updatedBy;
}
