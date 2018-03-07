package com.example.gallerykit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap.Config;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Sketchpad extends View {

	Paint pathPen;
	Paint backgroundPen;	
	Bitmap background;
	Canvas canvas;
	Path path;
	float lx;
	float ly;

	public Sketchpad(Context context, Bitmap map) {
		super(context);
		// TODO Auto-generated constructor stub
		background = map;
	
		pathPen = new Paint();
		pathPen.setDither(true);//设置递色  
		pathPen.setColor(0xFFFF0000);
		pathPen.setStyle(Paint.Style.STROKE);
		pathPen.setStrokeJoin(Paint.Join.ROUND);//默认类型是MITER（1.BEVEL 2.MITER 3.ROUND ）  
		pathPen.setStrokeCap(Paint.Cap.ROUND);//默认类型是BUTT（1.BUTT 2.ROUND 3.SQUARE ）
		pathPen.setStrokeWidth(12);
		pathPen.setAntiAlias(true);
		/*
		pathPen = new Paint();//创建画笔渲染对象  
		pathPen.setAntiAlias(true);//设置抗锯齿，让绘画比较平滑  
		pathPen.setColor(0xFFFF0000);//设置画笔的颜色  
		pathPen.setStyle(Paint.Style.STROKE);//画笔的类型有三种（1.FILL 2.FILL_AND_STROKE 3.STROKE ）  
		pathPen.setStrokeJoin(Paint.Join.ROUND);//默认类型是MITER（1.BEVEL 2.MITER 3.ROUND ）  
		pathPen.setStrokeCap(Paint.Cap.ROUND);//默认类型是BUTT（1.BUTT 2.ROUND 3.SQUARE ）  
	    pathPen.setStrokeWidth(12);//设置描边的宽度，如果设置的值为0那么边是一条极细的线  
		*/
		backgroundPen= new Paint();

		canvas = new Canvas(background);
		path = new Path();
	}
	
	public Bitmap getBitmap(){
		Bitmap image = Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(),Config.ARGB_8888);
		canvas.setBitmap(image);
		return image;
	}

	@Override
	protected void onDraw(Canvas canvas) {
			canvas.drawBitmap(background, 0, 0, backgroundPen);
			canvas.drawPath(path,pathPen);  
	}

	private void onTouchDown(float x, float y) {
			path.reset();
			path.moveTo(x, y);
			lx = x;
			ly=y;
	}

	private void onTouchUp(float x, float y) {
	       path.lineTo(lx, ly);  
	        canvas.drawPath(path, pathPen);    
	        path.reset();  
	}

	private void onTouchMove(float x, float y) {
		float mx = Math.abs(x - lx);
		float my = Math.abs(y - ly);
		path.quadTo(mx, my, (x + lx) / 2, (y + ly) / 2);
		lx = x;
		ly = y;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			onTouchDown(event.getX(), event.getY());
			invalidate();
			break;

		case MotionEvent.ACTION_MOVE:
			onTouchMove(event.getX(), event.getY());
			invalidate();
			break;

		case MotionEvent.ACTION_UP:
			onTouchUp(event.getX(), event.getY());
			invalidate();
			break;
		}

		return true;
	}
}
