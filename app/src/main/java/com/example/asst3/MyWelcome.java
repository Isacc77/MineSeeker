package com.example.asst3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is for displaying welcome screen
 *
 * Citation: Animation inspired by  https://www.bilibili.com/video/BV1Rt411e76H?p=41 & https://www.youtube.com/watch?v=goVoYf2qie0&ab_channel=CoderScionMedia
 *
 */



public class MyWelcome extends AppCompatActivity {

    ImageView iv_android_man;
    TextView tv_t, tv_a;
    Animation rotateA, authorA, titleA;
    Timer timer;

    boolean clicked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_welcome);


        iv_android_man=findViewById(R.id.iv_welcome);

        tv_t=findViewById(R.id.tv_welcome);
        tv_a=findViewById(R.id.tv_author);

        rotateA = AnimationUtils.loadAnimation(this,R.anim.rotate);
        authorA = AnimationUtils.loadAnimation(this,R.anim.author);
        titleA = AnimationUtils.loadAnimation(this,R.anim.title_animation);

        iv_android_man.setAnimation(rotateA);
        tv_t.setAnimation(titleA);
        tv_a.setAnimation(authorA);

        skipBtn();
        delay(7000);//delay 7s


    }

    private void skipBtn( ) {

        Button skip=findViewById(R.id.btn_skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked=true;
                Intent intent=new Intent(MyWelcome.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void delay(int time) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                if(clicked==false){
                    Intent intent=new Intent(MyWelcome.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        }, time);
    }







}
