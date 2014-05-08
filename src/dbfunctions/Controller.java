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
		list.clear();
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


	public void addItemToList(String arg0,ShoppingItemModel arg1){
		list.add(arg1);	
	}

	public List<ShoppingItemModel> getShoppingList(String arg0) {
		loadList(arg0);
		return list;
	}

	
	@SuppressWarnings("resource")
	 private void loadList(String Name){
		list.clear();
		String temp = filehandler.loadList(Name);
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

}
