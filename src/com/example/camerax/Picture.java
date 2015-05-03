package com.example.camerax;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.example.camera.R;
import com.example.camerax.event.MulitPointTouchListener;

public class Picture extends Activity {

	public static boolean isClicked=false;
	public ImageView picture;
	ZoomControls zoom;
	Bitmap bitmap;
	ImageButton finger_control;
	public AlertDialog.Builder builder;
   Intent intent;
   String picpath;
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.back:
			Intent back=new Intent(Picture.this,MainActivity.class);
			startActivity(back);
		    finish();
			break;
       
		case R.id.delete:
			try {
				File file=new File(picpath);
				picture.setImageBitmap(null);
				if (file.delete()) {
					Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater=new MenuInflater(Picture.this);
		inflater.inflate(R.menu.picture_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	final double minsize = 0.8 * 0.8 * 0.8 * 0.8 * 0.8 * 0.8 * 0.8 * 0.8 * 0.8
			* 0.8;
	private float scaleWidth = 1;
	private float scaleHeight = 1;

	// private int displayWidth;
	// private int displayHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pciture);

		picture = (ImageView) this.findViewById(R.id.picture);
        finger_control=(ImageButton) findViewById(R.id.finger_control);
		zoom = (ZoomControls) this.findViewById(R.id.zoom);

		zoom.setIsZoomInEnabled(true);
		zoom.setIsZoomOutEnabled(true);

		// DisplayMetrics dm = new DisplayMetrics();
		// getWindowManager().getDefaultDisplay().getMetrics(dm);
		// displayWidth = dm.widthPixels;
		// displayHeight = dm.heightPixels - 80;

		 intent = getIntent();
		 picpath = (String) intent.getCharSequenceExtra("picpath");
		bitmap = BitmapFactory.decodeFile(picpath, null);

		picture.setImageBitmap(bitmap);

		zoom.setOnZoomInClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double scale = 1.25;
				scaleWidth = (float) (scaleWidth * scale);
				scaleHeight = (float) (scaleHeight * scale);
				if (scaleWidth >= 1) {
					// 超出大小后的处理
					scaleWidth = (float) 1.0;
					scaleHeight = (float) 1.0;
				}

				Matrix matrix = new Matrix();
				matrix.postScale(scaleWidth, scaleHeight);
				Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), matrix, true);
				picture.setImageBitmap(newBitmap);

			}
		});
		zoom.setOnZoomOutClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double scale = 0.8;
				scaleWidth = (float) (scaleWidth * scale);
				scaleHeight = (float) (scaleHeight * scale);
				if (scaleWidth <= minsize) {
					// 低于大小后的处理
					scaleWidth = (float) minsize;
					scaleHeight = (float) minsize;
				}

				Matrix matrix = new Matrix();
				matrix.postScale(scaleWidth, scaleHeight);
				Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), matrix, true);
				picture.setImageBitmap(newBitmap);

			}
		});
        finger_control.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isClicked) {
					picture.setOnTouchListener(new MulitPointTouchListener());
					finger_control.setBackgroundResource(R.drawable.unlock);
					isClicked=true;
				}
				else {
					finger_control.setBackgroundResource(R.drawable.lock);
					picture.setOnTouchListener(null);
					
					isClicked=false;
				}
			}
		});
	}

}
