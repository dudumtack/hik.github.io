package com.dudumtackpackage.minigame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int timer_check2_retouch;
    int max_num_value = 6;
    int min_num_value = 1;
    public int score = 0;
    int randomNum = 0;
    ImageButton A, B, C, D, E, F,ADD, Rank_java;
    LinearLayout background_java;
    Button start_java;
    TextView score_java, l_time_java, centertime_java;
    int restart_cheak;
    int lefttime = 16;
    int centerlefttime = 6;
    MediaPlayer mediaPlayer;
    Timer timer, timer2;
    int retouch_check;
    String character_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        A = findViewById(R.id.a);
        B = findViewById(R.id.b);
        C = findViewById(R.id.c);
        D = findViewById(R.id.d);
        E = findViewById(R.id.e);
        F = findViewById(R.id.f);
        Rank_java = findViewById(R.id.rank);
        centertime_java = findViewById(R.id.centertimer);
        Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(A);
        Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(B);
        Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(C);
        Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(D);
        Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(E);
        Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(F);
        start_java = findViewById(R.id.start);
        ADD = findViewById(R.id.add);
        score_java = findViewById(R.id.scorestr);
        l_time_java = findViewById(R.id.l_time);
        l_time_java.setText("남은 시간 :"+lefttime);
        background_java = findViewById(R.id.background);




        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Character.class);
                startActivity(intent);
            }
        });
        Rank_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (restart_cheak != 1 || lefttime  ==0 || lefttime ==16) {
                    Intent intent = new Intent(MainActivity.this, Rank.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void start_click(View view) { //시간 일시정지후 0.4 고정이 아니라 0.4 추가로
        if (Objects.equals(character_path, "amongus")){
            Amongus.Amonguschar();
        }
        if (restart_cheak == 1 && timer_check2_retouch == 0) {

            restart_cheak = 0;
            When_Timer_stop(view);
            start_click(view);
            lefttime = 16;
        }
        if ((lefttime == 0 || lefttime == 16) && timer_check2_retouch == 0) {
            lefttime = 16;
            mediaPlayer = MediaPlayer.create(this, R.raw.nyan_cat);
            mediaPlayer.start();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.pause();
                }
            }, 100);
            Timer_check2(view);
            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    score = 0;
                    score_java.setText("점수 : " + score);
                    randomNum = 0;
                    lefttime = 16;
                    restart_cheak++;
                    retouch_check = 1;
                    start_java.setText("재시작하기");
                    Timer_check(view);
                    centertime_java.setText("");
                    Random_value_generater(view);
                    timer_check2_retouch = 0;

                }
            }, 4000);
        }
    }

    public void Random_value_generater(View view) {
        SharedPreferences sharedPreferences_char = getSharedPreferences("character_data", MODE_PRIVATE);
       character_path = sharedPreferences_char.getString("char",null);
        if (lefttime >= 0 && retouch_check == 1) {
            retouch_check = 0;
            int befor_random = randomNum;
            Random random = new Random();
            randomNum = random.nextInt(max_num_value - min_num_value + 1) + min_num_value;
            if (randomNum == befor_random) {
                randomNum--;
                if (randomNum == 0) {
                    randomNum = 3;
                }
            }
            switch (randomNum) {
                case 1:
                    if (Objects.equals(character_path, "nyacat") | character_path == null){
                        Glide.with(MainActivity.this).asGif().load(R.raw.catgif).into(A);
                    }
                    if (Objects.equals(character_path, "amongus")){
                        Glide.with(MainActivity.this).asGif().load(R.raw.amongus).into(A);
                    }

                    break;

                case 2:
                    if (Objects.equals(character_path, "nyacat") | character_path == null){
                        Glide.with(MainActivity.this).asGif().load(R.raw.catgif).into(B);
                    }
                    if (Objects.equals(character_path, "amongus")){
                        Glide.with(MainActivity.this).asGif().load(R.raw.amongus).into(B);
                    }
                    break;

                case 3:
                    if (Objects.equals(character_path, "nyacat") | character_path == null){
                        Glide.with(MainActivity.this).asGif().load(R.raw.catgif).into(C);
                    }
                    if (Objects.equals(character_path, "amongus")){
                        Glide.with(MainActivity.this).asGif().load(R.raw.amongus).into(C);
                    }
                    break;

                case 4:
                    if (Objects.equals(character_path, "nyacat") | character_path == null){
                        Glide.with(MainActivity.this).asGif().load(R.raw.catgif).into(D);
                    }
                    if (Objects.equals(character_path, "amongus")){
                        Glide.with(MainActivity.this).asGif().load(R.raw.amongus).into(D);
                    }
                    break;

                case 5:
                    if (Objects.equals(character_path, "nyacat") | character_path == null){
                        Glide.with(MainActivity.this).asGif().load(R.raw.catgif).into(E);
                    }
                    if (Objects.equals(character_path, "amongus")){
                        Glide.with(MainActivity.this).asGif().load(R.raw.amongus).into(E);
                    }
                    break;

                case 6:
                    if (Objects.equals(character_path, "nyacat") | character_path == null){
                        Glide.with(MainActivity.this).asGif().load(R.raw.catgif).into(F);
                    }
                    if (Objects.equals(character_path, "amongus")){
                        Glide.with(MainActivity.this).asGif().load(R.raw.amongus).into(F);
                    }
                    break;

            }
        }
    }
    public void clickEvent(ImageButton a){
            retouch_check = 1;
            score++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(a);
                }
            });
            score_java.setText("점수 : " + score);
            Random_value_generater(null);
            mediaPlayer.start();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.pause();
                }
            }, 400);
        }


    public void minus_event(){
        if (lefttime > 0 && lefttime != 16) {
            Toast.makeText(getApplicationContext(), "2점 감점!", Toast.LENGTH_SHORT).show();
            score--;
            score--;
            score_java.setText("점수 : " + score);
        }
    }

    public void a_click(View view) {
        if(randomNum == 1){
            clickEvent(A);
        }else {minus_event();}
    }

    public void b_click(View view) {
        if (randomNum == 2) {
            clickEvent(B);
        }else {minus_event();}
    }
    public void c_click(View view) {
        if (randomNum == 3) {
            clickEvent(C);
        }else {minus_event();}
    }

    public void d_click(View view) {
        if (randomNum == 4) {
            clickEvent(D);
        }else {minus_event();}
    }

    public void e_click(View view) {
        if (randomNum == 5) {
            clickEvent(E);
        }else {minus_event();}
    }

    public void f_click(View view) {
        if (randomNum == 6) {
            clickEvent(F);
        }else {minus_event();}
    }

    public void Timer_check2(View view) {
        timer_check2_retouch = 1;
        centerlefttime = 4;
        timer2 = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        centerlefttime--;
                        centertime_java.setText("" + centerlefttime);
                        if (centerlefttime <= 0) {
                            centertime_java.setText("start");
                            When_Timer_stop(view);
                        }
                    }
                });
            }

        };
        timer2.schedule(timerTask, 0, 1000);
    }

    public void Timer_check(View view) {

        lefttime = 16;
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lefttime--;
                        l_time_java.setText("남은 시간 :" + lefttime);
                        if (lefttime <= 0) {
                            When_Timer_stop(view);

                        }
                    }
                });

            }
        };
        timer.schedule(timerTask, 0, 1000);

