package com.example.trung.memogame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    SharedPreferences sharedOption;
    float x1,x2,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //set id for background sound;
        sharedOption = getSharedPreferences("sound", MODE_PRIVATE);
        if (sharedOption.getBoolean("sound", true)){
            stopService(new Intent(this, MediaService.class));
            startService(new Intent(this, MediaService.class));
        } else {
            stopService(new Intent(this, MediaService.class));
        }


    }

    public boolean onTouchEvent(MotionEvent event) {
        if (sharedOption.getBoolean("swipe", true)){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    x1=event.getX();
                    y1=event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    x2=event.getX();
                    y2=event.getY();
                    //swipe left
                    if (x1 > x2){
                        Intent i = new Intent(this, PlayActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }
                    //swipe right
                    if (x2 > x1) {
                        Intent l = new Intent(this, LeadBoActivity.class);
                        startActivity(l);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    }
                    // swipe up
                    if (y1 < y2){
                        Intent s = new Intent(this, SettingActivity.class);
                        startActivity(s);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    }
                    break;
            }
        } return false;

    }


    public void handle_menu_option(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                Intent i = new Intent(this, PlayActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                break;
            case R.id.btn_setting:
                Intent s = new Intent(this, SettingActivity.class);
                startActivity(s);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
            case R.id.btn_lb:
                Intent l = new Intent(this, LeadBoActivity.class);
                startActivity(l);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
    }
}
