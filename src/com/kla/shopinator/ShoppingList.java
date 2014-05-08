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
import android.widget.ListView;
import android.os.Build;

public class ShoppingList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_list);
		
		Controller con = new Controller();

		Context context = getApplicationContext();
		con.setContext(context);
		
		ShoppingItemModel test = new ShoppingItemModel(10,"a");
		con.setContext(context);
		con.SaveSpecificListToDB("list1");
		
		ArrayList<String> dummyList = new ArrayList<String>();
		List<ShoppingItemModel> temp = con.getShoppingList("list1");
		
		for(ShoppingItemModel i : temp){
			dummyList.add(i.getItemName()+i.getQuantity());
		}
		
		ListView listViewHandle = (ListView)findViewById(R.id.shopping_list);
		
		ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dummyList);
		
		listViewHandle.setAdapter(a);
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
