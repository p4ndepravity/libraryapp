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
		PatronsDB patrons = new PatronsDB();
		
		int mainChoice = 0;
		do {
			int secondChoice = 0;
			int searchFieldIndex;
			int columnNum;
			String newValue;
			String response;
			MainMenu m = new MainMenu();
			m.showOptions();
			mainChoice = m.choose();
			switch (mainChoice) {
			
			//////////////////////////////////////////////////////
			//	Books Menu										//
			//////////////////////////////////////////////////////
			case 1:
				BooksMenu bm = new BooksMenu();
				String bookid;
				bm.showOptions();
				secondChoice = bm.choose();
				switch (secondChoice) {
				
				// Add books menu
				case 1:
					AddMenu bam = new AddMenu(bm);
					Book b = new Book(bam.askOptions());
					books.add(b);
					break;
				
				// Search for books menu
				case 2:
					SearchMenu bsm = new SearchMenu(bm);
					bsm.showOptions();
					searchFieldIndex = bsm.choose();
					if (searchFieldIndex > 5 || searchFieldIndex < 1) break;
					response = bsm.respond(searchFieldIndex);
					bsm.printResults(searchFieldIndex, response, books);
					break;
					
				// Change a book menu
				case 3:
					ChangeMenu bcm = new ChangeMenu(bm);
					bookid = bcm.askOptions().get(0);
					bcm.showOptions();
					columnNum = bcm.choose();
					if (columnNum > 6 || columnNum < 1) break;
					newValue = bcm.respond(columnNum+1);
					books.change(bookid, columnNum, newValue);
					break;
					
				// Delete a book menu
				case 4:
					DeleteMenu bdm = new DeleteMenu(bm);
					bookid = bdm.askOptions().get(0);
					books.delete(bookid);
					break;
					
				case 5:
					secondChoice = -1;
					break;
				default:
					System.out.println("Unrecognized input.");
					break;
				}
				break;
				
			//////////////////////////////////////////////////////
			//	Patrons Menu									//
			//////////////////////////////////////////////////////
			case 2:
				PatronsMenu pm = new PatronsMenu();
				String patronid;
				pm.showOptions();
				secondChoice = pm.choose();
				switch (secondChoice) {
				
				// Add patron menu
				case 1:
					AddMenu pam = new AddMenu(pm);
					Patron p = new Patron(pam.askOptions());
					patrons.add(p);
					break;

				// Search for patrons menu
				case 2:
					SearchMenu psm = new SearchMenu(pm);
					psm.showOptions();
					searchFieldIndex = psm.choose();
					if (searchFieldIndex < 1 || searchFieldIndex > 7) break;
					response = psm.respond(searchFieldIndex);
					psm.printResults(searchFieldIndex, response, patrons);
					break;
					
				// Change a patron menu	
				case 3:
					ChangeMenu pcm = new ChangeMenu(pm);
					patronid = pcm.askOptions().get(0);
					pcm.showOptions();
					columnNum = pcm.choose();
					if (columnNum < 1 || columnNum > 6) break;
					newValue = pcm.respond(columnNum+1);
					if (columnNum == 5) newValue = newValue.toUpperCase();
					patrons.change(patronid, columnNum, newValue);
					break;
					
				// Delete a patron menu
				case 4:
					DeleteMenu pdm = new DeleteMenu(pm);
					patronid = pdm.askOptions().get(0);
					patrons.delete(patronid);
					break;
					
				case 5:
					secondChoice = -1;
					break;
				default:
					System.out.println("Unrecognized input.");
					break;
				}
				break;
				
			//////////////////////////////////////////////////////
			//	Transactions Menu								//
			//////////////////////////////////////////////////////
			case 3:
				TransactionsMenu tm = new TransactionsMenu();
				tm.showOptions();
				secondChoice = tm.choose();
				break;
				
			case 4:
				System.out.println("Exiting.");
				mainChoice = 0;
				break;
			default:
				System.out.println("Unrecognized input.");
				mainChoice = -1;
				break;
			}
		} while (mainChoice != 0);

	}

}
