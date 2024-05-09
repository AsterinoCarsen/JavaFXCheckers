package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import core.Board;

public class BoardTest {
	private final Board board = new Board();
	
	@Test
	void getString() {
		Board anotherBoard = new Board();
		anotherBoard.play("3b", "4a", 'x');
		
		assertNotEquals(board.toString(), anotherBoard.toString());
	}
	
	@Test
	void areDiagonal() {
		assertEquals(board.areDiagonal("1a", "2b"), true);
		
		assertEquals(board.areDiagonal("1b", "2a"), true);
		
		assertEquals(board.areDiagonal("1a", "3c"), true);
		
		assertEquals(board.areDiagonal("1a", "2a"), false);
		
		assertEquals(board.areDiagonal("2a", "1a"), false);
		
		assertEquals(board.areDiagonal("2a", "2b"), false);
		
		assertEquals(board.areDiagonal("1a", "1a"), false);
	}
	
	@Test
	void getCell() {
		assertEquals(board.getCell("1a").get(), ' ');
		assertEquals(board.getCell("1a").getName(), "1a");
		
		assertEquals(board.getCell("1b").get(), 'x');
		assertEquals(board.getCell("1b").getName(), "1b");
		
		assertEquals(board.getCell("8a").get(), 'o');
		assertEquals(board.getCell("8a").getName(), "8a");
		
		Exception exception = assertThrows(NoSuchElementException.class, () -> {
			board.getCell("9a");
		});
	}
	
	@Test
	void movingPieces() {
		assertEquals(board.getCell("3b").get(), 'x');
		assertEquals(board.getCell("4c").get(), ' ');
		
		board.movePiece("3b", "4c");
		
		assertEquals(board.getCell("3b").get(), ' ');
		assertEquals(board.getCell("4c").get(), 'x');
		
		board.movePiece("4c", "3b"); // Reset the positions
		
		assertEquals(board.getCell("6a").get(), 'o');
		assertEquals(board.getCell("5b").get(), ' ');
		
		board.movePiece("6a", "5b");
		
		assertEquals(board.getCell("6a").get(), ' ');
		assertEquals(board.getCell("5b").get(), 'o');
		
		board.movePiece("5b", "6a"); // Reset positions
	}
	
	@Test
	void getDistance() {
		assertEquals(board.dist("1a", "1a"), 0);
		assertEquals(board.dist("1a", "1b"), 1);
		assertEquals(board.dist("1a", "2b"), 2);
		assertEquals(board.dist("1a", "3c"), 4);
	}
	
	@Test
	void playTestWithCapture() {
		assertEquals(board.getScore()[0], 12);
		assertEquals(board.getScore()[1], 12);
		
		board.play("3b", "4a", 'x');
		board.play("6c", "5b", 'o');
		board.play("4a", "6c", 'x');
		
		assertEquals(board.getCell("4a").get(), ' ');
		assertEquals(board.getCell("5b").get(), ' ');
		assertEquals(board.getCell("6c").get(), 'x');
		
		assertEquals(board.getScore()[0], 12);
		assertEquals(board.getScore()[1], 11);
	}
}
