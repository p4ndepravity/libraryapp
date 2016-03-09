package menus;

import java.util.*;

public interface MenuInterface {
	void showOptions();
	List<String> askOptions();
	int choose();
	String respond();
	String respond(int index);
}
