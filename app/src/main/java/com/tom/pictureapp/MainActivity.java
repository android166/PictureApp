package com.tom.pictureapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL = 1;
    private GridView grid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = (GridView) findViewById(R.id.gridView);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL);
        }else{
            readThumbnails();
        }
    }

    private void readThumbnails() {
        ContentResolver resolver = getContentResolver();
        Cursor c = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);
        String[] from = {MediaStore.Images.Thumbnails.DATA, MediaStore.Images.Media.DISPLAY_NAME};
        int[] to = {R.id.thumb_image, R.id.thumb_text};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, R.layout.thumb_item, c, from, to, 0 );
        grid.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_EXTERNAL && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            readThumbnails();
        }
    }
}
