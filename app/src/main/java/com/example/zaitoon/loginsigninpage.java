package com.example.zaitoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class loginsigninpage extends AppCompatActivity {

    public Button button , buttonslide;
    ImageView image3;
    Animation buttonanimationfrombottom1, fromtop;









    public void init() {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginsigninpage.this, Registration.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideleft, R.anim.slideoutright);
            }
        });

        buttonslide = (Button) findViewById(R.id.buttonslide);
        buttonslide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginsigninpage.this, Useraccess.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideright, R.anim.slideoutleft);

            }
        });

    }
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsigninpage);
        View overlay = findViewById(R.id.lsp);
        overlay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
        );








        button=(Button) findViewById(R.id.button);
        buttonanimationfrombottom1= AnimationUtils.loadAnimation(this , R.anim.buttonanimationfrombottom1);
        button.setAnimation(buttonanimationfrombottom1);

        buttonslide=(Button) findViewById(R.id.buttonslide);
        buttonanimationfrombottom1= AnimationUtils.loadAnimation(this , R.anim.buttonanimationfrombottom1);
        buttonslide.setAnimation(buttonanimationfrombottom1);

        image3= (ImageView) findViewById(R.id.image3);
        fromtop = AnimationUtils.loadAnimation(this , R.anim.fromtop);
        image3.setAnimation(fromtop);
        init();
    }


}


