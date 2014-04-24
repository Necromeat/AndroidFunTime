package DBFunctions;

import java.util.ArrayList;
import java.util.List;

import Models.ShoppingList;

public class DummyData {

	public List<ShoppingList> getShoppingList(){
			ArrayList<ShoppingList> dummylist = new ArrayList();
		 dummylist.add(new ShoppingList(1,"a"));
			dummylist.add(new ShoppingList(2,"b"));
			dummylist.add(new ShoppingList(3,"c"));
			dummylist.add(new ShoppingList(4,"d"));
			dummylist.add(new ShoppingList(5,"e"));
			dummylist.add(new ShoppingList(6,"f"));
			dummylist.add(new ShoppingList(7,"g"));
			dummylist.add(new ShoppingList(20,"k"));
			return dummylist;
		
	}
	
	
	public void storList(List temp){
		for(Object s : temp){
			ShoppingList a = (ShoppingList)s;
			System.out.print(a.getItemName());
		}
	}
	
}
