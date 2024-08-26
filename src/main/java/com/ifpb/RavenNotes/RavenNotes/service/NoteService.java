package com.ifpb.RavenNotes.RavenNotes.service;

import com.ifpb.RavenNotes.RavenNotes.model.Note;
import com.ifpb.RavenNotes.RavenNotes.repository.NoteRepository;
import com.ifpb.RavenNotes.RavenNotes.utilities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private Message message;

    public ResponseEntity<?> addNote(Note note) {
        if (isEmptyNote(note)) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            noteRepository.save(note);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
    }

    public Note getNoteById(String id) {
        return noteRepository.findById(id);
    }

    public void updateNote(Note note) {
        noteRepository.save(note);
    }

    public void deleteNoteById(String id) {
        noteRepository.deleteById(id);
    }

    private boolean isEmptyNote(Note note) {
        if (note.getTitle().isEmpty()) {
            message.setMessage("Nota não foi salva. Titulo está vazio.");
            return true;
        }
        if (note.getContent().isEmpty()) {
            message.setMessage("Nota não foi salva. Conteúdo está vazio.");
            return true;
        }
        message.setMessage("Nota salva com sucesso! Id:" + note.getId());
        return false;
    }
}
