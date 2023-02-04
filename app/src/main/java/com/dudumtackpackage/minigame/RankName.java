package com.dudumtackpackage.minigame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RankName extends AppCompatActivity {
Button Save;
EditText Ed;
TextView db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_name);
        Intent cn = getIntent();
        int check_name = cn.getIntExtra("check",0);
        int score_Num = cn.getIntExtra("score_Num",0);
        String same = cn.getStringExtra("same");
        Ed = findViewById(R.id.ed);
        Save = findViewById(R.id.save);
        db = findViewById(R.id.debug);
        String cns = check_name +same;
        db.setText(cns);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("score_data", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor
                String previous_name1 = sharedPreferences.getString("name1","");
                String previous_name2 = sharedPreferences.getString("name2","");
                String previous_name3 = sharedPreferences.getString("name3","");
                if (cns.equals("1t")){
                    editor.putString("name1",previous_name1+", " + Ed.getText().toString()); // key,value 형식으로 저장
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
                    Intent intent = new Intent(RankName.this,Rank.class);
                    startActivity(intent);
                }
                if (cns.equals("1f")){
                    editor.putString("name1",Ed.getText().toString()); // key,value 형식으로 저장
                    editor.putString("high_score1",""+score_Num);
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
                    Intent intent = new Intent(RankName.this,Rank.class);
                    startActivity(intent);
                }
                if (cns.equals("2t")){
                    editor.putString("name2",previous_name2+", "  + Ed.getText().toString()); // key,value 형식으로 저장
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
                    Intent intent = new Intent(RankName.this,Rank.class);
                    startActivity(intent);
                }
                if (cns.equals("2f")){
                    editor.putString("name2",Ed.getText().toString()); // key,value 형식으로 저장
                    editor.putString("high_score2",""+score_Num);
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
                    Intent intent = new Intent(RankName.this,Rank.class);
                    startActivity(intent);
                }
                if (cns.equals("3t")){
                    Toast.makeText(getApplicationContext(), "2점 감점!", Toast.LENGTH_SHORT).show();
                    editor.putString("name3",previous_name3 +", " + Ed.getText().toString()); // key,value 형식으로 저장
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
                    Intent intent = new Intent(RankName.this,Rank.class);
                    startActivity(intent);
                }
                if (cns.equals("3f")){
                    editor.putString("name3",Ed.getText().toString()); // key,value 형식으로 저장
                    editor.putString("high_score3",""+score_Num);
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
                    Intent intent = new Intent(RankName.this,Rank.class);
                    startActivity(intent);
                }


            }
        });

    }
}