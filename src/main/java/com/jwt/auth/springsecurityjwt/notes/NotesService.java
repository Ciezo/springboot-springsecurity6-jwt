/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     This is where most of the logic is defined to handle all types of Notes related resources.
 * </p>
 * <p>
 *     There are sub-types of Notes which are NotesOnArchive - where notes are moved to archive,
 *     and NoteOnTrash - where the user has an option to permanently delete a note with no option to restore.
 * </p>
 */
package com.jwt.auth.springsecurityjwt.notes;


import com.jwt.auth.springsecurityjwt.notes.archive.NotesOnArchive;
import com.jwt.auth.springsecurityjwt.notes.archive.NotesOnArchiveInterface;
import com.jwt.auth.springsecurityjwt.notes.archive.NotesOnArchiveRepository;
import com.jwt.auth.springsecurityjwt.notes.trash.NotesOnTrash;
import com.jwt.auth.springsecurityjwt.notes.trash.NotesOnTrashInterface;
import com.jwt.auth.springsecurityjwt.notes.trash.NotesOnTrashRepository;
import com.jwt.auth.springsecurityjwt.user.User;
import com.jwt.auth.springsecurityjwt.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NotesService implements
        NotesInterface,
        NotesOnArchiveInterface,
        NotesOnTrashInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private NotesOnArchiveRepository notesArchiveRepository;

    @Autowired
    private NotesOnTrashRepository notesTrashRepository;

    public List<Notes> findAllNotes() {
        return (List<Notes>) notesRepository.findAll();
    }

    public List<Notes> findAllNotesByUserId(long id) {
        /* Find the User associated with the particular Notes */
        User user = userRepository.findById(id)
                .orElse(null);

        if(user == null){
            return Collections.emptyList();
        }

        return notesRepository.findByUser(user);
    }

    public Optional<Notes> findNoteById(long id) {
        return notesRepository.findNoteById(id);
    }

    public Optional<Notes> findNoteByTitle(String title) {
        return notesRepository.findNoteByTitle(title);
    }

    public Optional<Notes> findNoteByAuthor(String author) {
        return notesRepository.findNoteByAuthor(author);
    }

    @Override
    public Notes addNote(Notes note, User user) {
        note.setUser(user);
        return notesRepository.save(note);
    }

    @Override
    public Notes updateNote(Notes note, long id) {
        /* Notes object to update  */
        Notes n = notesRepository.findById(id).get();
        try {
            if(Objects.nonNull(n)) {
                n.setAuthor(note.getAuthor());
                n.setTitle(note.getTitle());
                n.setBody(note.getBody());
            }
        } catch(Exception err) {
            System.out.println("Cannot find the note resource by " + id);
            System.out.println("Something went wrong: " + err.getMessage());
            throw err;
        }
        return notesRepository.save(n);
    }

    @Override
    public void deleteNote(long id) {
        notesRepository.deleteById(id);
    }

    public List<NotesOnArchive> findAllArchivedNotes() {
        return (List<NotesOnArchive>) notesArchiveRepository.findAll();
    }

    public Optional<NotesOnArchive> findArchivedNoteById(long id) {
        return notesArchiveRepository.findArchivedNoteById(id);
    }

    @Override
    public NotesOnArchive addArchiveNote(NotesOnArchive note) {
        return notesArchiveRepository.save(note);
    }

    @Override
    public NotesOnArchive updateArchiveNote(NotesOnArchive note, long id) {
        /* NotesOnArchive object to update */
        NotesOnArchive n = notesArchiveRepository.findById(id).get();
        try {
            if(Objects.nonNull(n)) {
                n.setAuthor(note.getAuthor());
                n.setTitle(note.getTitle());
                n.setBody(note.getBody());
            }
        } catch(Exception err) {
            System.out.println("Cannot find the archive note resource by " + id);
            System.out.println("Something went wrong: " + err.getMessage());
            throw err;
        }

        return notesArchiveRepository.save(n);
    }

    @Override
    public void deleteArchiveNote(long id) {
        notesArchiveRepository.deleteById(id);
    }

    public List<NotesOnTrash> getAllTrashNotes() {
        return (List<NotesOnTrash>) notesTrashRepository.findAll();
    }

    public Optional<NotesOnTrash> findTrashNoteById(long id) {
        return notesTrashRepository.findTrashNoteById(id);
    }

    @Override
    public NotesOnTrash addTrashNote(NotesOnTrash note) {
        return notesTrashRepository.save(note);
    }

    @Override
    public NotesOnTrash updateTrashNote(NotesOnTrash note, long id) {
        /* NotesOnTrash to update */
        NotesOnTrash n = notesTrashRepository.findById(id).get();
        try {
            if(Objects.nonNull(n)) {
                n.setAuthor(note.getAuthor());
                n.setTitle(note.getTitle());
                n.setBody(note.getBody());
            }
        } catch(Exception err) {
            System.out.println("Cannot find the trash note resource by " + id);
            System.out.println("Something went wrong: " + err.getMessage());
            throw err;
        }

        return notesTrashRepository.save(n);
    }

    @Override
    public void deleteTrashNote(long id) {
        notesTrashRepository.deleteById(id);
    }
}
