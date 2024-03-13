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
 * @TODO
 * - IMPLEMENT QUERY PARAMETERS.
 *   My mapping and endpoints are very ambiguous. Hence, an error occurs:
 *
 *   ```java.lang.IllegalStateException: Ambiguous handler methods mapped for '/api/inkdown/v1/notes/104'```
 *
 *   A query parameter is used to identify different types of resources using a specific endpoint.
 *   Examples:
 *   http://localhost:18080/api/inkdown/v1/notes/104
 *
 *       - where this URL is using Id to GET a single Note.
 *
 *   http://localhost:18080/api/inkdown/v1/notes?title=Sharks
 *
 *       - where this URL is using GET to identify a single Note by title.
 *
 *  In conclusion, it is better to implement both ways, /id and /{param}, to GET or Map a resource.
 *
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

    @GetMapping("/{noteId}")
    public ResponseEntity<Optional<Notes>> getSingleNoteByID(
            @PathVariable("noteId") long noteId) {
        return new ResponseEntity<>(
                service.findNoteById(noteId),
                HttpStatus.OK
        );
    }

    /** @// TODO: 3/13/2024 fix this.
     * This is an ambiguous endpoint
     * I must use a query parameter, /notes?title=""
     * <b>Use the @RequestParam in the argument</b>*/
    @GetMapping("/{noteTitle}")
    public ResponseEntity<Optional<Notes>> getSingleNoteByTitle
            (@PathVariable("noteTitle") String noteTitle) {
        return new ResponseEntity<>(
                service.findNoteByTitle(noteTitle),
                HttpStatus.OK
        );
    }

    /** @// TODO: 3/13/2024 fix this.
     * This is an ambiguous endpoint
     * I must use a query parameter, /notes?author=""
     * <b>Use the @RequestParam in the argument</b>*/
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

    /** @// TODO: 3/13/2024 fix this.
     * The endpoint assigned is okay, however, it only returns
     * the current or existing note, but <b>not the updated note.</b> */
    @PutMapping("/update-note/id/{noteId}")
    public ResponseEntity<Notes> updateNote(
            @RequestBody Notes note,
            @PathVariable("noteId") long id) {
        return new ResponseEntity<>(
                service.updateNote(note, id),
                HttpStatus.OK
        );
    }

    /** @TODO: 3/13/2024 test this.
     * I have not tested this yet. I hope it works when the time comes
     * :crying_emoji: :crying_emoji: :crying_emoji: :crying_emoji: */
    @DeleteMapping("/delete-note/id/{noteId}")
    public void deleteNote(
            @PathVariable("noteId") long id) {
        service.deleteNote(id);
    }
}
