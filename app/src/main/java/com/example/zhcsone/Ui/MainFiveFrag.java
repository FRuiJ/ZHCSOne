package com.example.zhcsone.Ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhcsone.Adp.MyOkhttp;
import com.example.zhcsone.JsonData.UserMainData;
import com.example.zhcsone.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainFiveFrag extends Fragment {

    UserMainData userMainData;
    TextView textView1, textView2, textView3, textView4, textView5;
    ImageView imageView1;
    String userName = "test01";
    String passWord = "123456";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjVjNzMwMDcyLTZjMjItNDA5ZS05YzJhLWR" +
            "lNzA5MDM1MDU0OSJ9.KhR4ur4a-vHsRideth12b9HGYsYVpY5_rM4LrOtYcVQR0xu2lfSL7Iu-m-fACNXbMHwJEkCYnk5Vvjj7cuY3Vw";
    String ch = "http://172.17.36.212:10002/prod-api";
    String url = " http://172.17.36.212:10002/prod-api/api/common/user/getInfo"; // 查询个人基本信息
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    init();
                    break;
            }
        }
    };

    private void init() {
        System.out.println(ch + userMainData.getUser().getAvatar());
        Glide.with(this).load(ch + userMainData.getUser().getAvatar()).into(imageView1);
        textView1.setText(userMainData.getUser().getNickName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_five, container, false);
        initView(view);
        initOkhttp();

        return view;
    }

    private void initOkhttp() {
        MyOkhttp.getToken(url, token, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String s = response.body().string();
                System.out.println(s);
                userMainData = new Gson().fromJson(s, UserMainData.class);
                handler.sendEmptyMessage(0);
            }
        });

    }


    private void initView(View view) {
        textView1 = view.findViewById(R.id.five_tv1);
        textView2 = view.findViewById(R.id.five_tv2);
        textView3 = view.findViewById(R.id.five_tv3);
        textView4 = view.findViewById(R.id.five_tv4);
        textView5 = view.findViewById(R.id.five_tv5);
        imageView1 = view.findViewById(R.id.five_iv1);
        textView1.setOnClickListener(v -> {

        });
        textView2.setOnClickListener(v -> {
            UserOneAct userOneAct = new UserOneAct();
            userOneAct.actionStart(getContext());
        });
        textView3.setOnClickListener(v -> {

        });
        textView4.setOnClickListener(v -> {

        });
        textView5.setOnClickListener(v -> {

        });
        imageView1.setOnClickListener(v -> {

        });
    }


}