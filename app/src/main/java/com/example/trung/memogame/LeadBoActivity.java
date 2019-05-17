package com.example.trung.memogame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeadBoActivity extends AppCompatActivity {


    float x1,x2,y1,y2;
    SharedPreferences sharedOption;
    DatabaseHelper db;
    ListView lv_lb;
    ArrayList<Highscore> listScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_bo);
        // setting ID
        lv_lb = findViewById(R.id.lv_lb);
        // init db
        db = new DatabaseHelper(this);
        // set arraylist
        listScore = new ArrayList<>();
        loadData();
        //set adapter
        LeaderboardAdapter adapter = new LeaderboardAdapter(this, R.layout.leader_board_list_adapter,listScore);
        lv_lb.setAdapter(adapter);
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
                    //swipe right
                    if (x2 > x1) {
                        Intent l = new Intent(this, MenuActivity.class);
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
        } return false;}

    private void loadData(){
        Cursor cursor = db.getData();
        if (cursor.getCount() == 0){
            Toast.makeText(LeadBoActivity.this,
                    "Nothing to show, play the game", Toast.LENGTH_LONG).show();
        }
        while (cursor.moveToNext()){
            String n = cursor.getString(0);
            String s = String.valueOf(cursor.getInt(1));
            String r = String.valueOf(cursor.getInt(2));
            Highscore hs = new Highscore(n,s,r);
            listScore.add(hs);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
