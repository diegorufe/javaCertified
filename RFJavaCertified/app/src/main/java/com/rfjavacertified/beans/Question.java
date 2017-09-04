package com.rfjavacertified.beans;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3895539462882722629L;
	private String text;
	private List<Answer> answers;
	private int indexSuccesQuestion;
	private int selectAnswer;
	private String explanation;

	public Question() {
		super();
		this.selectAnswer = -1;
		this.explanation = "";
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

	public int getSelectAnswer() {
		return selectAnswer;
	}

	public void setSelectAnswer(int selectAnswer) {
		this.selectAnswer = selectAnswer;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Answer getSuccesAnswer(int indexSuccesAnswer) {
		for (Answer answer : answers) {
			if (answer.getIndex() == indexSuccesAnswer) {
				return answer;
			}
		}
		return null;
	}

}
