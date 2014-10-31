package com.example.echo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String url = preferences.getString("url", "");  
		Log.d("url", url);
		String name = preferences.getString("UserName", "");
		Log.d("name", name);
		String loc = preferences.getString("loc_desc", "");
		Log.d("loc desc", loc);
		String c_name = preferences.getString("class_name", "");
		Log.d("class nane", c_name);
		String c_num = preferences.getString("class_num", "");
		Log.d("class num", c_num);
		
		
		   
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		double longi = 0;
		double lat = 0; 
		if(location != null) {
			longi = location.getLongitude();
			lat = location.getLatitude();
		   }
		
//		TextView cool_name1 = (TextView) findViewById(R.id.cool_name1);
//		TextView classNameView = (TextView) findViewById(R.id.class_name1);
//		TextView classNumView = (TextView) findViewById(R.id.class_num1);
//		TextView locView = (TextView) findViewById(R.id.loc1);
//		TextView urlView = (TextView) findViewById(R.id.url);
//				  
//		cool_name1.setText(name);
//		classNameView.setText(c_name);
//		classNumView.setText(c_num);
//		locView.setText(loc);
//		urlView.setText(url);
		   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_results,
					container, false);
			return rootView;
		}
	}
}
