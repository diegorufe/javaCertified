package com.javaCertified.controller;

public interface IController {
	/**
	 * Menú master options
	 * 
	 * @return
	 */
	public String menuMasterOption();

	/**
	 * Mehtod to resolve master option
	 * 
	 * @param option
	 */
	public void resolveMasterOption(int option);
	/**
	 * Method to get message start program
	 * @return
	 */
	public String start();
	/**
	 * Method to run the controller
	 */
	public void run();
}
