package com.dudumtackpackage.minigame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Amongus extends AppCompatActivity {
    ImageButton A, B, C, D, E, F;
    ImageView Report;
    private MainActivity tackscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("character_data",MODE_PRIVATE);
        String character = sharedPreferences.getString("char","");
        A = findViewById(R.id.a);
        B = findViewById(R.id.b);
        C = findViewById(R.id.c);
        D = findViewById(R.id.d);
        E = findViewById(R.id.e);
        F = findViewById(R.id.f);
        Report = findViewById(R.id.report);
        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tackscore = (MainActivity)getApplicationContext();
                tackscore.score++;
            }
        });

    }
    public void a_click(View view) {

    }
   static void Amonguschar(){
       int max_num_value = 10;
       int min_num_value = 1;
       Random random = new Random();
       int randomNum = random.nextInt(max_num_value - min_num_value + 1) + min_num_value;

    }

}
