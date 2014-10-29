package com.example.echo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.mediafire.sdk.clients.ApiClient;
import com.mediafire.sdk.config.Configuration;
import com.mediafire.sdk.config.CredentialsInterface;
import com.mediafire.sdk.uploader.UploadListenerInterface;
import com.mediafire.sdk.uploader.UploadRunnable;
import com.mediafire.sdk.uploader.uploaditem.UploadItem;

public class MFExample {
	public static final String TAG = MFExample.class.getCanonicalName();
	public Context mContext;

	public MFExample(Context context) {
		Log.d("in constructor", "constructor");
		mContext = context;
	}
	public void startProcess(String pathForUrl){
		Log.d("PATHFORURL", pathForUrl);
		Configuration config = createMediaFireConfiguration();
		uploadPicture(config, pathForUrl, new MyUploadListener(mContext));
		Log.d("end start", "process");
		
	}
	public Configuration createMediaFireConfiguration() {
		Log.d("in config", "");
		// create a config object
		Configuration config;
		// create config builder
		Configuration.Builder builder = new Configuration.Builder();
		// customize the configuration
		// not customizing now
		// build the config object
		config = builder.build();
		config.init();
		// set dev credentials
		CredentialsInterface devCredentials = config.getDeveloperCredentials();
		
		Map<String, String> credentials = new LinkedHashMap<String, String>();
		credentials.put("application_id", "43251");
		credentials.put("api_key", "8n63ppfh3wihr33mrwbnopmlr2y3zd0nssv6ox1v");
		devCredentials.setCredentials(credentials);
		
		CredentialsInterface userCredentials = config.getUserCredentials();
		
		Map<String, String> usercredentials = new LinkedHashMap<String, String>();
		usercredentials.put("email", "bailey.bauman@tamu.edu");
		usercredentials.put("password", "password1234");
		
		
		userCredentials.setCredentials(usercredentials);
		
		return config;
	}
	
	public ApiClient createApiClient() {
		Log.d("in API client", "");
		Configuration config = createMediaFireConfiguration();
		// create an ApiClient passing in the configuration
		ApiClient apiClient = new ApiClient(config);
		// return the ApiClient object created
		return apiClient;
	}
	
	public void uploadPicture(Configuration configuration, String path, UploadListenerInterface listenerImplementation) {
		Log.d("in upload pic", "");
		// create upload runnable
		UploadRunnable runnable;
		// create upload item to pass to builder
		UploadItem uploadItem = new UploadItem(path);
		// customize runnable
		UploadRunnable.Builder builder = new UploadRunnable.Builder(configuration, uploadItem);
		// build runnable
		builder.uploadListener(listenerImplementation);
		runnable = builder.build();
		// pass runnable to thread 
		Thread thread = new Thread(runnable);
		// start thread
		thread.start();
	}
	
	public String getUrlForMediaFireQuickKey(String quickKey) {
		Log.d("in get url", "");
		String urlPart1 = "http://www.mediafire.com/conversion_server.php?fd56&quickkey=";
		Log.d("part1", urlPart1);
		String urlPart2 = "&doc_type=i&size_id=6";
		Log.d("part2", urlPart2);
//		http://www.mediafire.com/conversion_server.php?fd56&quickkey=xa9kzd915539iw7&doc_type=i&size_id=6
		String fullUrl = urlPart1 + quickKey + urlPart2;
		Log.d("fullURL", fullUrl);
		Log.d(TAG, "full url being returned: " + fullUrl);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
	    // The SharedPreferences editor - must use commit() to submit changes
	    SharedPreferences.Editor editor = preferences.edit();
	    editor.putString("url", fullUrl);
	    editor.commit();
	    
	    return fullUrl;
	}
	
//	UploadListenerInterface myUploadListener = new UploadListenerInterface() {
//
//		@Override
//		public void onCancelled(UploadItem uploadItem) {
//			// TODO Auto-generated method stub			
//		}
//
//		@Override
//		public void onProgressUpdate(UploadItem uploadItem, int currentChunk, int totalChunks) {
//			// TODO Auto-generated method stub			
//		}
//
//		@Override
//		public void onPolling(UploadItem uploadItem, String message) {
//			// TODO Auto-generated method stub			
//		}
//
//		@Override
//		public void onStarted(UploadItem uploadItem) {
//			// TODO Auto-generated method stub			
//		}
//
//		@Override
//		public void onCompleted(UploadItem uploadItem, final String quickKey) {
//			// TODO Auto-generated method stub
//			Log.d("quickkey", quickKey);
//			Intent intent = new Intent();
//			intent.setAction("com.hackathon.GOT_URL");
//			Bundle bundle = new Bundle();
//			bundle.putString("url", getUrlForMediaFireQuickKey(quickKey));
//			intent.putExtras(bundle);
//			mContext.sendBroadcast(intent);
//		}
//		
//	};
	
	public interface DoStuff {
		public void receiveUrl(String url);
	}
	
}
