package com.example.echo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;



public class MainActivity extends ActionBarActivity {

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		
	
		EditText editText = (EditText) findViewById(R.id.name);
	    EditText editText2 = (EditText) findViewById(R.id.location_description);
	    EditText editText3 = (EditText) findViewById(R.id.edit_message);
	    EditText editText4 = (EditText) findViewById(R.id.edit_message2);
	    
	    String message = editText.getText().toString();
	    String message2 = editText2.getText().toString();
	    String message3 = editText3.getText().toString();
	    String message4 = editText4.getText().toString();    
	    
	    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	    // The SharedPreferences editor - must use commit() to submit changes
	    SharedPreferences.Editor editor = preferences.edit();

	    // Edit the saved preferences
	    editor.putString("UserName", message);
	    editor.putString("loc_desc", message2);
	    editor.putString("class_name", message3);
	    editor.putString("class_num", message4);
	    editor.commit();
		    
	    startActivity(intent); 
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    BroadcastReceiver bc = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			
		}
    	
    };
}
