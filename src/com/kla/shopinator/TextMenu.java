package com.kla.shopinator;

import java.util.ArrayList;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.os.Build;

public class TextMenu extends Activity {
	
	Controller con;
	ArrayList<String> allLists;
	ListView listViewHandle;
	Context context;
	Intent textService;
	String listname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_menu);

		con = new Controller();
		context = getApplicationContext();
		con.setContext(context);
		
		textService = new Intent(this, TextService.class);
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
				listname = (String) listViewHandle.getItemAtPosition(position);
				alertbox("Text This List?", "Are you sure you want to choose " + listname+ "?");
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
		         .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener()
		         {
		             @Override
		             public void onClick(DialogInterface dialog, int whichButton)
		             {	
		            	 textService.putExtra("keyName", listname);
		 				startActivity(textService);
		             }
		         })
		      .show();
		   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_menu, menu);
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
