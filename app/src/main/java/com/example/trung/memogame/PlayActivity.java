package com.example.trung.memogame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class PlayActivity extends AppCompatActivity {

    float x1,x2,y1,y2;
    SharedPreferences sharedOption;
    // setting layout variable
    TextView tv_score, tv_time;
    ImageView pos11, pos12, pos13, pos14,
            pos21, pos22, pos23, pos24,
            pos31, pos32, pos33, pos34,
            pos41, pos42, pos43, pos44;
    //array for images
    Integer[] cardsArray = {11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28};

    //actual picture
    int pic11, pic12, pic13, pic14, pic15, pic16, pic17 ,pic18,
            pic21, pic22, pic23, pic24, pic25, pic26, pic27 ,pic28;

    // setting variables
    int firstCard, secondCard;
    int clickFirst, clickSecond;
    int cardNum = 1;
    int round=1;
    int score;
    SharedPreferences sharedResult;
    // countdown timer
    private CountDownTimer timer;
    private long timeLeft = 180000; // 3 min

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        //setting iv id, score id, time id
        settingID();
        // initialize score
        score = 0;
        tv_score.setText("Score: " + String.valueOf(score));
        initGame();
        setTimer();
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

    @Override
    public void finish() {
        super.finish();
        timer.cancel();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();

    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.start();
    }

    private void settingID(){
        tv_score = findViewById(R.id.tv_score);
        tv_time = findViewById(R.id.tv_time);
        pos11 = findViewById(R.id.pos11);
        pos12 = findViewById(R.id.pos12);
        pos13 = findViewById(R.id.pos13);
        pos14 = findViewById(R.id.pos14);
        pos21 = findViewById(R.id.pos21);
        pos22 = findViewById(R.id.pos22);
        pos23 = findViewById(R.id.pos23);
        pos24 = findViewById(R.id.pos24);
        pos31 = findViewById(R.id.pos31);
        pos32 = findViewById(R.id.pos32);
        pos33 = findViewById(R.id.pos33);
        pos34 = findViewById(R.id.pos34);
        pos41 = findViewById(R.id.pos41);
        pos42 = findViewById(R.id.pos42);
        pos43 = findViewById(R.id.pos43);
        pos44 = findViewById(R.id.pos44);

        pos11.setTag("0");
        pos12.setTag("1");
        pos13.setTag("2");
        pos14.setTag("3");
        pos21.setTag("4");
        pos22.setTag("5");
        pos23.setTag("6");
        pos24.setTag("7");
        pos31.setTag("8");
        pos32.setTag("9");
        pos33.setTag("10");
        pos34.setTag("11");
        pos41.setTag("12");
        pos42.setTag("13");
        pos43.setTag("14");
        pos44.setTag("15");

    }

    private void setTimer(){
        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                sharedResult = getSharedPreferences("result", Context.MODE_PRIVATE);
                String t = String.valueOf(millisUntilFinished/1000);
                tv_time.setText("Time: " + t);
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                sharedResult = getSharedPreferences("result", Context.MODE_PRIVATE);
                sharedResult.edit().putInt("score", score).apply();
                sharedResult.edit().putInt("round", round).apply();
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        }.start();
    }

    private void initGame(){
        //load card image
        loadFrontCard();
        //shuffle the card
        Collections.shuffle(Arrays.asList(cardsArray));
    }

    private void loadFrontCard(){
        pic11 = R.drawable.pic11;
        pic12 = R.drawable.pic12;
        pic13 = R.drawable.pic13;
        pic14 = R.drawable.pic14;
        pic15 = R.drawable.pic15;
        pic16 = R.drawable.pic16;
        pic17 = R.drawable.pic17;
        pic18 = R.drawable.pic18;
        pic21 = R.drawable.pic21;
        pic22 = R.drawable.pic22;
        pic23 = R.drawable.pic23;
        pic24 = R.drawable.pic24;
        pic25 = R.drawable.pic25;
        pic26 = R.drawable.pic26;
        pic27 = R.drawable.pic27;
        pic28 = R.drawable.pic28;


    }

    public void handleCardClick(View v){
        int theCard = Integer.parseInt((String) v.getTag());
        ImageView iv = findViewById(v.getId());
        handleCompare(iv, theCard);
    }

    private void handleCompare(ImageView iv, int card){
        switch (cardsArray[card]){
            case 11:
                iv.setImageResource(R.drawable.pic11);
                break;
            case 12:
                iv.setImageResource(R.drawable.pic12);
                break;
            case 13:
                iv.setImageResource(R.drawable.pic13);
                break;
            case 14:
                iv.setImageResource(R.drawable.pic14);
                break;
            case 15:
                iv.setImageResource(R.drawable.pic15);
                break;
            case 16:
                iv.setImageResource(R.drawable.pic16);
                break;
            case 17:
                iv.setImageResource(R.drawable.pic17);
                break;
            case 18:
                iv.setImageResource(R.drawable.pic18);
                break;
            case 21:
                iv.setImageResource(R.drawable.pic21);
                break;
            case 22:
                iv.setImageResource(R.drawable.pic22);
                break;
            case 23:
                iv.setImageResource(R.drawable.pic23);
                break;
            case 24:
                iv.setImageResource(R.drawable.pic24);
                break;
            case 25:
                iv.setImageResource(R.drawable.pic25);
                break;
            case 26:
                iv.setImageResource(R.drawable.pic26);
                break;
            case 27:
                iv.setImageResource(R.drawable.pic27);
                break;
            case 28:
                iv.setImageResource(R.drawable.pic28);
                break;
        }
        switch (cardNum){
            case 1:
                firstCard = cardsArray[card];
                if (firstCard>20){
                    firstCard = firstCard - 10;
                }
                cardNum = 2;
                clickFirst = card;
                iv.setEnabled(false);
                break;
            case 2:
                secondCard = cardsArray[card];
                if (secondCard > 20){
                    secondCard = secondCard - 10;
                }
                cardNum = 1;
                clickSecond = card;

                ivSetEnable(false);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        compare();
                    }
                },1000);
                break;
        }

    }

    private void ivSetEnable(boolean b){
        pos11.setEnabled(b);
        pos12.setEnabled(b);
        pos13.setEnabled(b);
        pos14.setEnabled(b);
        pos21.setEnabled(b);
        pos22.setEnabled(b);
        pos23.setEnabled(b);
        pos24.setEnabled(b);
        pos31.setEnabled(b);
        pos32.setEnabled(b);
        pos33.setEnabled(b);
        pos34.setEnabled(b);
        pos41.setEnabled(b);
        pos42.setEnabled(b);
        pos43.setEnabled(b);
        pos44.setEnabled(b);
    }

    private void compare(){
        // if correct
        if(firstCard == secondCard) {
            switch (clickFirst){
                case 0:
                    pos11.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    pos12.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    pos13.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    pos14.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    pos21.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    pos22.setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    pos23.setVisibility(View.INVISIBLE);
                    break;
                case 7:
                    pos24.setVisibility(View.INVISIBLE);
                    break;
                case 8:
                    pos31.setVisibility(View.INVISIBLE);
                    break;
                case 9:
                    pos32.setVisibility(View.INVISIBLE);
                    break;
                case 10:
                    pos33.setVisibility(View.INVISIBLE);
                    break;
                case 11:
                    pos34.setVisibility(View.INVISIBLE);
                    break;
                case 12:
                    pos41.setVisibility(View.INVISIBLE);
                    break;
                case 13:
                    pos42.setVisibility(View.INVISIBLE);
                    break;
                case 14:
                    pos43.setVisibility(View.INVISIBLE);
                    break;
                case 15:
                    pos44.setVisibility(View.INVISIBLE);
                    break;
            }

            switch (clickSecond){
                case 0:
                    pos11.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    pos12.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    pos13.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    pos14.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    pos21.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    pos22.setVisibility(View.INVISIBLE);
                    break;
                case 6:
                    pos23.setVisibility(View.INVISIBLE);
                    break;
                case 7:
                    pos24.setVisibility(View.INVISIBLE);
                    break;
                case 8:
                    pos31.setVisibility(View.INVISIBLE);
                    break;
                case 9:
                    pos32.setVisibility(View.INVISIBLE);
                    break;
                case 10:
                    pos33.setVisibility(View.INVISIBLE);
                    break;
                case 11:
                    pos34.setVisibility(View.INVISIBLE);
                    break;
                case 12:
                    pos41.setVisibility(View.INVISIBLE);
                    break;
                case 13:
                    pos42.setVisibility(View.INVISIBLE);
                    break;
                case 14:
                    pos43.setVisibility(View.INVISIBLE);
                    break;
                case 15:
                    pos44.setVisibility(View.INVISIBLE);
                    break;
            }
            score = score + 500;
            tv_score.setText("Score: " + String.valueOf(score));
            checkEnd();
        } else {
            setCardBack();
        }
        ivSetEnable(true);
    }

    private void setCardBack(){
        pos11.setImageResource(R.drawable.back);
        pos12.setImageResource(R.drawable.back);
        pos13.setImageResource(R.drawable.back);
        pos14.setImageResource(R.drawable.back);
        pos21.setImageResource(R.drawable.back);
        pos22.setImageResource(R.drawable.back);
        pos23.setImageResource(R.drawable.back);
        pos24.setImageResource(R.drawable.back);
        pos31.setImageResource(R.drawable.back);
        pos32.setImageResource(R.drawable.back);
        pos33.setImageResource(R.drawable.back);
        pos34.setImageResource(R.drawable.back);
        pos41.setImageResource(R.drawable.back);
        pos42.setImageResource(R.drawable.back);
        pos43.setImageResource(R.drawable.back);
        pos44.setImageResource(R.drawable.back);
    }

    private void checkEnd(){
        if (pos11.getVisibility() == View.INVISIBLE &&
                pos12.getVisibility() == View.INVISIBLE &&
                pos13.getVisibility() == View.INVISIBLE &&
                pos14.getVisibility() == View.INVISIBLE &&
                pos21.getVisibility() == View.INVISIBLE &&
                pos22.getVisibility() == View.INVISIBLE &&
                pos23.getVisibility() == View.INVISIBLE &&
                pos24.getVisibility() == View.INVISIBLE &&
                pos31.getVisibility() == View.INVISIBLE &&
                pos32.getVisibility() == View.INVISIBLE &&
                pos33.getVisibility() == View.INVISIBLE &&
                pos34.getVisibility() == View.INVISIBLE &&
                pos41.getVisibility() == View.INVISIBLE &&
                pos42.getVisibility() == View.INVISIBLE &&
                pos43.getVisibility() == View.INVISIBLE &&
                pos44.getVisibility() == View.INVISIBLE) {
            round++;
            createBoard();
            initGame();
        }
    }

    private void createBoard(){
        pos11.setVisibility(View.VISIBLE);
        pos12.setVisibility(View.VISIBLE);
        pos13.setVisibility(View.VISIBLE);
        pos14.setVisibility(View.VISIBLE);
        pos21.setVisibility(View.VISIBLE);
        pos22.setVisibility(View.VISIBLE);
        pos23.setVisibility(View.VISIBLE);
        pos24.setVisibility(View.VISIBLE);
        pos31.setVisibility(View.VISIBLE);
        pos32.setVisibility(View.VISIBLE);
        pos33.setVisibility(View.VISIBLE);
        pos34.setVisibility(View.VISIBLE);
        pos41.setVisibility(View.VISIBLE);
        pos42.setVisibility(View.VISIBLE);
        pos43.setVisibility(View.VISIBLE);
        pos44.setVisibility(View.VISIBLE);
        setCardBack();
    }
}
