package com.javaCertified.controller;

import java.util.Scanner;

/**
 * 
 * @author diego
 *
 */
public class JavaCertifiedMainController implements IController {
	private Scanner teclado = new Scanner(System.in);
	private int menuMasteroption = 0;
	private final int EXIT_MENU_MASTER_OPTION = 2;

	public String menuMasterOption() {
		return "1 - Java test certified 7 \n" + "2 - Exit";
	}

	public void resolveMasterOption(int option) {
		switch (option) {
		case 1:
			new JavaCertified7Controller().run();
			break;
		case EXIT_MENU_MASTER_OPTION:
			menuMasteroption = EXIT_MENU_MASTER_OPTION;
			System.out.println("Thanks for using the test java certified program, see toy later");
			break;
		default:
			break;
		}
	}

	public String start() {
		return "Welcome to the java certified test examns. The questions are substract for test killer from manual "
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
}
