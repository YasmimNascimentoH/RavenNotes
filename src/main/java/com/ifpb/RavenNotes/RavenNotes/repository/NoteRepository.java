package com.ifpb.RavenNotes.RavenNotes.repository;

import com.ifpb.RavenNotes.RavenNotes.model.Note;
import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepository {

    @Autowired
    private DocumentStore store;

    public void save(Note note) {
        try (IDocumentSession session = store.openSession()) {
            session.store(note);
            session.saveChanges();
        }
    }

    public Note findById(String id) {
        try (IDocumentSession session = store.openSession()) {
            return session.load(Note.class, id);
        }
    }

    public void deleteById(String id) {
        try (IDocumentSession session = store.openSession()) {
            Note note = session.load(Note.class, id);
            if (note != null) {
                session.delete(note);
                session.saveChanges();
            }
        }
    }
}
