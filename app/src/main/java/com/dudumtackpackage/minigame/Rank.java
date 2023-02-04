package com.dudumtackpackage.minigame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Rank extends AppCompatActivity {
TextView Rank_score, Rank_name,Rank_score2,Rank_name2,Rank_name3,Rank_score3;
Button Home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        SharedPreferences sharedPreferences= getSharedPreferences("score_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        String Name = sharedPreferences.getString("name1","");
        String Name2 = sharedPreferences.getString("name2","");
        String Name3 = sharedPreferences.getString("name3","");
       int High_score = sharedPreferences.getInt("high_score1",-1);
        int High_score2 = sharedPreferences.getInt("high_score2",-2);
        int High_score3 = sharedPreferences.getInt("high_score3",-3);
        Rank_score = findViewById(R.id.rank_score);
        Rank_score2 = findViewById(R.id.rank_score2);
        Rank_score3 = findViewById(R.id.rank_score3);
        Rank_name = findViewById(R.id.rank_name);
        Rank_name2 = findViewById(R.id.rank_name2);
        Rank_name3 = findViewById(R.id.rank_name3);

        Home = findViewById(R.id.home);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Rank.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Rank_score.setText(High_score + "점");
        Rank_name.setText("   1등 "+Name+"의 점수");
        Rank_score2.setText(High_score2 + "점");
        Rank_name2.setText("   2등 "+Name2+"의 점수");
        Rank_score3.setText(High_score3 + "점");
        Rank_name3.setText("   3등 "+Name3+"의 점수");
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
