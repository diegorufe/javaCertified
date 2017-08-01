package com.javaCertified.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author diego
 *
 */
public class QuestionAnswer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9125358521686772860L;
	
	private List<Question> questions;

	public QuestionAnswer() {
		super();
	}
	

	public QuestionAnswer(List<Question> questions) {
		super();
		this.questions = questions;
	}


	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	

	

	

	

}
