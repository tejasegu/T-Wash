package com.twash.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
/**
 * 
 * This class is a model class With Collection In MongoDB Will Store The Incremented Sequence For Other Collections
 *
 */
public class DatabaseSequence {

    @Id //Creates Id Field In MongoDB
    private String id;
    //Creates Sequence Name In MongoDB
    private long sequence; 
    //No Argument Constructor
    public DatabaseSequence() {}
    // Getters And Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }
}
