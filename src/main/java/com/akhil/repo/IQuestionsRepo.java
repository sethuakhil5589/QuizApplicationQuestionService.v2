package com.akhil.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.akhil.model.Questions;

public interface IQuestionsRepo extends JpaRepository<Questions, Integer> {
	List<Questions> findByTopic(String topic);
	@Query(value="SELECT * FROM QUESTIONS q WHERE topic=:topic ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
	List<Questions> findNumQByTopic(String topic, Integer numQ);
	
	 @Query("SELECT DISTINCT q.topic FROM Questions q")
	List<String> findAllTopic();
}
