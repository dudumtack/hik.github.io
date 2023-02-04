package com.example.progamermaker;

import static java.lang.Thread.sleep;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity {

    int Day = 1;
    int money, cpu_up_count, graphic_up_count, tired_physical, tired_mental, mouse_up_count,popular;
    int equip_percent = cpu_up_count*2+graphic_up_count*5+mouse_up_count;
    int skill_percent = 0; //위에처럼 적용 그냥 직접 더하기로
    int day_off_re;
    int tier_5_win = 0;
    int nowtier = 1;
    ImageView Charater, Charater_sleep,Tier;
    AnimationDrawable anim;
    TextView Test, Day_off_text, Day_count,Money_count,Equip,Skill,Mental,Physical,Shop;
    LinearLayout Fade_out;
    Button Day_off;
    Dialog dialog01,dialog02,dialog03,dialog04,dialog05,dialog06, dialog07,event1, event2;
    boolean cha_touch = false;

    boolean cha_sleep = false;
    boolean day_next = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Fade_out = findViewById(R.id.fade_out);
        Day_off = findViewById(R.id.day_off);
        Day_off_text = findViewById(R.id.day_off_text);
        Test = findViewById(R.id.test);
        Charater = findViewById(R.id.character);
        Charater_sleep = findViewById(R.id.character_sleep);
        Day_count = findViewById(R.id.day_count);
        Money_count = findViewById(R.id.money_count);
        Equip = findViewById(R.id.equip);
        Skill = findViewById(R.id.skill);
        Mental = findViewById(R.id.mental_tired);
        Physical = findViewById(R.id.physical_tired);
        Shop = findViewById(R.id.shop);
        Tier = findViewById(R.id.tier);
        next_day_set();
        movement();

        Tier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog07 = new Dialog(PlayActivity.this);       // Dialog 초기화
                dialog07.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
                dialog07.setContentView(R.layout.dialog_tier);
                showDialog_tier();

            }
        });

        Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog06 = new Dialog(PlayActivity.this);       // Dialog 초기화
                dialog06.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
                dialog06.setContentView(R.layout.dialog_shop);
                showDialog_shop();
            }
        });

        Physical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog05 = new Dialog(PlayActivity.this);       // Dialog 초기화
                dialog05.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
                dialog05.setContentView(R.layout.dialog_physical);
                showDialog_physical();
            }
        });

        Mental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog04 = new Dialog(PlayActivity.this);       // Dialog 초기화
                dialog04.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
                dialog04.setContentView(R.layout.dialog_mental);
                showDialog_mental();
            }
        });

        Skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog03 = new Dialog(PlayActivity.this);       // Dialog 초기화
                dialog03.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
                dialog03.setContentView(R.layout.dialog_skill);
                showDialog_skill();
            }
        });

        Equip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog02 = new Dialog(PlayActivity.this);       // Dialog 초기화
                dialog02.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
                dialog02.setContentView(R.layout.dialog_equip);
                showDialog_equip();
            }
        });

        Money_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog01 = new Dialog(PlayActivity.this);       // Dialog 초기화
                dialog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
                dialog01.setContentView(R.layout.dialog_money);
                showDialog_money();
            }
        });

        Day_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFade_out();
                day_next = false;

            }
        });


        Charater.setOnClickListener(new View.OnClickListener() {//캐릭터 클릭시
            @Override
            public void onClick(View view) {
                if(!cha_sleep){
                    cha_touch =true;
                }

            }
        });
    }

    public void setFade_out() {
        if (day_off_re == 0) {
            day_off_re++;
            Day++;
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                int A = 10;
                String color = "000000";
                @Override
                public void run() {
                    // 반복실행할 구문
                    A += 1;
                    if (A < 99){
                        color = "#" + A + "000000";
                        Fade_out.setBackgroundColor(Color.parseColor(color));
                    }
                    if (A == 20){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Charater_sleep.setImageResource(R.drawable.cha_sleep);
                            }
                        });

                    }

                    if (A == 99) {

                        Fade_out.setBackgroundColor(Color.parseColor("#FF000000"));
                    }

                    if (A == 200){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Charater_sleep.setImageResource(0);
                            }
                        });

                        Day_off_text.setText(Day + "일째");
                    }
                    if (A == 350){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Day_off_text.setText("");
                                Fade_out.setBackgroundColor(Color.parseColor("#00000000"));
                                day_off_re--;
                                timer.cancel();
                                next_day_set();
                                day_next = true;
                                Day_off_event();
                            }
                        });

                    }
                }
            };
