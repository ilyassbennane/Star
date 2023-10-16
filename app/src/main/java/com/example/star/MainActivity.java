package com.example.star;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);

        logo.animate().rotation(360f).setDuration(4000);

        logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(6000);

        logo.animate().translationYBy(1000f).setDuration(4000);

        logo.animate().alpha(0f).setDuration(10000);
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}