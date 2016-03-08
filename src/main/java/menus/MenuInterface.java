package menus;

import java.util.*;

public interface MenuInterface {
	void showOptions();
	int choose();
	List<String> askOptions();
	String respond();
}
