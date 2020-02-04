package com.anurag.grocerydelivery;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private static int timeout=4000;
    private ImageView logo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View decorview = getWindow().getDecorView();
        int uioptions=View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorview.setSystemUiVisibility(uioptions);


        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(SplashScreen.this,Welcome.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

                finish();
            }
        },timeout);
        logo=findViewById(R.id.logo);

        Animation animation= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.myanim);
        logo.startAnimation(animation);


    }
}
