package main;

import data.*;
import menus.*;

public class Application {

	public static void main(String[] args) throws Exception {

		/*
		 * try {
		 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 * } catch (ClassNotFoundException e) { e.printStackTrace(); } catch
		 * (InstantiationException e) { e.printStackTrace(); } catch
		 * (IllegalAccessException e) { e.printStackTrace(); } catch
		 * (UnsupportedLookAndFeelException e) { e.printStackTrace(); }
		 */

		@SuppressWarnings("unused")
		BooksDB books = new BooksDB();
		int choice = 0;

		do {
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
				bm.choose();
				break;
				
			//////////////////////////////////////////////////////
			//	Patrons Menu									//
			//////////////////////////////////////////////////////
			case 2:
				PatronsMenu pm = new PatronsMenu();
				pm.showOptions();
				pm.choose();
				break;
				
			//////////////////////////////////////////////////////
			//	Transactions Menu								//
			//////////////////////////////////////////////////////
			case 3:
				TransactionsMenu tm = new TransactionsMenu();
				tm.showOptions();
				tm.choose();
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
