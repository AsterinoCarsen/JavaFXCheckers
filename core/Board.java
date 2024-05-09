package core;

import java.util.NoSuchElementException;

/**
 * Behavior of the board, including data and the manipulations and rules of chess.
 * @author Carsen
 */
public class Board {
	private Cell[] board;
	
	/**
	 * Initializes the standard positions in chess, 12 pieces to each player.
	 */
	public Board() {
		this.board = new Cell[64];
	    
	    for (int row = 0; row < 8; row++) {
	        for (int col = 0; col < 8; col++) {
	            String name = String.valueOf(8 - row) + (char) ('a' + col);
	            
	            char value;
	            if ((row + col) % 2 == 0) {
	                if (row < 3) {
	                    value = 'o';
	                } else if (row >= 5) {
	                    value = 'x';
	                } else {
	                    value = ' ';
	                }
	            } else {
	                value = ' ';
	            }
	            
	            int index = row * 8 + col;
	            board[index] = new Cell(name, value);
	        }
	    }
	}
	
	/**
	 * Controls all rules of the game, moves pieces accordingly, deletes them, and rejects invalid moves.
	 * @param start start position.
	 * @param end end position.
	 * @param turn character value representing the current player's turn.
	 */
	public void play(String start, String end, char turn) {
	    Cell startCell = this.getCell(start);
	    Cell endCell = this.getCell(end);   
	    
	    // Check if the wrong piece is being moved.
	    if (startCell.get() != turn) {
	    	System.out.printf("\nInvalid Move: You cannot move the enemy's piece.\n");
	    	return;
	    }
	    
	    // Check if the end space is already occupied.
	    if (endCell.get() != ' ') {
	    	System.out.printf("\nInvalid Move: You cannot move into an occupied space.\n");
	    	return;
	    }
	    
	    // Check if number is out of bounds.
	    int digitStart = Integer.parseInt(start.substring(0, 1));
	    int digitEnd = Integer.parseInt(end.substring(0, 1));
	    if (digitStart > 8 || digitEnd > 8) {
	    	System.out.printf("\nInvalid Move: Selected positions are out of bounds.\n");
	    	return;
	    }
	    
	    // Check if letter is out of bounds.
	    int charStart = Character.getNumericValue(start.charAt(1));
	    int charEnd = Character.getNumericValue(end.charAt(1));
	    if (charStart > 17 || charEnd > 17) {
	    	System.out.printf("\nInvalid Move: Selected positions are out of bounds.\n");
	    	return;
	    }
	    
	    // Ensure the positions are diagonal.
	    if (!areDiagonal(start, end)) {
	    	System.out.printf("\nInvalid Move: Selected positions are not diagonal.\n");
	    	return;
	    }
	    
	    int dist = dist(start, end);
	    
	    // If the move is just one square, move it.
	    if (dist == 2) {
	    	movePiece(start, end);
	    	return;
	    }
	    
	    // If the player is trying to jump more than 2 pieces.
	    if (dist > 4) {
	    	System.out.printf("\nInvalid Move: You cannot move that far.\n");
	    	return;
	    }
	    
	    // Find the cell between the start and end, then delete it.
	    int avgDigit = (digitStart + digitEnd) / 2;
	    char avgChar = Character.forDigit(Math.round(((charStart + charEnd) / 2)), 16);
	    String betweenCellName = String.format("%d%c", avgDigit, avgChar);
	    Cell between = getCell(betweenCellName);
	    
	    if (between.get() == ' ') {
	    	System.out.printf("\nInvalid Move: You cannot move that far.\n");
	    	return;
	    }
	    
	    if (between.get() == turn) {
	    	System.out.printf("\nInvalid Move: You cannot jump your own piece.\n");
	    	return;
	    }
	    
	    // Delete the piece being jumped over.
	    board[getIndex(betweenCellName)].set(' ');
	    
	    this.movePiece(start, end); 
	}
	
	/**
	 * Helper method to determine if two points are diagonal to each other.  Is used in play().
	 * @param pos1 first position.
	 * @param pos2 second position.
	 * @return boolean representing the output.
	 */
	public boolean areDiagonal(String pos1, String pos2) {
		if (pos1.equals(pos2)) return false;
		
	    int row1 = 8 - Integer.parseInt(pos1.substring(0, 1));
	    int col1 = pos1.charAt(1) - 'a';
	    int row2 = 8 - Integer.parseInt(pos2.substring(0, 1));
	    int col2 = pos2.charAt(1) - 'a';

	    int rowDiff = Math.abs(row1 - row2);
	    int colDiff = Math.abs(col1 - col2);

	    return rowDiff == colDiff;
	}

	/**
	 * Retrieves and returns a copy of a Cell within the board given a name of the cell.
	 * @param pos
	 * @return
	 */
	public Cell getCell(String pos) {
		Cell out = new Cell();
		
		for (int i = 0; i < 64; i++) {
			if (board[i].getName().equals(pos)) {
				out = board[i];
				return out;
			}
		}
		
		throw new NoSuchElementException("The cell " + pos + " could not be found.");
	}
	
	/**
	 * Retrieves the index of a particular cell given it's name.  The index is the internal Cell array of length 64.
	 * @param pos
	 * @return
	 */
	protected int getIndex(String pos) {
		
		for (int i = 0; i < 64; i++) {
			if (board[i].getName().equals(pos)) return i;
		}
		
		throw new NoSuchElementException("The cell " + pos + " could not be found.");
	}
	
	/**
	 * Takes two positions, and moves piece from the first position to the next.
	 * @param start start position.
	 * @param end end position.
	 */
	public void movePiece(String start, String end) {
		int startIndex = 0;
		int endIndex = 0;
		
		for (int i = 0; i < 64; i++) {
			if (this.board[i].getName().equals(start))
				startIndex = i;
			else if (this.board[i].getName().equals(end))
				endIndex = i;
		}
		
		this.board[endIndex].set(this.board[startIndex].get());
		this.board[startIndex].set(' ');
	}
	
	/**
	 * Takes two positions on the board and gets the distance between them.
	 * @param start
	 * @param end
	 * @return
	 */
	public int dist(String start, String end) {
		int digitStart = Integer.parseInt(start.substring(0, 1));
	    int digitEnd = Integer.parseInt(end.substring(0, 1));
	    int charStart = Character.getNumericValue(start.charAt(1));
	    int charEnd = Character.getNumericValue(end.charAt(1));
		
		int dist = Math.abs(digitStart - digitEnd) + Math.abs(charStart - charEnd);
		
		return dist;
	}
	
	/**
	 * The current score of the game.
	 * @return an integer array of length 2 where the first element is the score of x, and the second element is the score of o.
	 */
	public int[] getScore() {
		int[] out = new int[2];
		
		for (Cell i : board) {
			if (i.get() == 'x')
				out[0]++;
			else if (i.get() == 'o')
				out[1]++;
		}
		
		return out;
	}
	
	public String toString() {
		StringBuilder out = new StringBuilder();
	    
		out.append("\n");
		
	    int counter = 8;
	    for (int i = 0; i < 64; i++) {
	        if (i % 8 == 0) {
	            out.append(counter--).append(" | ");
	        }
	        
	        out.append(board[i].get()).append(" | ");
	        
	        if ((i + 1) % 8 == 0) {
	            out.append("\n");
	        }
	    }
	    
	    out.append("    A   B   C   D   E   F   G   H\n");
	    
	    return out.toString();
	}
}
