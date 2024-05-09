package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import core.Cell;

public class CellTest {
	@Test
	void changingValues() {
		Cell cell = new Cell();
		
		cell.set('x');
		assertEquals(cell.get(), 'x');
		
		cell.set('o');
		assertEquals(cell.get(), 'o');
		
		cell.set(' ');
		assertEquals(cell.get(), ' ');
	}
	
	@Test
	void constructorNames() {
		Cell cell1 = new Cell("1", ' ');
		Cell cell2 = new Cell("2", ' ');
		Cell cell3 = new Cell("3", ' ');
		
		assertEquals(cell1.getName(), "1");
		assertEquals(cell2.getName(), "2");
		assertEquals(cell3.getName(), "3");
	}
	
	@Test
	void constructorValues() {
		Cell cell1 = new Cell("", 'x');
		Cell cell2 = new Cell("", 'o');
		Cell cell3 = new Cell("", ' ');
		
		assertEquals(cell1.get(), 'x');
		assertEquals(cell2.get(), 'o');
		assertEquals(cell3.get(), ' ');
	}
}
