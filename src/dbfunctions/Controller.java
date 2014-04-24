package dbfunctions;

import java.util.ArrayList;
import java.util.List;

import datamodels.ShoppingItemModel;

public class Controller {
	public List<ShoppingItemModel> getShoppingList(){
		ArrayList<ShoppingItemModel> dummylist = new ArrayList<ShoppingItemModel>();
		
		dummylist.add(new ShoppingItemModel(10,"apple"));
		dummylist.add(new ShoppingItemModel(2,"banana"));
		dummylist.add(new ShoppingItemModel(6,"coke"));
		dummylist.add(new ShoppingItemModel(1,"dog food"));
		dummylist.add(new ShoppingItemModel(12,"eggs"));
		dummylist.add(new ShoppingItemModel(1,"frozen peas"));
		dummylist.add(new ShoppingItemModel(1,"garbage bags"));
		dummylist.add(new ShoppingItemModel(1,"hagan daz"));
		return dummylist;
	}
}
