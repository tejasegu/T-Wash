package com.twash.userservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.twash.userservice.model.Reviews;
import com.twash.userservice.repository.ReviewsRepository;


@DataMongoTest
public class ReviewsRepositoryTest {
	
	@Autowired
	private ReviewsRepository repotest;

	String id="1";
	String username="Teja";
	String userid="21009";
	String washerid="21028";
	String review="Super";
	long rating=4;
	
	Reviews revie=new Reviews(id,username,userid,washerid,review,rating);

	@Test
	void itShouldselectReviewsByWasherId() {
		 // Given
         
		repotest.save(revie);
        Optional<List<Reviews>> optionalreview=repotest.findByWasherid(washerid);
        
        assertThat(optionalreview).isPresent();

	}
	
	@Test
	void itShouldnotselectReviewIfWasherIdNotPresent() {
		 // Given
         
		repotest.save(revie);
        List<Reviews> optionalreview=repotest.findByWasherid("12").get();
        
        assertThat(optionalreview).isEmpty();

	}
}
