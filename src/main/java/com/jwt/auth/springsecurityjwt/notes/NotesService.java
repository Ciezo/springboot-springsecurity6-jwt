package com.jwt.auth.springsecurityjwt.notes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NotesService implements NotesInterface {

    @Autowired
    private NotesRepository repository;

    public List<Notes> findAllNotes() {
        return (List<Notes>) repository.findAll();
    }

    public Optional<Notes> findNoteById(long id) {
        return repository.findNoteById(id);
    }

    public Optional<Notes> findNoteByTitle(String title) {
        return repository.findNoteByTitle(title);
    }

    public Optional<Notes> findNoteByAuthor(String author) {
        return repository.findNoteByAuthor(author);
    }

    @Override
    public Notes addNote(Notes note) {
        return repository.save(note);
    }

    @Override
    public Notes updateNote(Notes note, long id) {
        try {
            Notes n = repository.findById(id).get();
            if(Objects.nonNull(n)) {
                Notes newNote = new Notes(
                        n.getId(),
                        n.getTitle(),
                        n.getAuthor(),
                        n.getBody());
                return repository.save(newNote);
            }
        } catch(Exception err) {
            System.out.println("Cannot find the note resource by " + id);
            System.out.println("Something went wrong: " + err.getMessage());
            throw err;
        }
        return null;
    }

    @Override
    public void deleteNote(long id) {
        repository.deleteById(id);
    }
}
