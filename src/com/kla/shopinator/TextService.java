package com.kla.shopinator;

import java.util.ArrayList;
import java.util.List;

import datamodels.ShoppingItemModel;
import dbfunctions.Controller;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.os.Build;
import android.provider.ContactsContract;

public class TextService extends Activity {

	String dummyList = null;
	ListView listContacts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_service);
		
		Controller con = new Controller();
		
		
		List<ShoppingItemModel> temp = con.getShoppingList();
		
		for(ShoppingItemModel i : temp){
			dummyList += i.getItemName()+" x "+i.getQuantity() + "\n";
		}
		
		
		listContacts = (ListView)findViewById(R.id.conactlist);
		  
		  Uri queryUri = ContactsContract.Contacts.CONTENT_URI;

		  String[] projection = new String[] {
		    ContactsContract.Contacts._ID,
		    ContactsContract.Contacts.DISPLAY_NAME};
		  
		  String selection = ContactsContract.Contacts.DISPLAY_NAME + " IS NOT NULL";
		  
		  CursorLoader cursorLoader = new CursorLoader(
		            this, 
		            queryUri, 
		            projection, 
		            selection, 
		            null, 
		            null);
		  
		  Cursor cursor = cursorLoader.loadInBackground();
		  
		  String[] from = {ContactsContract.Contacts.DISPLAY_NAME};
		        int[] to = {android.R.id.text1};
		        
		        ListAdapter adapter = new SimpleCursorAdapter(
		                this, 
		                android.R.layout.simple_list_item_1, 
		                cursor, 
		                from, 
		                to, 
		                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		        listContacts.setAdapter(adapter);
		 }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text, menu);
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
	
	public void sendTextButton(View v){
		SmsManager smsM = SmsManager.getDefault();
		smsM.sendTextMessage("28414527", null, dummyList, null, null);
	}

}
