package core;

/**
 * Behavior of an individual square on a checkers board.
 * 
 * Has a name notation from "1A" to "8H".
 * 
 * Each cell contains either a x, o, or space, representing occupied by either piece type or empty.
 * @author Carsen
 *
 */
public class Cell {
	private String name;
	private char value;
	
	/**
	 * Name and value of this cell defaults to space.
	 */
	public Cell() {
		this.value = ' ';
		this.name = " ";
	}
	
	/*
	 * Sets the internal values to the parameters.
	 */
	public Cell(String name, char val) {
		this.name = name;
		this.value = val;
	}
	
	/**
	 * @return the value of what's currently on this cell, whether an x, o, or empty, represented as a space.
	 */
	public char get() {
		return this.value;
	}
	
	/**
	 * @return get the name of this cell, ex. "8B", "3C", as a string.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @param piece, sets the value of this cell to either an x or and o.
	 */
	public void set(char piece) {
		if (piece == 'x' || piece == 'o' || piece == ' ') this.value = piece;
	}
}
