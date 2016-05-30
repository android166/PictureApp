package com.tom.pictureapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        position = getIntent().getIntExtra("POS", 0);
        Log.d("POS", position +"");
        
    }
}
