package com.example.proshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView textview;
    ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textview = (TextView) findViewById(R.id.textView2);
        icon = (ImageView) findViewById(R.id.imageView2);

        icon.setY(-2000);
        icon.animate().translationY(0).scaleYBy(0.5f).setDuration(1500);
        textview.setY(-2000);
        textview.animate().translationY(40).scaleYBy(0.5f).setDuration(1500);

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}