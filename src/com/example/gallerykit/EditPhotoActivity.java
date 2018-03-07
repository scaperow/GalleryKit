package com.example.gallerykit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class EditPhotoActivity extends Activity {


	Sketchpad pad;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_edit_photo);
        
        Intent indent = this.getIntent();
        int imageId = indent.getIntExtra("IMAGEID", 0);
        Bitmap  background= BitmapFactory.decodeResource(this.getResources(), imageId);
       // ImageView view = (ImageView)this.findViewById(R.id.imageView1);
       // view.setImageBitmap(background);
         pad = new Sketchpad(EditPhotoActivity.this, background.copy(Config.ARGB_8888, true));
        this.setContentView(pad);
        //this.setContentView(view);
        File f = this.getFilesDir();
        
        ImageView iv = new ImageView();
        iv.setImageURI(uri)
    }

    @Override
    public void onPause(){
    	Bitmap image = pad.getBitmap();
    	FileOutputStream fos;
		try {
			fos = new FileOutputStream("/sdcard/a.jpg");
			image.compress(CompressFormat.JPEG, 100, fos); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_edit_photo, menu);
        return true;
    }
}
