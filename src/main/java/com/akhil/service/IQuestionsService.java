package com.akhil.service;

import java.util.List;

import com.akhil.model.Answers;
import com.akhil.model.Questions;
import com.akhil.model.QuestionsOnly;

public interface IQuestionsService {
	List<Questions> fetchQuestions();
	List<QuestionsOnly> fetchQuestionsOnlyByTopic(String topic);
	String savingData(Questions question);
	List<Questions> fetchingByTopic(String topic);
	List<Integer> createQuizByNumQTopic(String topic, Integer numQ);
	List<QuestionsOnly> fetchByQNums(List<Integer> questionNums);
	Integer getScore(List<Answers> answers);
	List<String> findAllTopics();
	List<QuestionsOnly> fetchQuestionsOnly();
	String updateQuestion(Questions question);
	String deleteQuestion(Integer id);
	Questions getQuestionById(Integer questionId);
}
