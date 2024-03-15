package com.jwt.auth.springsecurityjwt.notes.trash;

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
public class NotesOnTrash {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String title;
    private String author;
    @Column(columnDefinition = "LONGTEXT")
    private String body;
}
