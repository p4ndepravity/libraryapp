package menusTest;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import menus.PatronsMenu;

public class PatronsMenuTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private ByteArrayInputStream inContent;
	PatronsMenu pm;

	@Before
	public void setUpStreams() {
		pm = new PatronsMenu();
		System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void showsCorrectOptionsTest() {
		String s = 	"Choose an option: \n"          
				  + "1. Add a patron\n"             
				  + "2. Search for a patron\n"      
				  + "3. Change a patron's details\n"
				  + "4. Delete a patron\n"          
				  + "5. Main Menu\n";               
		pm.showOptions();
		assertEquals(s, outContent.toString());
	}
	
	@Test
	public void chooseTest_1returns1() {
		inContent = new ByteArrayInputStream("1".getBytes());
		System.setIn(inContent);
		assertEquals(1,pm.choose());
	}
	
	@Test
	public void chooseTest_StringReturnsNegative1() {
		inContent = new ByteArrayInputStream("string".getBytes());
		System.setIn(inContent);
		assertEquals(-1,pm.choose());
	}
	
	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setIn(System.in);
	}
}
