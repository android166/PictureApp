package com.tom.pictureapp;

import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private int position;
    private Cursor cursor;
    private ImageView image;
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detector = new GestureDetector(this, this);
        position = getIntent().getIntExtra("POS", 0);
        Log.d("POS", position +"");
        CursorLoader loader = new CursorLoader(this,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);
        cursor = loader.loadInBackground();
        cursor.moveToPosition(position);
        image = (ImageView) findViewById(R.id.imageView);
        updateImage();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    private void updateImage() {
        String path = cursor.getString(
                cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        Log.d("PATH", path);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        image.setImageBitmap(bitmap);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("GESTURE", "onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("GESTURE", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("GESTURE", "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("GESTURE", "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("GESTURE", "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("GESTURE", "onFling/"+velocityX+"/"+velocityY);
        float distance = e2.getX()-e1.getX();
        if (distance > 100){
            Log.d("FLING", "to the Right");
            if (!cursor.moveToPrevious()) {
                cursor.moveToLast();
            }
            updateImage();
        }else if (distance<-100){
            Log.d("FLING", "to the Left");
            if (!cursor.moveToNext()) {
                cursor.moveToFirst();
            }
            updateImage();
        }
        return false;
    }
}
