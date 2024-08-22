package com.ifpb.RavenNotes.RavenNotes.controller;

import com.ifpb.RavenNotes.RavenNotes.model.Note;
import com.ifpb.RavenNotes.RavenNotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }
}
