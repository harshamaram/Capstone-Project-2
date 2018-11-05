package com.falcon.balav.eatmonster.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public  class FoodCut {

    public static Bitmap EatFood(Bitmap srcBitmap, int State) {
        switch (State) {
            case 0:
                return cutRightTop (srcBitmap);
            case 1:
                return cutLeftTop (srcBitmap);
            case 2:
                return cutLeftBottom (srcBitmap);
            case 3:
                return cutRightBottom (srcBitmap);
        }
        return srcBitmap;
    }

    private static Bitmap cutRightTop(Bitmap srcBitmap) {
        return cutArcImage (srcBitmap, 0F, 270F);
    }

    private static Bitmap cutLeftTop(Bitmap srcBitmap) {
        return cutArcImage (srcBitmap, 0F, 180F);
    }

    private static Bitmap cutLeftBottom(Bitmap srcBitmap) {
        return cutArcImage (srcBitmap, 90F, 90F);
    }

    private static Bitmap cutRightBottom(Bitmap srcBitmap) {
        return cutArcImage (srcBitmap, 0F, 0F);
    }


    private static Bitmap cutArcImage(Bitmap bitmap, float startAngle, float sweepAngle) {
        Log.v ("getArcBitmap", "Bitmap-W:" + bitmap.getWidth () + ",H:" + bitmap.getHeight ());
        
        int halfWidth = bitmap.getWidth () / 2;
        int halfheight = bitmap.getHeight () / 2;     
        float radius = Math.sqrt(halfWidth*halfWidth + halfheight*halfheight);
        
        // create new cavas which fits original image and contains more space for the oval
        Bitmap output = Bitmap.createBitmap (bitmap.getWidth (), bitmap.getHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas (output);
        canvas.drawARGB (0, 0, 0, 0);
        
        final Paint paint = new Paint();
        paint.setAntiAlias (true);
        
        // did not understand about this variable
        final Rect rect = new Rect (0, 0, bitmap.getWidth (), bitmap.getHeight ());
                
        final RectF oval = new RectF ();
        oval.set (halfWidth - radius, halfheight - radius, halfWidth + radius, halfheight + radius);
        
        canvas.drawArc (oval, startAngle, sweepAngle, true, paint);     
        paint.setXfermode (new PorterDuffXfermode (PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap (bitmap, rect, rect, paint);
        return output;
    }
    
}
