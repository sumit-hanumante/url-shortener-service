package com.sumit.urlshortenerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // Generates getters, setters, equals, hash, and toString methods
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates a constructor for all fields
@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false)
    private String shortUrl;

    private String userId;

    private Date creationDate;

    private Date expirationDate;

    private String password;

    private int clicks;

    // getters and setters
}
