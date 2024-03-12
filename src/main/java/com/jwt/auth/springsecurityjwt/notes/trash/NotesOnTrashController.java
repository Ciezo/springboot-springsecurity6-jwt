package com.jwt.auth.springsecurityjwt.notes.trash;

import com.jwt.auth.springsecurityjwt.notes.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inkdown/v1/notes-trash")
public class NotesOnTrashController {

    @Autowired
    private NotesService service;

    @GetMapping
    public ResponseEntity<List<NotesOnTrash>> getNotesOnTrash() {
        return new ResponseEntity<>(
                service.getAllTrashNotes(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Optional<NotesOnTrash>> getSingleTrashNote(
            @PathVariable("noteId") long noteId) {
        return new ResponseEntity<>(
                service.findTrashNoteById(noteId),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<NotesOnTrash> createTrashNote(
            @RequestBody NotesOnTrash notes) {
        return new ResponseEntity<>(
                service.addTrashNote(notes),
                HttpStatus.OK
        );
    }

    @PutMapping("/update-trash-note/id/{noteId}")
    public ResponseEntity<NotesOnTrash> updateDeleteNote(
            @RequestBody NotesOnTrash notes,
            @PathVariable("noteId") long id) {
        return new ResponseEntity<>(
                service.updateTrashNote(notes, id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-trash-note/id/{noteId}")
    public void deleteTrashNote(
            @PathVariable("noteId") long id) {
        service.deleteTrashNote(id);
    }

}
