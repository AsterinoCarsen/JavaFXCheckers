package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import core.CheckersLogic;

public class LogicTest {
	
	private final CheckersLogic logic = new CheckersLogic();
	
	@Test
	void getDistance() {
		assertEquals(logic.dist("1a", "1a"), 0);
		assertEquals(logic.dist("1a", "1b"), 1);
		assertEquals(logic.dist("1a", "2b"), 2);
		assertEquals(logic.dist("1a", "3c"), 4);
	}
	
	@Test
	void getCell() {
		assertEquals(logic.getCell("1a").get(), ' ');
		assertEquals(logic.getCell("1a").getName(), "1a");
		
		assertEquals(logic.getCell("1b").get(), 'x');
		assertEquals(logic.getCell("1b").getName(), "1b");
		
		assertEquals(logic.getCell("8a").get(), 'o');
		assertEquals(logic.getCell("8a").getName(), "8a");
		
		Exception exception = assertThrows(NoSuchElementException.class, () -> {
			logic.getCell("9a");
		});
	}
	
	@Test
	void isDiagonal() {
		assertEquals(logic.isDiagonal("1a", "2b"), true);
		
		assertEquals(logic.isDiagonal("1b", "2a"), true);
		
		assertEquals(logic.isDiagonal("1a", "3c"), true);
		
		assertEquals(logic.isDiagonal("1a", "2a"), false);
		
		assertEquals(logic.isDiagonal("2a", "1a"), false);
		
		assertEquals(logic.isDiagonal("2a", "2b"), false);
		
		assertEquals(logic.isDiagonal("1a", "1a"), false);
	}
	
	@Test
	void playTest() {
		assertEquals(logic.getScore()[0], 12);
		assertEquals(logic.getScore()[1], 12);
		
		logic.play("3b", "4a", 'x');
		logic.play("6c", "5b", 'o');
		logic.play("4a", "6c", 'x');
		
		assertEquals(logic.getCell("4a").get(), ' ');
		assertEquals(logic.getCell("5b").get(), ' ');
		assertEquals(logic.getCell("6c").get(), 'x');
		
		assertEquals(logic.getScore()[0], 12);
		assertEquals(logic.getScore()[1], 11);
	}
}
