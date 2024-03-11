package com.jwt.auth.springsecurityjwt.notes.trash;


public interface NotesOnTrashInterface {
    NotesOnTrash addTrashNote(NotesOnTrash note);
    NotesOnTrash updateTrashNote(NotesOnTrash note, long id);
    void deleteTrashNote(long id);
}
