package com.kla.shopinator;

import dbfunctions.Controller;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainMenu extends Activity {
	
	Intent viewShoppingLists;
	Intent textMenu;
	Intent findStore;
	Intent scanProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		viewShoppingLists = new Intent(this, ViewShoppingLists.class);//ShoppingList.class);
		textMenu = new Intent(this, TextMenu.class);
		findStore = new Intent(this, FindStore.class);
		scanProduct = new Intent(this, ScanProduct.class);

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
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
	
	public void shoppingListButton(View v){
		startActivity(viewShoppingLists);
	}
	public void textServiceButton(View v){
		startActivity(textMenu);
	}
	public void findStoreButton(View v){
		startActivity(findStore);
	}
	public void scanProductButton(View v){
		startActivity(scanProduct);
	}

}
