package com.jwt.auth.springsecurityjwt.notes.trash;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotesOnTrashRepository extends CrudRepository<NotesOnTrash, Long> {
    Optional<NotesOnTrash> findTrashNoteById(long id);
}
