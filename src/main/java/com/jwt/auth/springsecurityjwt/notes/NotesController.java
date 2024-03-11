package com.jwt.auth.springsecurityjwt.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inkdown/v1/notes")
public class NotesController {

    @Autowired
    private NotesService service;

    @GetMapping
    public ResponseEntity<List<Notes>> getNotes() {
        return new ResponseEntity<>(
                service.findAllNotes(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Optional<Notes>> getSingleNoteByID(
            @PathVariable("noteId") long noteId) {
        return new ResponseEntity<>(
                service.findNoteById(noteId),
                HttpStatus.OK
        );
    }

    @GetMapping("/{noteTitle}")
    public ResponseEntity<Optional<Notes>> getSingleNoteByTitle
            (@PathVariable("noteTitle") String noteTitle) {
        return new ResponseEntity<>(
                service.findNoteByTitle(noteTitle),
                HttpStatus.OK
        );
    }

    @GetMapping("/{noteAuthor}")
    public ResponseEntity<Optional<Notes>> getSingleNoteByAuthor(
            @PathVariable("noteAuthor") String noteAuthor) {
        return new ResponseEntity<>(
                service.findNoteByAuthor(noteAuthor),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<Notes> createNote(
            @RequestBody Notes notes) {
        return new ResponseEntity<>(
                service.addNote(notes),
                HttpStatus.OK
        );
    }

    @PutMapping("/update-note/id/{noteId}")
    public ResponseEntity<Notes> updateNote(
            @RequestBody Notes note,
            @PathVariable("noteId") long id) {
        return new ResponseEntity<>(
                service.updateNote(note, id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-note/id/{noteId}")
    public void deleteNote(
            @PathVariable("noteId") long id) {
        service.deleteNote(id);
    }
}
