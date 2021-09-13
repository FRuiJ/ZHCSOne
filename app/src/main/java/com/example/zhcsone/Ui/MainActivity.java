package com.example.zhcsone.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zhcsone.Adp.MyPageFragAdp;
import com.example.zhcsone.JsonData.OnePager1;
import com.example.zhcsone.LocalData.StartData;
import com.example.zhcsone.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    static boolean mark = false;
    ViewPager viewPager;
    RadioGroup radioGroup;
    MyPageFragAdp myPageFragAdp;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mark = StartData.getBoolean(this, StartData.STA);
        // 当第一次启动，进入引导
        if (!mark) {
            StartAct startAct = new StartAct();
            startAct.actionStart(MainActivity.this);
            finish();
        }


        initA();

        myPageFragAdp = new MyPageFragAdp(getSupportFragmentManager(), fragmentList.size(), fragmentList);
        viewPager.setAdapter(myPageFragAdp);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                    if (radioButton.isChecked()) {
                        viewPager.setCurrentItem(i);
                    }
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initA() {
        viewPager = findViewById(R.id.main_vp);
        radioGroup = findViewById(R.id.main_rg);
        radioGroup.check(radioGroup.getChildAt(0).getId());

        fragmentList.add(new MainOneFragment());
        fragmentList.add(new MainFiveFrag());
        fragmentList.add(new MainFiveFrag());
        fragmentList.add(new MainFiveFrag());
        fragmentList.add(new MainFiveFrag());

    }

    public void actionStart(Context context) {
        Intent intent = new Intent(context, this.getClass());
        context.startActivity(intent);
    }
}