package com.ifpb.RavenNotes.RavenNotes.config;

import net.ravendb.client.documents.DocumentStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RavenDBConfig {

    @Bean
    public DocumentStore documentStore() {
        System.out.println("DocumentStore bean being created...");
        DocumentStore store = new DocumentStore("http://localhost:8080", "notesdb");
        store.initialize();
        return store;
    }
}
