package com.example.trung.memogame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView tv_result;
    EditText et_name;
    SharedPreferences sharedResult, sharedOption;
    DatabaseHelper db;
    int resultLogScore;
    int resultLogRound;
    String resultLog;
    float x1,x2,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        // setting ID for result
        tv_result = findViewById(R.id.tv_result);
        et_name = findViewById(R.id.et_name);
        //Initialised variable
        sharedResult = getSharedPreferences("result", MODE_PRIVATE);
        db = new DatabaseHelper(this);
        showResult();
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

    public void showResult(){
        // get score and round
        resultLogScore = sharedResult.getInt("score", 0);
        resultLogRound = sharedResult.getInt("round", 0);
        resultLog = "Score: " + Integer.toString(resultLogScore) +
                "\n Round: " + Integer.toString(resultLogRound);
        tv_result.setText(resultLog);
    }

    public void handleResultButton(View v){
        // save score whatever the name is
        // navigate to other activity
        switch(v.getId()){
            case R.id.btn_save:
                String userName = et_name.getText().toString();
                if (userName.equals("") || userName.equals(" ")) {
                    userName = "anonymous";
                }
                boolean isInsert = db.insertData(userName.toUpperCase(),
                        resultLogScore, resultLogRound );
                if (isInsert) {
                    Toast.makeText(ResultActivity.this,
                            "Saved into database successful",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ResultActivity.this,
                            "Something went wrong",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_share:
                break;
            case R.id.btn_replay:
                Intent i = new Intent(this, PlayActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
            case R.id.btn_menu:
                Intent u = new Intent(this, MenuActivity.class);
                startActivity(u);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
