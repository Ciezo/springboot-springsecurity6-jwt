package com.jwt.auth.springsecurityjwt.notes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String title;
    private String author;
    private String body;
}
