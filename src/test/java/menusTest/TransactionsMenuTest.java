package menusTest;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import menus.TransactionsMenu;

public class TransactionsMenuTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private ByteArrayInputStream inContent;
	TransactionsMenu tm;

	@Before
	public void setUpStreams() {
		tm = new TransactionsMenu();
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void showsCorrectOptionsTest() {
		String s = 	"Choose an option: \n"          
				  + "1. Add a transaction\n"             
				  + "2. Search for a transaction\n"      
				  + "3. Change a transaction's details\n"
				  + "4. Delete a transaction\n"          
				  + "5. Main Menu\n";               
		tm.showOptions();
		assertEquals(s, outContent.toString());
	}
	
	@Test
	public void chooseTest_1returns1() {
		inContent = new ByteArrayInputStream("1".getBytes());
		System.setIn(inContent);
		assertEquals(1,tm.choose());
	}
	
	@Test
	public void chooseTest_StringReturnsNegative1() {
		inContent = new ByteArrayInputStream("string".getBytes());
		System.setIn(inContent);
		assertEquals(-1,tm.choose());
	}
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setIn(System.in);
	}
}
