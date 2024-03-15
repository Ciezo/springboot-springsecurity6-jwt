package com.jwt.auth.springsecurityjwt.notes.archive;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NotesOnArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String body;
}
