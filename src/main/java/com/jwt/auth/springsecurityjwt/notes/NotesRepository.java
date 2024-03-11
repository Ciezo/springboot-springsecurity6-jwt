package com.jwt.auth.springsecurityjwt.notes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotesRepository extends CrudRepository<Notes, Long> {
    Optional<Notes> findNoteById(long id);
    Optional<Notes> findNoteByTitle(String title);
    Optional<Notes> findNoteByAuthor(String author);
}
