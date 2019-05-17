package com.example.trung.memogame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class SettingActivity extends AppCompatActivity {

    CheckBox cb_swipe,cb_sound;
    SharedPreferences sharedOption;
    float x1,x2,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //set id
        cb_swipe = findViewById(R.id.cb_swipe);
        cb_sound = findViewById(R.id.cb_sound);
        // init shared pref
        sharedOption = getSharedPreferences("Setting", MODE_PRIVATE);

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
                    // swipe down
                    if (y2 < y1){
                        Intent s = new Intent(this, MenuActivity.class);
                        startActivity(s);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    }
                    break;
            }
        } return false;}

    public void handleSettingClick(View v){
        switch(v.getId()){
            case R.id.cb_swipe:
                if (cb_swipe.isChecked()){
                    sharedOption.edit().putBoolean("swipe", true).apply();
                } else {
                    sharedOption.edit().putBoolean("swipe", false).apply();
                }
                break;
            case R.id.cb_sound:
                if (cb_sound.isChecked()){
                    sharedOption.edit().putBoolean("sound", true).apply();
                } else {
                    sharedOption.edit().putBoolean("sound", false).apply();
                }
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
