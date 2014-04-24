package dbfunctions;

import java.util.ArrayList;
import java.util.List;

import datamodels.ShoppingItemModel;

public class Controller {
	public List<ShoppingItemModel> getShoppingList(){
		ArrayList<ShoppingItemModel> dummylist = new ArrayList<ShoppingItemModel>();
		
		dummylist.add(new ShoppingItemModel(1,"a"));
		dummylist.add(new ShoppingItemModel(2,"b"));
		dummylist.add(new ShoppingItemModel(3,"c"));
		dummylist.add(new ShoppingItemModel(4,"d"));
		dummylist.add(new ShoppingItemModel(5,"e"));
		dummylist.add(new ShoppingItemModel(6,"f"));
		dummylist.add(new ShoppingItemModel(7,"g"));
		dummylist.add(new ShoppingItemModel(20,"k"));
		return dummylist;
	}
}
