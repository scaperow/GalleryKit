package com.example.gallerykit;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;

public class Utility {
	public static byte[] Bitmap2Bytes(Bitmap bm){   
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();     
	    bm.compress(Bitmap.CompressFormat.PNG, 100, baos);     
	    return baos.toByteArray();   
	   }  

}
