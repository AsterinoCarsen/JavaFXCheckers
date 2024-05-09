package core;

/**
 * Parent class of the logic underlying chess.
 * @author Carsen
 *
 */
public class CheckersLogic {
	
	protected Board board;
	
	/**
	 * Initalizes board.
	 */
	public CheckersLogic() {
		this.board = new Board();
	}
	
	/**
	 * Controls all rules of the game, moves pieces accordingly, deletes them, and rejects invalid moves.
	 * @param start start position.
	 * @param end end position.
	 * @param turn character value representing the current player's turn.
	 */
	public void play(String start, String end, char turn) {
		this.board.play(start, end, turn);
	}
	
	/**
	 * The current score of the game.
	 * @return an integer array of length 2 where the first element is the score of x, and the second element is the score of o.
	 */
	public int[] getScore() {
		return this.board.getScore();
	}
	
	public String toString() {
		return this.board.toString();
	}
	
	/**
	 * Determines whether two points on the board are diagonal to one another.
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean isDiagonal(String start, String end) {
		return this.board.areDiagonal(start, end);
	}
	
	/**
	 * Retrieves a particular cell from a location within the board.
	 * @param pos
	 * @return
	 */
	public Cell getCell(String pos) {
		return this.board.getCell(pos);
	}
	
	/**
	 * Takes two positions on the board and gets the distance between them.
	 * @param start
	 * @param end
	 * @return
	 */
	public int dist(String start, String end) {
		return this.board.dist(start, end);
	}
}
