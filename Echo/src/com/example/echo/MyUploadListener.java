package com.example.echo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mediafire.sdk.uploader.UploadListenerInterface;
import com.mediafire.sdk.uploader.uploaditem.UploadItem;

public class MyUploadListener implements UploadListenerInterface {

	private Context mContext;

	public MyUploadListener (Context context){
		mContext = context;
	}

	@Override
	public void onCancelled(UploadItem uploadItem) {
		// TODO Auto-generated method stub			
	}

	@Override
	public void onProgressUpdate(UploadItem uploadItem, int currentChunk, int totalChunks) {
		// TODO Auto-generated method stub			
	}

	@Override
	public void onPolling(UploadItem uploadItem, String message) {
		// TODO Auto-generated method stub			
	}

	@Override
	public void onStarted(UploadItem uploadItem) {
		// TODO Auto-generated method 		
	}

	@Override
	public void onCompleted(UploadItem uploadItem, final String quickKey) {
		// TODO Auto-generated method stub
		Log.d("quickkey", quickKey);
		Intent intent = new Intent();
		intent.setAction("com.hackathon.GOT_URL");
		Bundle bundle = new Bundle();
		bundle.putString("url", new MFExample(mContext).getUrlForMediaFireQuickKey(quickKey));
		Log.d("url", new MFExample(mContext).getUrlForMediaFireQuickKey(quickKey));
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
}
