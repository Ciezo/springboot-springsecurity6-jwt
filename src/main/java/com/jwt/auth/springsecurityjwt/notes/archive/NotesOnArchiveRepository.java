package com.jwt.auth.springsecurityjwt.notes.archive;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotesOnArchiveRepository extends CrudRepository<NotesOnArchive, Long> {
    Optional<NotesOnArchive> findArchivedNoteById(long id);
}
