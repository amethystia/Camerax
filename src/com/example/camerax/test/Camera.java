package com.example.camerax.test;

import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.example.camerax.MainActivity;
import com.example.camerax.Picture;
public class Camera extends ActivityInstrumentationTestCase2<MainActivity> {

	private static final String TAG = "Camera";
	MainActivity mainActivity;
	
	public Camera() {
		super("com.example.camera",MainActivity.class);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		mainActivity=(MainActivity)getActivity();
	}
//	public void testSDcard() {
//		// TODO Auto-generated method stub
//       if (Environment.getExternalStorageState()!= null) {
//    	   Log.i(TAG, "SD������");
//	}
//	}
	public void testCamera() {
		// TODO Auto-generated method stub
       if ( mainActivity.camera != null) {
		Log.i(TAG, "����Ѿ�����");
	}
	}
	
	

}
