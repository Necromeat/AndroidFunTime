package datamodels;

import java.util.ArrayList;
import java.util.List;


public class ShoppingListModel {
	ArrayList<ShoppingItemModel> list = new ArrayList<ShoppingItemModel>();
	private String shoppinglistname;
	public ShoppingListModel(String name) {
		this.shoppinglistname = name; 
	}
	
	public void addItemToList(ShoppingItemModel arg0) {
		list.add(arg0);
	}

	public List<ShoppingItemModel> getShoppingList() {
		// TODO Auto-generated method stub
		return list;
	}

	public String getShoppingListName() {
		// TODO Auto-generated method stub
		return shoppinglistname;
	}

	public void removeItemFromList(ShoppingItemModel arg0) {
		list.remove(arg0);
	}



}
