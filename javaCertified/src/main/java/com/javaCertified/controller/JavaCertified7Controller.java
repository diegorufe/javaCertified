package com.javaCertified.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.javaCertified.beans.Answer;
import com.javaCertified.beans.Question;
import com.javaCertified.beans.QuestionAnswer;
import com.javaCertified.constants.IJavaCertifiedConstants;
import com.javaCertified.jsonResolver.JsonResolver;

public class JavaCertified7Controller implements IController {

	private Scanner teclado = new Scanner(System.in);
	private int menuMasteroption = 0;
	private final int EXIT_MENU_MASTER_OPTION = 2;

	public String menuMasterOption() {
		return "1 - Start Java test certified 7 \n" + "2 - Exit\n";
	}

	public void resolveMasterOption(int option) {
		switch (option) {
		case 1:
			runTestProgram();
			break;
		case EXIT_MENU_MASTER_OPTION:
			menuMasteroption = EXIT_MENU_MASTER_OPTION;
			System.out.println("Thanks for using the test java 7 certified test proman, see toy later");
			break;
		default:
			break;
		}
	}

	public String start() {
		return "Welcome to the java 7 certified test examns. The questions are substract for test killer from manual "
				+ "of diferents books of a certified java exams.";
	}

	public void run() {
		System.out.println(start());
		while (menuMasteroption != EXIT_MENU_MASTER_OPTION) {
			try {
				System.out.println(menuMasterOption());
				menuMasteroption = teclado.nextInt();
			} catch (Exception e) {
				menuMasteroption = 0;
			}
			resolveMasterOption(menuMasteroption);
		}
	}

	/**
	 * Method to run test program for java 7
	 */
	public void runTestProgram() {
		QuestionAnswer questionsAnswer = null;
		try {
			questionsAnswer = JsonResolver
					.resolveQuestionAnswerClassLoader(IJavaCertifiedConstants.PATH_JAVA_CERTIFIED_7);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in java certified test program. Sorry for the error, the program wil be closed");
			System.exit(0);
		}
		List<Question> questions = questionsAnswer.getQuestions();
		Collections.shuffle(questions);
		int indexExit = -1;
		List<Question> responseQuestions = new ArrayList<Question>();
		System.out.println("Questions for test java certified 7");
		for (Question question : questions) {
			indexExit = resolveQuestion(question);
			if (indexExit == 0) {
				break;
			} else {
				responseQuestions.add(question);
			}
		}
		resolveFinalTest(responseQuestions);
	}

	/**
	 * Method to resolve question
	 * 
	 * @param question
	 */
	public int resolveQuestion(Question question) {
		int response = -1;
		while (response < 0 || response > question.getAnswers().size()) {
			System.out.println("Question: 	\n\t"+question.getText()+"\n");
			System.out.println(getTextAnswers(question));
			try {
				response = teclado.nextInt();
				if (response > question.getAnswers().size()) {
					System.out.println("Response error select the correct answer");
				} else {
					if (response == 0) {
						question.setSelectAnswer(-1);
					} else {
						question.setSelectAnswer(response);
						if(question.getAnswers().get(question.getSelectAnswer() - 1).getIndex() == question.getIndexSuccesQuestion()) {
							System.out.println("The response is succes\n\t"+
									"Explanation: \n\t\t"+question.getExplanation()+"\n");
						}else {
							System.err.println("The response is wrong\n\t"+
									"Succes answer: \n\t\t"+
									question.getSuccesAnswer(question.getIndexSuccesQuestion()).getText()+"\n"+
									"Explanation: \n\t\t"+question.getExplanation()+"\n");
						}
					}
				}
			} catch (Exception e) {
				response = -1;
				System.out.println("Response error select the correct answer");
			}
		}
		return response;
	}

	/**
	 * Method to get the text for answers
	 * 
	 * @param question
	 * @return
	 */
	public String getTextAnswers(Question question) {
		String text = "Answers\n\t0 - Exit\n\t";
		int index = 1;
		List<Answer> answuers = question.getAnswers();
		Collections.shuffle(answuers);
		for (Answer answer : answuers) {
			text += "" + index + " - " + answer.getText().trim() + "\n\t";
			index++;
		}
		return text;
	}
	
	/**
	 * Method to resolve final test
	 * @param questions
	 */
	public void resolveFinalTest(List<Question> questions) {
		int succesResponse = 0;
		int wrongResponse = questions.size();
		Answer answer = null;
		for (Question question : questions) {
			answer = question.getAnswers().get(question.getSelectAnswer() - 1);
			if (answer.getIndex() == question.getIndexSuccesQuestion()) {
				succesResponse++;
				wrongResponse--;
			}
		}
		System.out.println("Final results: \n"+
							"\tTotal response: "+(succesResponse+wrongResponse)+"\n"+
							"\tSucces response: "+succesResponse+"\n"+
							"\tWrong response: "+wrongResponse);
	}

}
