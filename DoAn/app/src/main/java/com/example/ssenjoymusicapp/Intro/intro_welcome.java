package com.example.ssenjoymusicapp.Intro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ssenjoymusicapp.Activity.MainActivity;
import com.example.ssenjoymusicapp.R;

public class intro_welcome extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout layoutDot;
    private TextView[]dotstv;
    private int[]layouts;
    private Button btnSkipIntro;
    private Button btnNextIntro;

    private IntroAdapter introAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isFirstTime()){
            startMain();
            finish();
        }

        setStatusBarTransparent();

        setContentView(R.layout.activity_intro_welcome);

        viewPager = findViewById(R.id.ViewPager_Intro);
        layoutDot = findViewById(R.id.dotLayout);
        btnSkipIntro = findViewById(R.id.btn_skip_intro);
        btnNextIntro = findViewById(R.id.btn_next_intro);

        btnSkipIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMain();
            }
        });

        btnNextIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPage =  viewPager.getCurrentItem() + 1;
                if (currentPage < layouts.length) {
                    viewPager.setCurrentItem(currentPage);
                }else{
                    startMain();
                }
            }
        });

        layouts = new int[]{R.layout.intro_slide_eat, R.layout.intro_slide_sleep, R.layout.intro_slide_code, R.layout.intro_slide_enjoy};
        introAdapter = new IntroAdapter(layouts, getApplicationContext());
        viewPager.setAdapter(introAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == layouts.length -1){
                    btnNextIntro.setText("Start");
                    btnSkipIntro.setVisibility(View.GONE);
                }
                else{
                    btnNextIntro.setText("Next");
                    btnSkipIntro.setVisibility(View.VISIBLE);
                }
                setDotStatus(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        setDotStatus(0);
    }

    private boolean isFirstTime(){
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroApp", Context.MODE_PRIVATE);
        return ref.getBoolean("FirstTimeStartFlag", true);
    }

    private void setFirtTimeStartStatus(boolean stt){
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ref.edit();
        editor.putBoolean("FirstTimeStartFlag", stt);
        editor.commit();
    }

    private void setDotStatus(int page){
        layoutDot.removeAllViews();
        dotstv = new TextView[layouts.length];
        for(int i = 0; i < dotstv.length ; i++){
            dotstv[i] = new TextView(this);
            dotstv[i].setText(Html.fromHtml("&#8226;"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutDot.addView(dotstv[i]);
        }
        if (dotstv.length > 0){
            dotstv[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private void startMain(){
        setFirtTimeStartStatus(false);
        startActivity(new Intent(intro_welcome.this, MainActivity.class));
        finish();
    }

    private void setStatusBarTransparent(){
        if (Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}