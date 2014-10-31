package com.example.echo;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;
import android.preference.PreferenceManager;

public class GetUrlActivity extends ActionBarActivity {
	String full_url = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_url);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		IntentFilter filter = new IntentFilter();
        filter.addAction("com.hackathon.GOT_URL");
		Intent i = registerReceiver(bc, filter);

		
//		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//		String name = preferences.getString("UserName", "");
//		Log.d("name", name);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(DisplayMessageActivity.EXTRA_MESSAGE);
		Log.d("PATH", message);
		
	   MFExample example = new MFExample(this);
		
	   example.startProcess(message);
	   
	   Log.d("url from broad", full_url);
//	   Intent intent2 = new Intent(this, ResultsActivity.class);
//	   startActivity(intent); 
////	   
//	   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//	   String name = preferences.getString("url", "");
//	   Log.d("FINAL url", name);
//	   
	   
//	   LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//	   Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//	   double longi = 0;
//	   double lat = 0; 
//	   if(location != null) {
//		   longi = location.getLongitude();
//		   lat = location.getLatitude();
//	   }
//	   
//	   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//	   String url = preferences.getString("url", "");    
//	   String name = preferences.getString("UserName", "");
////	   Log.d("name", name);
//	   String loc = preferences.getString("loc_desc", "");
//	   String c_name = preferences.getString("class_name", "");
//	   String c_num = preferences.getString("class_num", "");
	   
//	   TextView nameView = (TextView) findViewById(R.id.cool_name1);
//	   TextView classNameView = (TextView) findViewById(R.id.class_name1);
//	   TextView classNumView = (TextView) findViewById(R.id.class_num1);
//	   TextView locView = (TextView) findViewById(R.id.loc1);
//	   TextView urlView = (TextView) findViewById(R.id.url);
	  
//	   nameView.setText(name);
//	   classNameView.setText(c_name);
//	   classNumView.setText(c_num);
//	   locView.setText(loc);
//	   urlView.setText(url);
	   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_url, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_get_url,
					container, false);
			return rootView;
		}
	}
	BroadcastReceiver bc = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			String a = arg1.getStringExtra("url");
			Log.d("arg", a);
			full_url = a;
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(arg0);
			String name = preferences.getString("url", "");
			Log.d("FINAL url", name);
			
			Intent intent = new Intent(arg0, ResultsActivity.class);
			startActivity(intent); 
			
			
		}
    	
    };
}
