package com.jwt.auth.springsecurityjwt.notes.archive;

public interface NotesOnArchiveInterface {
    NotesOnArchive addArchiveNote(NotesOnArchive note);
    NotesOnArchive updateArchiveNote(NotesOnArchive note, long id);
    void deleteArchiveNote(long id);
}
