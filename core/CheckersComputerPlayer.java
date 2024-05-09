package core;

import java.util.Random;

/**
 * An implementation of a computer AI which extends the base logic of checkers, in replacement of.  The AI will never attempt to take an enemy piece.
 * @author Carsen
 *
 */
public class CheckersComputerPlayer extends CheckersLogic {
	
	/**
	 * Generates a valid move on the board for the "o" player, will never attempt to take enemy pieces.
	 * 
	 * Picks a random, valid, 1 distance move.
	 * 
	 * @return string array of length 2 where the first element is the chosen piece, and the second element is where it is moving that piece.
	 */
	public String[] generateMove() {
		String[] move = new String[2];
		char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
		Random rand = new Random();
		
		move[0] = "1a";
		move[1] = "1a";
		
		while (!isValidNonJump(move[0], move[1])) {
			move[0] = String.format("%d%c", rand.nextInt(1, 9), letters[rand.nextInt(0, 8)]);
			move[1] = String.format("%d%c", rand.nextInt(1, 9), letters[rand.nextInt(0, 8)]);
		}
		
		System.out.printf("\nChosen move: %s-%s\n", move[0], move[1]);
		
		return move;
	}
	
	/**
	 * Determines if two points on the board is a valid move to make.  Doesn't accept valid jumps.  Helper method for generateMove() but works disintegrated.
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean isValidNonJump(String start, String end) {
		
		if (!isDiagonal(start, end)) return false;
		if (dist(start, end) > 2) return false;
		
		Cell startCell = getCell(start);
		Cell endCell = getCell(end);
		
		if (startCell.get() != 'o') return false;
		if (endCell.get() == 'x' || endCell.get() == 'o') return false;
		
		return true;
	}
}
