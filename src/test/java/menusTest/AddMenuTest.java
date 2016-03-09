package menusTest;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import menus.*;

public class AddMenuTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	AddMenu am;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void showsCorrectPromptsForBooksMenuTest() {
		BooksMenu bm = new BooksMenu();
		am = new AddMenu(bm);
		String s = 	"Enter the ID: \n"                               
				  + "Enter the title: \n"                            
				  + "Enter the author's last name: \n"                  
				  + "Enter the author's first name: \n"                 
				  + "Enter the librarians rating (1=worst 10=best): \n";
		am.showOptions();
		assertEquals(s, outContent.toString());
	}
	
	@Test
	public void showsCorrectPromptsForPatronsMenuTest() {
		PatronsMenu pm = new PatronsMenu();
		am = new AddMenu(pm);
		String s = 	"Enter the ID: \n"            
				  + "Enter the last name: \n"     
				  + "Enter the first name: \n"    
				  + "Enter the street address: \n"
				  + "Enter the city: \n"          
				  + "Enter the state: \n"         
				  + "Enter the zipcode: \n";       
		am.showOptions();
		assertEquals(s, outContent.toString());
	}
	
	@Test
	public void showsCorrectPromptsForTransactionsMenuTest() {
		TransactionsMenu tm = new TransactionsMenu();
		am = new AddMenu(tm);
		String s = 	"Enter the ID: \n"                           
				  + "Enter the patron's ID: \n"                  
				  + "Enter the book's ID: \n"                    
				  + "Enter the transaction date (mm-dd-yyyy): \n"
				  + "Enter the transaction type (1-7): \n";
		am.showOptions();
		assertEquals(s, outContent.toString());
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setIn(System.in);
	}

}
