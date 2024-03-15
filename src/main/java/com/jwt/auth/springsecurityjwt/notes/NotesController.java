/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     The endpoints for Notes related resources are defined here for each respective actions
 *     such as get, post, put, and delete.
 * </p>
 */
package com.jwt.auth.springsecurityjwt.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @// TODO: 3/15/2024
 * - Create an endpoint to find all notes by one author based on username.
 */
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

    /** Example:
     * http://localhost:18080/api/inkdown/v1/notes/id/104 */
    @GetMapping("/id/{noteId}")
    public ResponseEntity<Optional<Notes>> getSingleNoteByID(
            @PathVariable("noteId")
            long noteId) {
        return new ResponseEntity<>(
                service.findNoteById(noteId),
                HttpStatus.OK
        );
    }

    /** Example:
     * http://localhost:18080/api/inkdown/v1/notes/author?author=cloydvan */
    @GetMapping("/author")
    public ResponseEntity<Optional<Notes>> getSingleNoteByAuthor(
            @RequestParam(value = "author")
            String author) {
        return new ResponseEntity<>(
                service.findNoteByAuthor(author),
                HttpStatus.OK
        );
    }

    /** Example:
     * http://localhost:18080/api/inkdown/v1/notes/title?=sharks */
    @GetMapping("/title")
    public ResponseEntity<Optional<Notes>> getSingleNoteByTitle (
            @RequestParam(value = "title")
            String title) {
        return new ResponseEntity<>(
                service.findNoteByTitle(title),
                HttpStatus.OK
        );
    }

    /** Example:
     * http://localhost:18080/api/inkdown/v1/notes */
    @PostMapping()
    public ResponseEntity<Notes> createNote(
            @RequestBody Notes notes) {
        return new ResponseEntity<>(
                service.addNote(notes),
                HttpStatus.OK
        );
    }

    /** Example:
     * http://localhost:18080/api/inkdown/v1/notes/update-note/id/152 */
    @PutMapping("/update-note/id/{noteId}")
    public ResponseEntity<Notes> updateNote(
            @RequestBody Notes note,
            @PathVariable("noteId") long id) {
        return new ResponseEntity<>(
                service.updateNote(note, id),
                HttpStatus.OK
        );
    }

    /**
     * Example:
     * http://localhost:18080/api/inkdown/v1/notes/delete-note/id/103 */
    @DeleteMapping("/delete-note/id/{noteId}")
    public void deleteNote(
            @PathVariable("noteId") long id) {
        service.deleteNote(id);
    }
}
