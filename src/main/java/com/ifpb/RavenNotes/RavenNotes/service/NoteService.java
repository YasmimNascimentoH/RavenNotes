package com.ifpb.RavenNotes.RavenNotes.service;

import com.ifpb.RavenNotes.RavenNotes.model.Note;
import com.ifpb.RavenNotes.RavenNotes.utilities.Counter;
import com.ifpb.RavenNotes.RavenNotes.utilities.Message;
import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private DocumentStore store;
    @Autowired
    private Message message;

    public ResponseEntity<?> addNote(Note note) {
        createNoteWithAutoId(note);

        if ( isEmptyNote(note)){
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }else{
            try (IDocumentSession session = store.openSession()) {
                session.store(note);
                session.saveChanges();
                return new ResponseEntity<>(message, HttpStatus.CREATED);
            }
        }
    }

    public List<Note> getAllNotes() {
        try (var session = store.openSession()) {
            return session.query(Note.class).toList();
        }
    }

    public Note getNoteById(String id) {
        try (IDocumentSession session = store.openSession()) {
            return session.load(Note.class, id);
        }
    }

    public void updateNote(Note note) {
        try (IDocumentSession session = store.openSession()) {
            session.store(note);
            session.saveChanges();
        }
    }

    public void deleteNoteById(String id) {
        try (IDocumentSession session = store.openSession()) {
            Note note = session.load(Note.class, id);
            if (note != null) {
                session.delete(note);
                session.saveChanges();
            }
        }
    }

    public boolean isEmptyNote(Note note) {
        if (note.getTitle().isEmpty()) {
            message.setMessage("Nota não foi salva. Titulo está vazio.");
            return true;
        }
        if (note.getContent().isEmpty()) {
            message.setMessage("Nota não foi salva. Conteudo está vazio.");
            return true;
        }
        message.setMessage("Nota salva com Sucesso! Id:" + note.getId());
        return false;
    }

    public void createNoteWithAutoId(Note note) {
        try (IDocumentSession session = store.openSession()) {
            // Carregar ou inicializar o documento do contador
            Counter counter = session.load(Counter.class, "counters/notes");
            if (counter == null) {
                counter = new Counter();
                counter.setCount(0);
                session.store(counter, "counters/notes");
            }

            // Incrementar o contador e gerar o novo ID
            int newId = counter.getCount() + 1;
            note.setId(""+newId); //atribui o ID
            counter.setCount(newId);

            // Armazenar a nota e o contador atualizado
            session.store(note);
            session.store(counter);
            session.saveChanges(); // Persistir mudanças
        }
    }
}