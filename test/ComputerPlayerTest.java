package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import core.CheckersComputerPlayer;

public class ComputerPlayerTest {
	private final CheckersComputerPlayer computer = new CheckersComputerPlayer();
	
	@Test
	void validMoves() {
		assertEquals(computer.isValidNonJump("6a", "5b"), true);
		assertEquals(computer.isValidNonJump("5b", "6a"), false);
		assertEquals(computer.isValidNonJump("3b", "4c"), false);
		assertEquals(computer.isValidNonJump("6a", "6a"), false);
		assertEquals(computer.isValidNonJump("6a", "4c"), false);
	}
	
	@Test
	void randomMoves() {
		computer.generateMove();
	}
}
