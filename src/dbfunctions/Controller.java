package dbfunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import datamodels.ShoppingItemModel;
import android.content.Context;

public class Controller {
	private ArrayList<ShoppingItemModel> list = new ArrayList<ShoppingItemModel>();
	private FileHandler filehandler;
	
		
	public void setContext(Context m){
		filehandler = new FileHandler(m);
	}


	public void SaveSpecificListToDB(String arg0) {
		try {
			filehandler.savelist(arg0, list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveNewListToLists(String listName) {
		try {
			filehandler.saveListNames(listName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void addItemToList(String arg0,ShoppingItemModel arg1){
		loadList(arg0); 	// added by Kris 15/5
		list.add(arg1);
		if(list.contains(arg1)){
			System.out.println("Item added (in controller).");
			System.out.println("Current list:");
			for(ShoppingItemModel s : list){
				System.out.println(s.getItemName());
			}
		}
		SaveSpecificListToDB(arg0);		// added by Kris 15/5
	}

	public List<ShoppingItemModel> getShoppingList(String arg0) {
		loadList(arg0);
		return list;
	}
	
	/*
	 * method added by Kris 15/5
	 * Needs to be fixed:
	 * 1 Shouldn't be public!
	 * 2 Deletes all files I think - should only delete a specific shopping list
	 */
	public void deleteList(String fileName){
		try {
			filehandler.deleteList(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	 private void loadList(String name){
		list.clear();
		String temp = filehandler.loadList(name);
		Scanner scan = new Scanner(temp).useDelimiter(";");
	
		Scanner scanner = scan;
		while(scanner.hasNext()){
			String temper = scanner.next();
			int i = scanner.nextInt();
			list.add(new ShoppingItemModel(temper,i));
			
		}
		scan.close();
		scanner.close();
	}
	
	public ArrayList<String> getLists(){
		ArrayList<String> tempList = new ArrayList<String>();
		String temp = filehandler.loadList("FileForStoringListName");
		Scanner scan = new Scanner(temp).useDelimiter(",");
		while(scan.hasNext()){
			tempList.add(scan.next());
		}
		return tempList;
	}


	public void deleteItem(String keyName, String itemname) {
		for(ShoppingItemModel s : list){
			if(itemname.contains(s.getItemName())){
				list.remove(s);
			}
		}
		try {
			filehandler.savelist(keyName, list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isUnique(String item) {
		for(ShoppingItemModel s : list){
			if(s.getItemName().toLowerCase().equals(item.toLowerCase())){
				return false;
			}
		}
		return true;
	}


	public boolean isUniqueList(String fileName) {
		return filehandler.isUniqueList(fileName);
	}


	

}
