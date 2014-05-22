package com.kla.shopinator;

import java.util.ArrayList;
import java.util.List;

import datamodels.ShoppingItemModel;
import dbfunctions.Controller;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.os.Build;

public class ShoppingList extends Activity {
	Controller con;
	Spinner itemAmount;
	ListView listViewHandle;
	String keyName;
	Context context;
	String itemname;
	
	ArrayList<String> tempArray;
	List<ShoppingItemModel> tempList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_list);
		
		fillSpinner();
		
		con = new Controller();

		context = getApplicationContext();
		con.setContext(context);
		
		
		
		tempArray = new ArrayList<String>();
		tempList = new ArrayList<ShoppingItemModel>();
		Intent myIntent = getIntent();
		keyName = myIntent.getStringExtra("keyName");
		fillList(keyName);
		
		TextView tv = (TextView)findViewById(R.id.list_title);
		tv.setText(keyName.toUpperCase() + " SHOPPING LIST:");
	}
	
	public void fillSpinner() {
		itemAmount = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_numbers, android.R.layout.simple_spinner_item);
		spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		itemAmount.setAdapter(spinAdapter);
		//System.out.println("filling spinner");
	}
	
	public void fillList(String filename){
		tempList.clear();
		tempList = con.getShoppingList(filename);
		tempArray.clear();
		for(ShoppingItemModel i : tempList){
			String amount = ""+i.getQuantity();
			tempArray.add(i.getItemName()+" x "+amount);
		}
		
		listViewHandle = (ListView)findViewById(R.id.shopping_list);
		ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempArray);
		listViewHandle.setAdapter(a);
		
		listViewHandle.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {				
				itemname = (String) listViewHandle.getItemAtPosition(position);
				System.out.println("item to be deleted: "+itemname);
				alertbox("Delete item", "Are you sure you want delete item?");
				return true;
			}
		});
	}
	
	protected void alertbox(String title, String mymessage) {
		   new AlertDialog.Builder(this)
		      .setMessage(mymessage)
		      .setTitle(title)
		      .setCancelable(true)
		      .setNegativeButton("CANCEL",
		         new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton){}
		         })
		         .setPositiveButton("DELETE", new DialogInterface.OnClickListener()
		         {
		             @Override
		             public void onClick(DialogInterface dialog, int whichButton)
		             {	
		            	//delete selected item
		            	con.deleteItem(keyName, itemname);
		            	fillList(keyName);
		 				CharSequence text = "Item deleted!";
						int duration = Toast.LENGTH_LONG; 
						Toast toast = Toast.makeText(context, text, duration); 
						toast.show();
		             }
		         })
		      .show();
	}
	
	public void addItemButton(View v){
		System.out.println("Adding new Item");
		ShoppingItemModel temp;
		EditText newItem = (EditText)findViewById(R.id.new_item);
		itemAmount = (Spinner)findViewById(R.id.spinner1);
		String item = newItem.getText().toString();
		int amount = Integer.parseInt(itemAmount.getSelectedItem().toString());
		temp = new ShoppingItemModel(amount, item);
		boolean isUnique = con.isUnique(item);
		if(item.length()>0 && isUnique){
			con.addItemToList(keyName, temp);
			newItem.setText("");
			fillList(keyName);
		}else{
		if(!isUnique){
			newItem.setText("");
			CharSequence text = "Item already in list!";
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		else {
			newItem.setText("");
			CharSequence text = "Item name not valid!";
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		}
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
