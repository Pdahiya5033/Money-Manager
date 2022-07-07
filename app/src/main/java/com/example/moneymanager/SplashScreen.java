package com.example.moneymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);
        getSupportActionBar().hide();
        imageView=(ImageView) findViewById(R.id.splash_screen_img);
        textView=(TextView) findViewById(R.id.splash_screen_text);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_src_animation);
        imageView.startAnimation(animation);
        textView.startAnimation(animation);
        Thread t = new Thread(){
            public void run(){
                try {
                    sleep(2500);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        t.start();
    }
}
