package com.javaCertified.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.javaCertified.beans.QuestionAnswer;
import com.javaCertified.constants.IJavaCertifiedConstants;
import com.javaCertified.jsonResolver.JsonResolver;

public class JavaCertified7Controller implements IController {

	private Scanner teclado = new Scanner(System.in);
	private int menuMasteroption = 0;
	private final int EXIT_MENU_MASTER_OPTION = 2;

	public String menuMasterOption() {
		return "1 - Start Java test certified 7 \n" + "2 - Exit";
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
	}

}
