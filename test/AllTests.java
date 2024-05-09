package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@SelectClasses({CellTest.class, BoardTest.class, LogicTest.class, ComputerPlayerTest.class})

public class AllTests {
}
