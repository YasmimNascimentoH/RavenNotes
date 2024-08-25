package com.ifpb.RavenNotes.RavenNotes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ifpb.RavenNotes.RavenNotes.model.Note;
import com.ifpb.RavenNotes.RavenNotes.service.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(){
        return ResponseEntity.status(HttpStatus.OK).body(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        Note note = noteService.getNoteById(id);
        if (note != null) {
            return ResponseEntity.ok(note);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable String id, @RequestBody Note note) {
        Note noteBD = noteService.getNoteById(id);
        if (note != null) {
            noteBD.setTitle(note.getTitle());
            noteBD.setContent(note.getContent());
            noteService.updateNote(noteBD);
            return ResponseEntity.ok(note);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable String id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
}