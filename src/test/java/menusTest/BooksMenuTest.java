package menusTest;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import menus.*;

public class BooksMenuTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private ByteArrayInputStream inContent;
	BooksMenu bm;

	@Before
	public void setUpStreams() {
		bm = new BooksMenu();
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void showsCorrectOptionsTest() {
		String s = 	"Choose an option: \n"
				  + "1. Add a book\n"
				  + "2. Search for a book\n"
				  + "3. Change a book's details\n"
				  + "4. Delete a book\n"
				  + "5. Main Menu\n";
		bm.showOptions();
		assertEquals(s, outContent.toString());
	}
	
	@Test
	public void chooseTest_1returns1() {
		inContent = new ByteArrayInputStream("1".getBytes());
		System.setIn(inContent);
		assertEquals(1,bm.choose());
	}
	
	@Test
	public void chooseTest_StringReturnsNegative1() {
		inContent = new ByteArrayInputStream("string".getBytes());
		System.setIn(inContent);
		assertEquals(-1,bm.choose());
	}
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setIn(System.in);
	}
}
