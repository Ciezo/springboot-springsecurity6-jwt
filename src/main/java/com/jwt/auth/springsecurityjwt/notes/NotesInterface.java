/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     Common `Notes` related actions to handle common CRUD operations.
 * </p>
 */
package com.jwt.auth.springsecurityjwt.notes;

public interface NotesInterface {
    Notes addNote(Notes note);
    Notes updateNote(Notes note, long id);
    void deleteNote(long id);
}
