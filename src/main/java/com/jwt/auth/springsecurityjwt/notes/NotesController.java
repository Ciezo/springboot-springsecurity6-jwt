/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     The endpoints for Notes related resources are defined here for each respective actions
 *     such as get, post, put, and delete.
 * </p>
 */
package com.jwt.auth.springsecurityjwt.notes;

import com.jwt.auth.springsecurityjwt.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
     * http://localhost:18080/api/inkdown/v1/notes/user/1 */
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Notes>> findAllNotesByUserId
            (@PathVariable long id) {
        List<Notes> userNotes = service.findAllNotesByUserId(id);
        if (userNotes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userNotes, HttpStatus.OK);
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
            @RequestBody NoteRequest noteRequest) {
        return new ResponseEntity<>(
                service.addNote(
                        noteRequest.getNote(),
                        noteRequest.getUser()
                ),
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
