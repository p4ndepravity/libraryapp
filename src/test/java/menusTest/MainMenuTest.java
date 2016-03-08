package menusTest;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import menus.MainMenu;

public class MainMenuTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private ByteArrayInputStream inContent;
	MainMenu mm;

	@Before
	public void setUpStreams() {
		mm = new MainMenu();
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void showsCorrectOptionsTest() {
		String s = "Choose an option: \n"
				 + "1. Books\n"
				 + "2. Patrons\n"
				 + "3. Transactions\n"
				 + "4. Exit\n";
		mm.showOptions();
		assertEquals(s, outContent.toString());
	}
	
	@Test
	public void chooseTest_1returns1() {
		inContent = new ByteArrayInputStream("1".getBytes());
		System.setIn(inContent);
		assertEquals(1,mm.choose());
	}
	
	@Test
	public void chooseTest_StringReturnsNegative1() {
		inContent = new ByteArrayInputStream("string".getBytes());
		System.setIn(inContent);
		assertEquals(-1,mm.choose());
	}
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setIn(System.in);
	}

}
