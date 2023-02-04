package com.dudumtackpackage.minigame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Character extends AppCompatActivity {

    ImageView Amongus,Nyacat;
    TextView Explane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        SharedPreferences sharedPreferences = getSharedPreferences("character_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent intent = new Intent(Character.this,MainActivity.class);
        Nyacat = findViewById(R.id.nyacat);
        Explane = findViewById(R.id.explane);
        Glide.with(Character.this).asGif().load(R.raw.catgif).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(Nyacat);
        Amongus = findViewById(R.id.amongus);
        Glide.with(Character.this).asGif().load(R.raw.amongus).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(Amongus);

        Amongus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("char","amongus");
                editor.commit();
                startActivity(intent);
            }
        });
        Nyacat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("char","nyacat");
                editor.commit();
                startActivity(intent);
            }
        });

        Nyacat.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Explane.setText("[냥켓]\n가장 흔한 냥켓이다.\n능력 : 처음 시작시간 1초 추가");

                return true;
            }
        });
        Amongus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Explane.setText("[어몽어스]\n동료를 죽였을 수도 안죽였을 수도 있다.\n능력 : 플레이중 일정 확률로 신고버튼이 생긴다 신고 버튼을 누르면 동료를 불러 점수를 얻는다");

                return true;
            }
        });

    }
}
