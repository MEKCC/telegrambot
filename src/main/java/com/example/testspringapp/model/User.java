package com.example.testspringapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
public class User {

    @Id
    private Long id;
    private Integer percent;
    @Column(columnDefinition = "LONGTEXT")
    private String cryptocurrencyRecords;
    @CreationTimestamp
    private Date registeredTime;
}
