package com.example.zhcsone.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
        initB(); // 加载Gson
        myPageFragAdp = new MyPageFragAdp(getSupportFragmentManager(), fragmentList.size(), fragmentList);
        viewPager.setAdapter(myPageFragAdp);

    }

    private void initB() {

    }

    private void initA() {
        viewPager = findViewById(R.id.main_vp);
        radioGroup = findViewById(R.id.main_rg);
        fragmentList.add(new MainOneFragment());

    }

    public void actionStart(Context context) {
        Intent intent = new Intent(context, this.getClass());
        context.startActivity(intent);
    }
}