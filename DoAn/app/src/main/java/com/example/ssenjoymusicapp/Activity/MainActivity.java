package com.example.ssenjoymusicapp.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.ssenjoymusicapp.Adapter.MainViewPagerAdapter;
import com.example.ssenjoymusicapp.Fragment.Fragment_CaNhan;
import com.example.ssenjoymusicapp.Fragment.Fragment_TimKiem;
import com.example.ssenjoymusicapp.Fragment.Fragment_TrangChu;
import com.example.ssenjoymusicapp.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(),"Trang Chủ");
        mainViewPagerAdapter.addFragment(new Fragment_TimKiem(),"Tìm Kiếm");
        //mainViewPagerAdapter.addFragment(new Fragment_CaNhan(),"Cá nhân");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
        //tabLayout.getTabAt(2).setIcon(R.drawable.iconcanhan);
    }

    private  void anhXa(){
        tabLayout = findViewById(R.id.myTapLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}