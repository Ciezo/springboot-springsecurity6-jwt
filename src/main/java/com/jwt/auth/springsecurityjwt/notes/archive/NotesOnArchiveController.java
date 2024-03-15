package com.jwt.auth.springsecurityjwt.notes.archive;

import com.jwt.auth.springsecurityjwt.notes.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inkdown/v1/notes-archive")
public class NotesOnArchiveController {

    @Autowired
    private NotesService service;

    @GetMapping
    public ResponseEntity<List<NotesOnArchive>> getNotesOnArchived() {
        return new ResponseEntity<>(
                service.findAllArchivedNotes(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Optional<NotesOnArchive>> getSingleArchiveNote(
            @PathVariable("noteId") long noteId) {
        return new ResponseEntity<>(
                service.findArchivedNoteById(noteId),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<NotesOnArchive> createArchiveNote(
            @RequestBody NotesOnArchive notes) {
        return new ResponseEntity<>(
                service.addArchiveNote(notes),
                HttpStatus.OK
        );
    }

    @PutMapping("/update-archive-note/id/{noteId}")
    public ResponseEntity<NotesOnArchive> updateArchiveNote(
            @RequestBody NotesOnArchive notes,
            @PathVariable("noteId") long id) {
        return new ResponseEntity<>(
                service.updateArchiveNote(notes, id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-archive-note/id/{noteId}")
    public void deleteArchiveNote(
            @PathVariable("noteId") long id) {
        service.deleteArchiveNote(id);
    }
}
