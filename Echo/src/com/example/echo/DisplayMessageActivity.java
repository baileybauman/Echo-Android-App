package com.example.echo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class DisplayMessageActivity extends ActionBarActivity {

	

	String pathForUrl = "";
	public final static String EXTRA_MESSAGE = "com.example.echo.MESSAGE";
	private static final int RESULT_LOAD_IMAGE = 1;
	
	public void displayInfo(View view) {
	    // Do something in response to button
		Intent i = new Intent(
		Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				 
		startActivityForResult(i, RESULT_LOAD_IMAGE);
		
	}
	
	public void nextActivity(View view) {
		Intent intent = new Intent(this, GetUrlActivity.class);
		intent.putExtra(EXTRA_MESSAGE, pathForUrl);
		startActivity(intent); 
		
	}
	
	public String getRealPathFromURI(Uri uri) {
	    String[] projection = { MediaStore.Images.Media.DATA };
	    @SuppressWarnings("deprecation")
	    Cursor cursor = managedQuery(uri, projection, null, null, null);
	    int column_index = cursor
	            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	}
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        try{ 
	        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };
	            
	           // String path = selectedImage.getPath();
	           // Log.d("PATH", path);
	            String actualUrl = getRealPathFromURI(selectedImage);
	            Log.d("actualpath", actualUrl);
	            pathForUrl = actualUrl;
	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();
	 
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String picturePath = cursor.getString(columnIndex);
	            cursor.close();
	             
	            ImageView imageView = (ImageView) findViewById(R.id.imgView);
	            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	         
	            
	           
			 } else {
		         Toast.makeText(this, "You haven't picked Image",
		                 Toast.LENGTH_LONG).show();
		     }
	        }
			  catch (Exception e) {
			     Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
			             .show();
			 }
	     
	    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_display_message,
					container, false);
			return rootView;
		}
	}
}
