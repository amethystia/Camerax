package com.example.camerax.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawCaptureRect extends View {
	  private int mcolorfill;
	     private int mleft, mtop, mwidth, mheight;
	  public DrawCaptureRect(Context context,int left, int top,         int width, int height, int colorfill) {
	   super(context);
	   // TODO Auto-generated constructor stub
	   this.mcolorfill = colorfill;
	   this.mleft = left;
	   this.mtop = top;
	   this.mwidth = width;
	   this.mheight = height;
	  }
	public DrawCaptureRect(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public DrawCaptureRect(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DrawCaptureRect(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// TODO Auto-generated method stub
		   Paint mpaint = new Paint();
		   mpaint.setColor(mcolorfill);
		   mpaint.setStyle(Paint.Style.FILL);
		   mpaint.setStrokeWidth(1.0f);
		   canvas.drawLine(mleft, mtop, mleft+mwidth, mtop, mpaint);
		   canvas.drawLine(mleft+mwidth, mtop, mleft+mwidth, mtop+mheight, mpaint);
		   canvas.drawLine(mleft, mtop, mleft, mtop+mheight, mpaint);
		   canvas.drawLine(mleft, mtop+mheight, mleft+mwidth, mtop+mheight, mpaint);
		   super.onDraw(canvas); 
	}
	 
}
