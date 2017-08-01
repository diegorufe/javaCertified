package com.javaCertified.beans;

import java.io.Serializable;
import java.util.List;

public class Question  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3895539462882722629L;
	private String text;
	private List<Answer> answers;
	private int indexSuccesQuestion;
	
	
	
	
	public Question() {
		super();
	}
	
	public Question(String text, List<Answer> answer, int indexSuccesQuestion) {
		super();
		this.text = text;
		this.answers = answer;
		this.indexSuccesQuestion = indexSuccesQuestion;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public int getIndexSuccesQuestion() {
		return indexSuccesQuestion;
	}
	public void setIndexSuccesQuestion(int indexSuccesQuestion) {
		this.indexSuccesQuestion = indexSuccesQuestion;
	}
	
	
}
