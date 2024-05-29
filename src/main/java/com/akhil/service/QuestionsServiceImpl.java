package com.akhil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhil.model.Answers;
import com.akhil.model.Questions;
import com.akhil.model.QuestionsOnly;
import com.akhil.repo.IQuestionsRepo;

@Service
public class QuestionsServiceImpl implements IQuestionsService{
	@Autowired
	private IQuestionsRepo questionsRepo;
	
	@Override
	public List<Questions> fetchQuestions() {
		return questionsRepo.findAll();
	}

	@Override
	public String savingData(Questions question) {
		return "Data Saved with id: "+questionsRepo.save(question).getQuestionId();
	}

	@Override
	public List<Questions> fetchingByTopic(String topic) {
		return questionsRepo.findByTopic(topic);
	}

	@Override
	public List<Integer> createQuizByNumQTopic(String topic, Integer numQ) {
		List<Questions> questions = questionsRepo.findNumQByTopic(topic, numQ);
		List<Integer> questionNumbers=new ArrayList<>();
		for(Questions question:questions) {
			questionNumbers.add(question.getQuestionId());
		}
		return questionNumbers;
	}

	@Override
	public List<QuestionsOnly> fetchByQNums(List<Integer> questionNums) {
		List<QuestionsOnly> questionsList=new ArrayList<>();
		for (Integer num:questionNums) {
			Questions question = questionsRepo.findById(num).get();
			QuestionsOnly questionOnly=new QuestionsOnly(question.getQuestionId(),question.getQuestion(),
					question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
			questionsList.add(questionOnly);
		}
		return questionsList;
	}

	@Override
	public Integer getScore(List<Answers> answers) {
		Integer result=0;
		for(Answers answer:answers) {
			Questions question = questionsRepo.findById(answer.getId()).get();
			if(question.getAnswer().equals(answer.getAnswer()))
				result++;
		}
		return result;
	}

	@Override
	public List<QuestionsOnly> fetchQuestionsOnlyByTopic(String topic) {
		List<QuestionsOnly> questionsOnlyList=new ArrayList<>();
		List<Questions> questionList = questionsRepo.findByTopic(topic);
		for (Questions question:questionList) {
			QuestionsOnly questionOnly=new QuestionsOnly(question.getQuestionId(),question.getQuestion(),
					question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
			questionsOnlyList.add(questionOnly);
		}
		return questionsOnlyList;
	}

	@Override
	public List<String> findAllTopics() {
		return questionsRepo.findAllTopic();
	}

	@Override
	public List<QuestionsOnly> fetchQuestionsOnly() {
		List<Questions> questionsList = questionsRepo.findAll();
		List<QuestionsOnly> questionsOnlyList= new ArrayList<>();
		for(Questions question:questionsList) {
			QuestionsOnly questionOnly=new QuestionsOnly(question.getQuestionId(),question.getQuestion(),
					question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
			questionsOnlyList.add(questionOnly);
		}
		return questionsOnlyList;
	}

	@Override
	public String updateQuestion(Questions question) {
		Questions actualQuestion =questionsRepo.findById(question.getQuestionId()).get();
		actualQuestion.setAnswer(question.getAnswer());
		actualQuestion.setOption1(question.getOption1());
		actualQuestion.setOption2(question.getOption2());
		actualQuestion.setOption3(question.getOption3());
		actualQuestion.setOption4(question.getOption4());
		actualQuestion.setQuestion(question.getQuestion());
		actualQuestion.setTopic(question.getTopic());
		actualQuestion.setQuestionId(question.getQuestionId());
		questionsRepo.save(actualQuestion);
		return "Data saved with Question Id: "+actualQuestion.getQuestionId();
	}

	@Override
	public String deleteQuestion(Integer id) {
		questionsRepo.deleteById(id);
		return "Question deleted with Id: "+id;
	}

	@Override
	public Questions getQuestionById(Integer questionId) {
		return questionsRepo.findById(questionId).get();
	}
	
	
	
}
