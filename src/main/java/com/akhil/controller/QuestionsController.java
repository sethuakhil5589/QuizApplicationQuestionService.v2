package com.akhil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhil.model.Answers;
import com.akhil.model.Questions;
import com.akhil.model.QuestionsOnly;
import com.akhil.service.QuestionsServiceImpl;

@RestController
@RequestMapping("/questions")
public class QuestionsController {
	@Autowired
	private QuestionsServiceImpl service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Questions>> fetchingQuestions(){
		return new ResponseEntity<>(service.fetchQuestions(),HttpStatus.OK);
	}
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<QuestionsOnly>> fetchingQuestionsOnly(){
		return new ResponseEntity<>(service.fetchQuestionsOnly(),HttpStatus.OK);
	}
	
	@PostMapping("/addQuestion")
	public ResponseEntity<String> savingData(@RequestBody Questions question){
		return new ResponseEntity<>(service.savingData(question),HttpStatus.CREATED);
	}
	
	@GetMapping("/fetch/{topic}")
	public ResponseEntity<List<Questions>> fetchingQuestionsByTopic(@PathVariable String topic){
		return new ResponseEntity<>(service.fetchingByTopic(topic),HttpStatus.OK);
	}
	
	@GetMapping("/getQuestions/{topic}")
	public ResponseEntity<List<QuestionsOnly>> fetchingQuestionsOnlyByTopic(@PathVariable String topic){
		return new ResponseEntity<>(service.fetchQuestionsOnlyByTopic(topic),HttpStatus.OK);
	}
	
	@PostMapping("/create/{topic}/{numQ}")
	public ResponseEntity<List<Integer>> createQuiz(@PathVariable String topic, @PathVariable Integer numQ){
		return new ResponseEntity<>(service.createQuizByNumQTopic(topic, numQ),HttpStatus.CREATED);
	}
	
	@PostMapping("/fetchByQNums")
	public ResponseEntity<List<QuestionsOnly>> fetchQuestionsByQuestionNums(@RequestBody List<Integer> questionNumbers){
		return new ResponseEntity<>(service.fetchByQNums(questionNumbers),HttpStatus.OK);
	}
	
	@PostMapping("/submit")
	public ResponseEntity<Integer> gettingScore(@RequestBody List<Answers> answers){
		return new ResponseEntity<>(service.getScore(answers),HttpStatus.OK);
	}
	
	@GetMapping("/getTopics")
	public ResponseEntity<List<String>> findAllTitles(){
		return new ResponseEntity<>(service.findAllTopics(),HttpStatus.FOUND);
	}
	
	@PutMapping("/updateQuestion")
	public ResponseEntity<String> updateQuestion(@RequestBody Questions question){
		return new ResponseEntity<>(service.updateQuestion(question),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteQuestion/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
		return new ResponseEntity<>(service.deleteQuestion(id),HttpStatus.OK);
	}
	@GetMapping("/getQuestion/{questionId}")
	public ResponseEntity<Questions> getQuestionById(@PathVariable Integer questionId){
		return new ResponseEntity<>(service.getQuestionById(questionId),HttpStatus.OK);
	}
}