// timer 실행
            timer.schedule(timerTask, 0, 20);

        }
    }

    public void movement() {
                    int max_random_value = 2;
                    int min_random_value = 1;
                    int max_random_value2 = 500;
                    int min_random_value2 = 100;
                    Random random = new Random();
                    Random random2 = new Random();
                    int randomNum = random.nextInt(max_random_value - min_random_value + 1) + min_random_value; //오른쪽 왼쪽
                    int randomNum2 = random2.nextInt(max_random_value2 - min_random_value2 + 1) + min_random_value2; //이동 시간
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getRealSize(size);
                    if (randomNum == 1) {//오른쪽
                        movement_right(randomNum2, size.x);
                    }
                    if (randomNum == 2) {//왼쪽
                        movement_left(randomNum2, size.x);
                    }
                }
                private void movement_right(int count, int size){
        Charater.setImageResource(R.drawable.frame_anim_cha_right);
        anim = (AnimationDrawable) Charater.getDrawable();
        anim.start();
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        int count2 = count;
                        @Override
                        public void run() {


                            if (count2 <= 0){
                                timer.cancel();
                                movement();
                            }
                            if (Charater.getX() >= size - 250){
                                timer.cancel();
                                movement();
                            }
                            if (cha_touch){
                                timer.cancel();
                                movement_angry();
                            }
                            if (tired_physical == 100){
                                timer.cancel();
                                movement_tired();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Charater.setX(Charater.getX() + 1);
                                }
                            });
                            count2--;

                        }
                    };
                    timer.schedule(timerTask, 0, 20);
    }
    private void movement_left(int count, int size){
        Charater.setImageDrawable(getDrawable(R.drawable.frame_anim_cha_left));
        anim = (AnimationDrawable) Charater.getDrawable();
        anim.start();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int count2 = count;
            @Override
            public void run() {

                if (count2 <= 0){
                    timer.cancel();
                    movement();
                }
                if (Charater.getX() <= 25){
                    timer.cancel();
                    movement();
                }
                if (cha_touch){
                    timer.cancel();
                    movement_angry();
                }
                if (tired_physical == 100){
                    timer.cancel();
                    movement_tired();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Charater.setX(Charater.getX() - 1);
                    }
                });
                count2--;
            }
        };
        timer.schedule(timerTask, 0, 20);
    }
    private void movement_angry(){

        Charater.setImageResource(R.drawable.cha_angry);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                movement();
                cha_touch = false;
            }
        }, 2000);
    }

    private  void movement_tired(){
        Charater.setImageResource(R.drawable.cha_sleep);
        cha_sleep=true;
    }
    public void next_day_set () {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tired_mental = 0;
                tired_physical = 0;
                Day_count.setText("날짜\n  " +Day);
                Money_count.setText(money+"원");
                Physical.setText("육체적 피로도"+ tired_physical);
                if (cha_sleep){
                    cha_sleep=false;
                    movement();
                }
            }
        });

    }
    public void refresh(){
        equip_percent = cpu_up_count*2+graphic_up_count*5+mouse_up_count;
        Physical.setText("육체적 피로도"+ tired_physical);
        Mental.setText("정신적 피로도"+tired_mental);
        Money_count.setText(money+"원");
        Equip.setText("장비 "+ equip_percent +"%");
        Skill.setText("실력 "+ skill_percent+"%");
        if (nowtier == 2) {
            Tier.setImageResource(R.drawable.tier_2);
        }
        if (nowtier == 3) {
            //Tier.setImageResource(R.drawable.tier_3);
        }
        if (nowtier == 4) {
            //Tier.setImageResource(R.drawable.tier_4);
        }
        if (nowtier == 5) {
            //Tier.setImageResource(R.drawable.tier_5);
        }
    }

    public void showDialog_money(){
        if (day_next){
            dialog01.show();
        }
        TextView short_money = dialog01.findViewById(R.id.short_money);
        TextView long_money = dialog01.findViewById(R.id.long_money);
        TextView Money_bj = dialog01.findViewById(R.id.money_bj);
        TextView Money_cost = dialog01.findViewById(R.id.money_cost);
        TextView Money_now_popular = dialog01.findViewById(R.id.money_now_popular);
        Money_cost.setText("+"+Money_bj_function(popular+1)+"원");
        Money_bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tired_mental + 45 <= 100){
                    tired_mental+=45;
                    popular++;
                    money+= Money_bj_function(popular);
                    Money_now_popular.setText("인기("+popular+")");
                    Money_cost.setText("+"+Money_bj_function(popular+1)+"원");
                    refresh();
                }else {
                    Toast.makeText(getApplicationContext(), "더 이상 일 할 수 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        long_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tired_physical == 0){
                    money+= 70000;
                    tired_physical+= 100;
                    refresh();
                } else {
                    Toast.makeText(getApplicationContext(), "더 이상 일 할 수 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        short_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tired_physical + 35 <= 100){
                    money+= 20000;
                    tired_physical+= 35;
                    refresh();
                } else {
                    Toast.makeText(getApplicationContext(), "더 이상 일 할 수 없습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // '아래 아니오 버튼'처럼 일반적인 방법대로 연결하면 재사용에 용이하고,
        // '아래 네 버튼'처럼 바로 연결하면 일회성으로 사용하기 편함.
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        // 아니오 버튼

                //dialog01.dismiss(); // 다이얼로그 닫기
        // 네 버튼
        dialog01.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money+= 10000;
               refresh();
            }
        });
    }

    public void showDialog_equip(){
        if (day_next){
        dialog02.show();} // 다이얼로그 띄우기
        TextView Equip_money = dialog02.findViewById(R.id.equip_money);
        Equip_money.setText("현재 : "+money);
        TextView Equip_cpu_cost_money = dialog02.findViewById(R.id.equip_cpu_cost_money);
        TextView Equip_cpu_up = dialog02.findViewById(R.id.cpu_up);
        TextView Equip_cup_now_enforce = dialog02.findViewById(R.id.equip_cpu_now_enforce);
        TextView Equip_graphic_up = dialog02.findViewById(R.id.graphic_up);
        TextView Equip_graphic_cost_money = dialog02.findViewById(R.id.equip_graphic_cost_money);
        TextView Equip_graphic_now_enforce = dialog02.findViewById(R.id.equip_graphic_now_enforce);
        TextView Equip_mouse_up = dialog02.findViewById(R.id.mouse_up);
        TextView Equip_mouse_cost_money = dialog02.findViewById(R.id.equip_mouse_cost_money);
        TextView Equip_mouse_now_enforce = dialog02.findViewById(R.id.equip_mouse_now_enforce);


        Equip_mouse_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (money >= Equip_mouse_up_function(mouse_up_count)){
                    money-=Equip_mouse_up_function(mouse_up_count);
                    mouse_up_count++;
                    Equip_mouse_cost_money.setText(Equip_mouse_up_function(mouse_up_count)+"원");
                    Equip_mouse_now_enforce.setText(mouse_up_count + "강 ("+mouse_up_count+"%)");
                    Equip_money.setText("현재 : "+money);
                    refresh();
                }else {
                    Toast.makeText(getApplicationContext(),"돈이 부족합니다",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Equip_graphic_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (money >= Equip_graphic_up_function(graphic_up_count)){
                    money-=Equip_graphic_up_function(graphic_up_count);
                    graphic_up_count++;
                    Equip_graphic_cost_money.setText(Equip_graphic_up_function(graphic_up_count)+"원");
                    Equip_graphic_now_enforce.setText(graphic_up_count + "강 ("+graphic_up_count*5+"%)");
                    Equip_money.setText("현재 : "+money);
                    refresh();

                }else {
                    Toast.makeText(getApplicationContext(),"돈이 부족합니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Equip_cpu_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (money >= Equip_cpu_up_function(cpu_up_count)){
                    money-=Equip_cpu_up_function(cpu_up_count);
                    cpu_up_count++;
                    Equip_cpu_cost_money.setText(Equip_cpu_up_function(cpu_up_count)+"원");//https://junyoung-developer.tistory.com/205 플레이스 홀더는 다음에
                    Equip_cup_now_enforce.setText(cpu_up_count + "강 ("+cpu_up_count*2+"%)");
                    Equip_money.setText("현재 : "+money);
                   refresh();
                }else {
                    Toast.makeText(getApplicationContext(),"돈이 부족합니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Equip_cpu_cost_money.setText(Equip_cpu_up_function(cpu_up_count)+"원");
        Equip_cup_now_enforce.setText(cpu_up_count + "강 ("+cpu_up_count*2+"%)");
        Equip_graphic_cost_money.setText(Equip_graphic_up_function(graphic_up_count)+"원");
        Equip_graphic_now_enforce.setText(graphic_up_count + "강 ("+graphic_up_count*2+"%)");
        Equip_mouse_cost_money.setText(Equip_mouse_up_function(mouse_up_count)+"원");
        Equip_mouse_now_enforce.setText(mouse_up_count + "강 ("+mouse_up_count*2+"%)");

    }



    public void showDialog_skill(){
        if (day_next) {
            dialog03.show(); // 다이얼로그 띄우기
            TextView Skill_play = dialog03.findViewById(R.id.skill_play);
            TextView Skill_watch = dialog03.findViewById(R.id.skill_watch);
            TextView Skill_study = dialog03.findViewById(R.id.skill_study);
            TextView Skill_friend = dialog03.findViewById(R.id.skill_friend);
            TextView Skill_now = dialog03.findViewById(R.id.skill_now);
            Skill_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Skill_set(5, 1, 20);
                }
            });
            Skill_watch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Skill_set(6, 2, 35);
                }
            });
            Skill_friend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Skill_set(3, 1, 15);
                }
            });
            Skill_study.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Skill_set(7, 5, 70);
                }
            });
        }
    }

    public void Skill_set(int A, int B, int C) {
        if (tired_mental+C < 100) {
            TextView Skill_now = dialog03.findViewById(R.id.skill_now);
            Random random = new Random();
            int randomnum = random.nextInt(A - B + 1) + B;
            skill_percent += randomnum;
            tired_mental += C;
            refresh();
            Skill_now.setText("현재 실력 " + skill_percent + "%");
        }else {
            Toast.makeText(getApplicationContext(),"정신이 많이 지쳤습니다", Toast.LENGTH_SHORT).show();
        }
    }

    public void showDialog_mental(){
        if (day_next) {
            dialog04.show(); // 다이얼로그 띄우기
        }
    }

    public void showDialog_physical(){
        if (day_next) {
            dialog05.show(); // 다이얼로그 띄우기
        }
    }

    public void showDialog_shop(){
        if (day_next) {
            dialog06.show(); // 다이얼로그 띄우기
        }
    }

    public void showDialog_tier(){
        if (day_next) {
        dialog07.show();
        TextView tier_play_rank = dialog07.findViewById(R.id.play_rank);
        TextView tier_percent = dialog07.findViewById(R.id.tier_percent);
        ImageView tier_im1 = dialog07.findViewById(R.id.tier_im1);
            ImageView tier_im2 = dialog07.findViewById(R.id.tier_im2);
            ImageView tier_im3 = dialog07.findViewById(R.id.tier_im3);
            ImageView tier_im4 = dialog07.findViewById(R.id.tier_im4);
            ImageView tier_im5 = dialog07.findViewById(R.id.tier_im5);
            ImageView tier_tier = dialog07.findViewById(R.id.tier_tier);
            int power = equip_percent+skill_percent-tired_mental;
            tier_percent.setText(power+"%");
            if (nowtier == 2) {
                tier_tier.setImageResource(R.drawable.tier_2);
            }
            if (nowtier == 3) {
                //tier_tier.setImageResource(R.drawable.tier_3);
            }
            if (nowtier == 4) {
                //tier_tier.setImageResource(R.drawable.tier_4);
            }
            if (nowtier == 5) {
                //tier_tier.setImageResource(R.drawable.tier_5);
            }
        tier_play_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tired_mental + 45 <= 100){
                    tired_mental+=45;
                    refresh();
                    int random_max = 100;
                int random_min = 1;
                int power = equip_percent+skill_percent-tired_mental;
                Random random = new Random();
                int randomNum = random.nextInt(random_max - random_min + 1) - random_min;
                if (randomNum <= power) {

                    if (tier_im1.getDrawable() == null) {
                        tier_im1.setImageResource(R.drawable.circle);
                        tier_5_win++;
                    } else if (tier_im2.getDrawable() == null) {
                        tier_im2.setImageResource(R.drawable.circle);
                        tier_5_win++;
                    } else if (tier_im3.getDrawable() == null) {
                        tier_im3.setImageResource(R.drawable.circle);
                        tier_5_win++;
                    } else if (tier_im4.getDrawable() == null) {
                        tier_im4.setImageResource(R.drawable.circle);
                        tier_5_win++;
                    } else if (tier_im5.getDrawable() == null) {
                        tier_im5.setImageResource(R.drawable.circle);
                        tier_5_win++;
                        if (tier_5_win >= 3) {
                            nowtier++;
                            refresh();
                        }
                    }
                }
                if (randomNum > power){
                    if (tier_im1.getDrawable() == null) {
                        tier_im1.setImageResource(R.drawable.x);
                    } else if (tier_im2.getDrawable() == null) {
                        tier_im2.setImageResource(R.drawable.x);
                    } else if (tier_im3.getDrawable() == null) {
                        tier_im3.setImageResource(R.drawable.x);
                    } else if (tier_im4.getDrawable() == null) {
                        tier_im4.setImageResource(R.drawable.x);
                    } else if (tier_im5.getDrawable() == null) {
                        tier_im5.setImageResource(R.drawable.x);
                        if (tier_5_win >= 3) {
                            refresh();
                        }
                    }
                }
            }else {
                    Toast.makeText(getApplicationContext(),"정신이 많이 지쳤습니다",Toast.LENGTH_SHORT).show();
                }
            }
        });


        }
    }


    public int Equip_cpu_up_function(int x){
        int a = 50;
        int b = 350;
        int c = 600;
       return a*x*x + b*x +c;
    }
    public int Equip_graphic_up_function(int x){
        int a = 1500;
        int b = -1500;
        int c = 10000;
        return a*x*x + b*x +c;
    }
    public int Equip_mouse_up_function(int x){
        int a = 250;
        int b = 1250;
        int c = 100;
        return a*x*x + b*x +c;
    }
    public int Money_bj_function(int x){
        return 500 + x * 50;
    }

    public void Day_off_event(){
        int random_max = 100;
        int random_min = 1;
        Random random = new Random();
        int randomNum = random.nextInt(random_max - random_min + 1)+random_min;
        if (randomNum <= 2 || money >= 20000){
            Event_voracity();
        }
        if (randomNum == 3){
            Event_little_toe();
        }
    }

    public void Event_voracity(){
        event1 = new Dialog(PlayActivity.this);       // Dialog 초기화
        event1.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        event1.setContentView(R.layout.dialog_event_voracity);
        event1.show();
        ImageButton event_cancel = event1.findViewById(R.id.event_cancel);
        event_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event1.dismiss();
            }
        });
        tired_physical +=10;
        money-=20000;
        refresh();
    }
    public void Event_little_toe(){
        event2 = new Dialog(PlayActivity.this);
        event2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        event2.setContentView(R.layout.dialog_event_little_toe);
        event2.show();
        ImageButton event_cancel = event2.findViewById(R.id.event_cancel);
        event_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event2.dismiss();
            }
        });
        tired_physical +=5;
        tired_mental +=10;
        refresh();
    }


}