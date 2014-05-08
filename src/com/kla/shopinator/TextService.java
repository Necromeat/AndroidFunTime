package com.kla.shopinator;

import java.util.ArrayList;
import java.util.List;

import datamodels.ShoppingItemModel;
import dbfunctions.Controller;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Data;

public class TextService extends Activity {

	String dummyList = "";
	ListView listContacts;
	CursorLoader cursorLoader;
	Cursor cursor; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_service);
		
		Controller con = new Controller();

		Context context = getApplicationContext();
		con.setContext(context);
		
		ShoppingItemModel test = new ShoppingItemModel(10,"a");
		con.addItemToList("shopping_list",test);
		con.SaveSpecificListToDB("shopping_list");
		
		List<ShoppingItemModel> temp = con.getShoppingList("shopping_list");
		
		for(ShoppingItemModel i : temp){
			dummyList += i.getItemName()+" x "+i.getQuantity() + "\n";
		}
		
		
		listContacts = (ListView)findViewById(R.id.conactlist);
		listContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				String number = cursor.getString(cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER));
				
				Context context = getApplicationContext(); 
				CharSequence text = "texting to"+number;
				int duration = Toast.LENGTH_LONG; 
				Toast toast = Toast.makeText(context, text, duration); 
				toast.show();
				
				SmsManager smsM = SmsManager.getDefault();
				smsM.sendTextMessage(number, null, dummyList, null, null);
				
			}
		});
		  
		  //Uri queryUri = ContactsContract.Contacts.CONTENT_URI;
		  Uri queryUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

		  
		  String[] projection = new String[] {
		    //ContactsContract.Contacts._ID,
		    //ContactsContract.Contacts.DISPLAY_NAME
		    ContactsContract.CommonDataKinds.Phone._ID,
		    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
		    ContactsContract.CommonDataKinds.Phone.NUMBER
		    };
		  
		  String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " IS NOT NULL";
		  
		  cursorLoader = new CursorLoader(
		            this, 
		            queryUri, 
		            projection, 
		            selection, 
		            null, 
		            null);
		  
		  cursor = cursorLoader.loadInBackground();
		  
		  
		  String[] from = {(String)ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,(String)ContactsContract.CommonDataKinds.Phone.NUMBER};
		  
		        int[] to = {android.R.id.text1,android.R.id.text2};
		        
		        ListAdapter adapter = new SimpleCursorAdapter(
		                this, 
		                android.R.layout.simple_list_item_2, 
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


	
	
	//public void sendTextButton(View v){
	//	SmsManager smsM = SmsManager.getDefault();
	//	smsM.sendTextMessage("28414527", null, dummyList, null, null);
	//}
	
	
	
	

}
