package DBFunctions;

import java.util.List;

import Models.ShoppingList;

public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runner run = new Runner();
		run.temp();
	}

	public void temp(){
		
		Controller con = new Controller();
		List temp = con.getShoppingList();
		con.storeList(temp);
	}
	
}
