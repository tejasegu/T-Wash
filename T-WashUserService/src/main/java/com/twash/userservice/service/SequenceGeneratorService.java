package com.twash.userservice.service;

import java.util.Objects;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.twash.userservice.model.DatabaseSequence;

/**
 * This Service Is Used To Generate Custom Id And In sequential Manner 
 *
 */
@Service

public class SequenceGeneratorService {
	private MongoOperations mongooperations; //Used For Mongo Operations
	
	 @Autowired
	    public SequenceGeneratorService(MongoOperations mongoOperations) {
	        this.mongooperations = mongoOperations;
	    }
        //Method For Increasing the Value Of Sequence In SequenceGenerator Database
	    public long generateSequence(String seqName) {
            // Get Sequence Number And Updates It In Users Document
	        DatabaseSequence counter = mongooperations.findAndModify(query(where("_id").is(seqName)),
	                new Update().inc("sequence",1), options().returnNew(true).upsert(true),
	                DatabaseSequence.class);
	        return !Objects.isNull(counter) ? counter.getSequence() :1;

	    }
	    // Method For Decreasing The Sequence In Case Of Mongo Insertion Error In Users Database 
	    public void decreamentSequence(String seqName) {

	        mongooperations.findAndModify(query(where("_id").is(seqName)),
	                new Update().inc("sequence",-1), options().returnNew(true).upsert(true),
	                DatabaseSequence.class);

	    }

}
