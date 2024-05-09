package core;

import java.util.Scanner;

import ui.CheckersGUI;
import ui.CheckersTextConsole;

/**
 * Start point of the game.
 * @author Carsen
 *
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Would you like to use a console-based UI or GUI?");
		System.out.println("x = console based");
		System.out.println("o = GUI based");
		String choice = scnr.nextLine();
		
		if (choice.equals("x")) {
			CheckersTextConsole game = new CheckersTextConsole();
		} else if (choice.equals("o")) {
			CheckersGUI game = new CheckersGUI();
			System.out.println("Would you like to play against a computer or in versus mode?");
			System.out.println("x = versus");
			System.out.println("o = computer");
			String secondChoice = scnr.nextLine();
			
			if (secondChoice.equals("x")) {
				args = new String[0];
				game.main(args);
			} else if (secondChoice.equals("o")) {
				args = new String[1];
				game.main(args);
			} else {
				
			}
		} else {
			System.out.println("Incorrect input, exiting runtime.");
		}
	}
}
