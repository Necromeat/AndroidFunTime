package com.kla.shopinator;

import java.util.ArrayList;

import dbfunctions.Controller;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class ViewShoppingLists extends Activity {

	Controller con;
	ArrayList<String> allLists;
	ListView listViewHandle;
	Context context;
	Intent shoppingList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_shopping_lists);
		
		con = new Controller();
		context = getApplicationContext();
		con.setContext(context);
		
		shoppingList = new Intent(this, ShoppingList.class);
		fillAllShoppingLists();
	}
	
	public void fillAllShoppingLists(){
		allLists = con.getLists();
		listViewHandle = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allLists);
		listViewHandle.setAdapter(a);
		
		
		listViewHandle.setOnItemClickListener(new OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//option to delete or view
				
				//
				String listname = (String) listViewHandle.getItemAtPosition(position);
				System.out.println("selected list item: "+listname);
				shoppingList.putExtra("keyName", listname);
				startActivity(shoppingList);
			}
		});
	}
	
	public void makeNewListButton(View v){
		EditText tempText = (EditText)findViewById(R.id.new_listname);
		System.out.println("new list name: "+tempText.getText().toString());
		String temp = tempText.getText().toString();
		
		if(temp.length()>0){
			allLists.add(temp);
			con.saveNewListToLists(temp);
			con.SaveSpecificListToDB(temp);
			fillAllShoppingLists();
		}
		else{
			CharSequence text = "List name not valid!";
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_shopping_lists, menu);
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
