package com.kla.shopinator;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import datamodels.GPSTracker;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.provider.SyncStateContract.Constants;

public class FindStore extends Activity {
	private GoogleMap mMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_store);
		
		setUpMap();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_store, menu);
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

	private void setUpMapIfNeeded() {
	    // Do a null check to confirm that we have not already instantiated the map.
	    if (mMap == null) {
	        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	                            .getMap();
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            // The Map is verified. It is now safe to manipulate the map.

	        }
	    }
	}
	
	public void setUpMap(){
		GoogleMap mMap;
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		setUpMapIfNeeded();
		
		GoogleMapOptions options = new GoogleMapOptions();
		
		options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
	    .compassEnabled(true)
	    .rotateGesturesEnabled(false)
	    .tiltGesturesEnabled(false);
		
		mMap.setMyLocationEnabled(true);
		
		
		GPSTracker gps = new GPSTracker(this);
		double latitude = 0;
		double longitude = 0;
		
		// check if GPS enabled     
		if(gps.canGetLocation()){
		    latitude = gps.getLatitude();
		    longitude = gps.getLongitude();
		}else{
		// can't get location
		// GPS or Network is not enabled
		// Ask user to enable GPS/network in settings
		    gps.showSettingsAlert();    
		}
		
		CameraUpdate center=
		        CameraUpdateFactory.newLatLng(new LatLng(latitude,
		        		longitude));
		CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
		
		mMap.moveCamera(center);
	    mMap.animateCamera(zoom);
	}
}
