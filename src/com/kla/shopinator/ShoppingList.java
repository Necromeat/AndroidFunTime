package com.kla.shopinator;

import java.util.ArrayList;
import java.util.List;

import datamodels.ShoppingItemModel;
import dbfunctions.Controller;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.os.Build;

public class ShoppingList extends Activity {
	//just quick change
	Controller con;
	Spinner itemAmount;
	ListView listViewHandle;
	
	ArrayList<String> tempArray;
	List<ShoppingItemModel> tempList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_list);
		
		fillSpinner();
		
		con = new Controller();

		Context context = getApplicationContext();
		con.setContext(context);
		
		ShoppingItemModel test = new ShoppingItemModel(10,"apples");
		con.addItemToList("shopping_list",test);
		con.SaveSpecificListToDB("shopping_list");
		
		tempArray = new ArrayList<String>();
		tempList = new ArrayList<ShoppingItemModel>();
		fillList();
	}
	
	public void fillSpinner(){
		try{
			List<Integer> itemX = new ArrayList<Integer>();
			for(int i = 1; i < 30; i++){
				itemX.add(i);
			}
			ArrayAdapter<Integer> spinAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, itemX);
			spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			itemAmount.setAdapter(spinAdapter);
		}
		catch(NullPointerException e){
			System.out.println("Spinner error: "+e.getMessage());
		}
	}
	
	public void fillList(){
		for(ShoppingItemModel i : con.getShoppingList("shopping_list")){
			if(tempList.contains(i)==false){
				tempList.add(i);
				System.out.println("Item added to temp array: "+i.toString());
			}else{
				System.out.println("Temp array already contains "+i.toString());
			}
		}
		for(ShoppingItemModel i : tempList){
			if(tempArray.contains(i.getItemName())==false){
				tempArray.add(i.getItemName());
				System.out.println("Item added to temp shopping list: "+i.getItemName());
			}else{
				System.out.println("Shopping list already contains "+i.getItemName());
			}
		}
		
		listViewHandle = (ListView)findViewById(R.id.shopping_list);
		ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempArray);
		listViewHandle.setAdapter(a);
	}
	
	public void addItemButton(View v){
		System.out.println("Adding new Item");
		ShoppingItemModel temp;
		EditText newItem = (EditText)findViewById(R.id.editText1);
		itemAmount = (Spinner)findViewById(R.id.spinner1);
		String item = newItem.getText().toString();
		//Testing with 5 for now
		int amount = 5;//Integer.parseInt(itemAmount.getSelectedItem().toString());
		temp = new ShoppingItemModel(amount, item);
		con.addItemToList("shopping_list", temp);
		fillList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shopping_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
