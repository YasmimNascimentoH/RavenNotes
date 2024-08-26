package com.ifpb.RavenNotes.RavenNotes.config;

import net.ravendb.client.documents.DocumentStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RavenDBConfig {

    @Bean
    public DocumentStore documentStore() {
        DocumentStore store = new DocumentStore("http://127.0.0.1:8080", "notesdb");
        store.initialize();
        return store;
    }
}