package com.kla.shopinator;

import java.util.List;

import datamodels.ShoppingItemModel;
import dbfunctions.Controller;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;

public class TextService extends Activity {

	String dummyList = "";
	String number = "";
	ListView listContacts;
	CursorLoader cursorLoader;
	Cursor cursor;
	String keyName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_service);
		
		Controller con = new Controller();

		Context context = getApplicationContext();
		con.setContext(context);
		
		//ShoppingItemModel test = new ShoppingItemModel(10,"a");
		//con.addItemToList("shopping_list",test);
		//con.SaveSpecificListToDB("shopping_list");
		
		Intent myIntent = getIntent();
		keyName = myIntent.getStringExtra("keyName");
		List<ShoppingItemModel> temp = con.getShoppingList(keyName);
		
		for(ShoppingItemModel i : temp){
			dummyList += i.getItemName()+" x "+i.getQuantity() + "\n";
		}
		
		listContacts = (ListView)findViewById(R.id.conactlist);
		listContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				number = cursor.getString(cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER));
				String name = cursor.getString(cursor.getColumnIndex(CommonDataKinds.Phone.DISPLAY_NAME));
				 
				
				alertbox("Send Text", "Are you sure you want to text "+ name);
				
			}
		});
		  
		  Uri queryUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

		  
		  String[] projection = new String[] {
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

	
	public void sendTextButton(View v){
		EditText tempNum = (EditText)findViewById(R.id.new_number);
		number = tempNum.getText().toString();
		if(!number.isEmpty()&&number.length()>=8){
			alertbox("Send Text", "Are you sure you want to text "+ number);
			tempNum.setText("");
		}else{
			Context context = getApplicationContext(); 
			CharSequence text = "Number not valid!";
			int duration = Toast.LENGTH_LONG; 
			Toast toast = Toast.makeText(context, text, duration); 
			toast.show();
		}
	}
	
	
	protected void alertbox(String title, String mymessage)
	   {
	   new AlertDialog.Builder(this)
	      .setMessage(mymessage)
	      .setTitle(title)
	      .setCancelable(true)
	      .setNegativeButton("CANCEL",
	         new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int whichButton){}
	         })
	         .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener()
	         {
	             @Override
	             public void onClick(DialogInterface dialog, int whichButton)
	             {
	                // SHOULD NOW WORK
	            	SmsManager smsM = SmsManager.getDefault();
	 				smsM.sendTextMessage(number, null, keyName+ " Shopping List:\n" +dummyList, null, null);
	 				
	 				Context context = getApplicationContext();
	 				CharSequence text = "Text Sent";
					int duration = Toast.LENGTH_LONG; 
					Toast toast = Toast.makeText(context, text, duration); 
					toast.show();
	             }
	         })
	      .show();
	   }
	

}