// timer 종료

    }

    public void When_Timer_stop(View view) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(A);
                Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(B);
                Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(C);
                Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(D);
                Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(E);
                Glide.with(MainActivity.this).asGif().load(R.raw.nyancat_background).into(F);
                start_java.setText("시작 하기");
            }
        });
        randomNum = 0;
        restart_cheak = 0;
        timer2.cancel();

        if (timer != null) {
            timer.cancel();
        }
        if (lefttime <= 0) {
            SharedPreferences sharedPreferences = getSharedPreferences("score_data", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
            int previous_score = sharedPreferences.getInt("high_score1", -1);
            int previous_score2 = sharedPreferences.getInt("high_score2", -2);
            int previous_score3 = sharedPreferences.getInt("high_score3", -3);
            if (score > previous_score) {
                GoRankName(1,1,score, "f");
            }
            if (score == previous_score){
                GoRankName(1,1,score, "t");
            }
            if (previous_score > score && score > previous_score2) {
             GoRankName(2,2,score,"f");
            }
            if (previous_score > score && score == previous_score2) {
                GoRankName(2,2,score,"t");
            }
            if (previous_score2 > score && score > previous_score3) {
               GoRankName(3,3,score,"f");

            }
            if (previous_score2 > score && score == previous_score3) {
                GoRankName(3,3,score,"t");

            }

            //intent
        }
    }
    public void GoRankName(int a, int b,int score,String same){
        if (lefttime == 0){
        SharedPreferences sharedPreferences = getSharedPreferences("score_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        int previous_score = sharedPreferences.getInt("high_score1", -1);
        String previous_name = sharedPreferences.getString("name1","이름");
        int previous_score2 = sharedPreferences.getInt("high_score2", -2);
        String previous_name2 = sharedPreferences.getString("name2","이름");
        if (b == 1){
            if (previous_score == score){
            }else {
                editor.putInt("high_score2", previous_score);
                editor.putString("name2", previous_name);
                editor.putInt("high_score3", previous_score2);
                editor.putString("name3",previous_name2);
                editor.commit();
            }
        }
        if (b == 2) {
            if (previous_score2 == score){
            }else {
                editor.putInt("high_score3", previous_score2);
                editor.commit();
            }
        }
        Intent intent = new Intent(MainActivity.this, RankName.class);
        intent.putExtra("check",a);
        intent.putExtra("same",same);
        intent.putExtra("score_Num",score);
        startActivity(intent);}
    }
}