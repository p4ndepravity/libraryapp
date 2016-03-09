package main;

import data.*;
import menus.*;
import models.*;

public class Application {

	public static void main(String[] args) throws Exception {

		/* Implements visual style for UI elements
		 * 
		 * try {
		 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 * } catch (ClassNotFoundException e) { e.printStackTrace(); } catch
		 * (InstantiationException e) { e.printStackTrace(); } catch
		 * (IllegalAccessException e) { e.printStackTrace(); } catch
		 * (UnsupportedLookAndFeelException e) { e.printStackTrace(); }
		 */

		BooksDB books = new BooksDB();
		int choice = 0;

		do {
			int choice2 = 0;
			MainMenu m = new MainMenu();
			m.showOptions();
			choice = m.choose();
			switch (choice) {
			
			//////////////////////////////////////////////////////
			//	Books Menu										//
			//////////////////////////////////////////////////////
			case 1:
				BooksMenu bm = new BooksMenu();
				bm.showOptions();
				choice2 = bm.choose();
				switch (choice2) {
				
				// Add books menu
				case 1:
					AddMenu am = new AddMenu(bm);
					Book b = new Book(am.askOptions());
					books.add(b);
					break;
				
				// Search for books menu
				case 2:
					SearchMenu sm = new SearchMenu(bm);
					sm.showOptions();
					int searchFieldIndex = sm.choose();
					if (searchFieldIndex > 5 || searchFieldIndex < 1) break;
					String response = sm.respond(searchFieldIndex);
					sm.printResults(searchFieldIndex, response, books);
					break;
					
				// Change a book menu
				case 3:
					ChangeMenu cm = new ChangeMenu(bm);
					String bookid = cm.askOptions().get(0);
					cm.showOptions();
					int columnNum = cm.choose();
					if (columnNum > 5 || columnNum < 1) break;
					String newValue = cm.respond(columnNum);
					books.change(bookid, columnNum, newValue);
					break;
					
				// Delete a book menu
				case 4:
					//TODO
					break;
					
				default:
					break;
				}
				break;
				
			//////////////////////////////////////////////////////
			//	Patrons Menu									//
			//////////////////////////////////////////////////////
			case 2:
				PatronsMenu pm = new PatronsMenu();
				pm.showOptions();
				choice2 = pm.choose();
				break;
				
			//////////////////////////////////////////////////////
			//	Transactions Menu								//
			//////////////////////////////////////////////////////
			case 3:
				TransactionsMenu tm = new TransactionsMenu();
				tm.showOptions();
				choice2 = tm.choose();
				break;
				
			case 4:
				System.out.println("Exiting.");
				choice = 0;
				break;
			default:
				System.out.println("Unrecognized input.");
				choice = -1;
				break;
			}
		} while (choice != 0);

	}

}
