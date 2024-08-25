package com.ifpb.RavenNotes.RavenNotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Gera getter's, setter's, toString(), equals() e hashCode()
@NoArgsConstructor // Gera construtor sem argumentos
@AllArgsConstructor // Gera construtor com todos argumentos

public class Note {

    private String id;
    private String title;
    private String content;

}