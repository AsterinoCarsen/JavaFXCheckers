package ui;

import core.CheckersComputerPlayer;
import core.CheckersLogic;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Contains the loop for the game, and printing to the console.
 * @author Carsen
 *
 */
public class CheckersTextConsole {
	protected CheckersLogic logic;
	protected CheckersComputerPlayer computerPlayer;
	
	/**
	 * Initalizes the game logic, and starts the game loop.
	 * @throws InterruptedException 
	 */
	public CheckersTextConsole() throws InterruptedException {
		logic = new CheckersLogic();
		computerPlayer = new CheckersComputerPlayer();
		Scanner scnr = new Scanner(System.in);
		
		System.out.printf("Choose which gamemode do you wish to play.\nx = versus\no = computer\n");
		String choice = scnr.nextLine();
		
		if (choice.equals("x")) {
			System.out.printf("\nPlaying in versus mode.\n");
			versusLoop();
		} else if (choice.equals("o")) {
			System.out.printf("\nPlaying in computer mode.\n");
			computerLoop();
		} else {
			System.out.printf("\nIncorrect input, exiting runtime.\n");
		}
	}
	
	/**
	 * Checks the current count of both player's pieces, ends the runtime if one player has won.
	 */
	protected void checkScoresComputer() {
		int[] scores = computerPlayer.getScore();
		if (scores[0] <= 0) {
			System.out.println("O has won!");
			System.exit(0);
		} else if (scores[1] <= 0) {
			System.out.println("X has won!");
			System.exit(0);
		}
	}
	
	/**
	 * Checks the current count of both player's pieces, ends the runtime if one player has won.
	 */
	protected void checkScoresVersus() {
		int[] scores = logic.getScore();
		if (scores[0] <= 0) {
			System.out.println("O has won!");
			System.exit(0);
		} else if (scores[1] <= 0) {
			System.out.println("X has won!");
			System.exit(0);
		}
	}
	
	/**
	 * Repeated cycle of the game in a human vs. human match.
	 */
	protected void versusLoop() {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println(logic.toString());
		System.out.println("Player X - your turn.");
		System.out.println("Chose a piece to move.");
		String start = scnr.nextLine();
		System.out.println("Chose a location to move it.");
		String end = scnr.nextLine();
		
		logic.play(start, end, 'x');
		//System.out.println(logic.toString());
		
		checkScoresVersus();
		
		System.out.println(logic.toString());
		System.out.println("Player O - your turn.");
		System.out.println("Chose a piece to move.");
		String start1 = scnr.nextLine();
		System.out.println("Chose a location to move it.");
		String end1 = scnr.nextLine();
		
		logic.play(start1, end1, 'o');
		//System.out.println(logic.toString());
		
		checkScoresVersus();
		
		versusLoop();
	}
	
	/**
	 * Repeated cycle of the game in a human vs. computer match using the CheckersComputerPlayer AI.
	 * @throws InterruptedException wait time for generation of move.
	 */
	protected void computerLoop() throws InterruptedException {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println(computerPlayer.toString());
		System.out.println("Player X - your turn.");
		System.out.println("Chose a piece to move.");
		String start = scnr.nextLine();
		System.out.println("Chose a location to move it.");
		String end = scnr.nextLine();
		
		computerPlayer.play(start, end, 'x');
		
		checkScoresComputer();
		
		System.out.println(computerPlayer.toString());
		System.out.printf("\nGenerating a move...\n");
		TimeUnit.SECONDS.sleep(2);
		String[] move = computerPlayer.generateMove();
		computerPlayer.play(move[0], move[1], 'o');
		
		checkScoresComputer();
		
		computerLoop();
	}
	
}
