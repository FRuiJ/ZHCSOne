package com.example.zhcsone.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

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

public class UserOneAct extends AppCompatActivity {

    UserMainData userMainData;
    ImageView imageView1;
    EditText editText1, editText2, editText3;
    String url = "http://172.17.36.212:10002/prod-api/api/common/user"; // 修改个人信息
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjVjNzMwMDcyLTZjMjItNDA5ZS05YzJhLWR" +
            "lNzA5MDM1MDU0OSJ9.KhR4ur4a-vHsRideth12b9HGYsYVpY5_rM4LrOtYcVQR0xu2lfSL7Iu-m-fACNXbMHwJEkCYnk5Vvjj7cuY3Vw";
    String ch = "http://172.17.36.212:10002/prod-api";
    String url1 = " http://172.17.36.212:10002/prod-api/api/common/user/getInfo"; // 查询个人基本信息
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initEt();
                    break;
            }
        }
    };

    private void initEt() {
        Glide.with(this).load(ch + userMainData.getUser().getAvatar()).into(imageView1);
        editText1.setText(userMainData.getUser().getNickName());
        if (Integer.parseInt(userMainData.getUser().getSex()) == 0) {
            editText1.setText("男");
        } else editText1.setText("女");
        editText1.setText(userMainData.getUser().getPhonenumber());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_one);
        Toolbar toolbar = findViewById(R.id.user_one_tl);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intiView();
        initHttp();

    }

    private void initHttp() {
        MyOkhttp.getToken(url1, token, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("UserOneAct失败1");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                userMainData = new Gson().fromJson(response.body().string(), UserMainData.class);
                handler.sendEmptyMessage(0);
            }
        });
    }

    private void intiView() {
        imageView1 = findViewById(R.id.iv1);
        editText1 = findViewById(R.id.et1);
        editText2 = findViewById(R.id.et2);
        editText3 = findViewById(R.id.et3);
        imageView1.setOnClickListener(v -> {

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 100, 1, "修改");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        if (item.getItemId() == 100) {

        }
        return super.onOptionsItemSelected(item);
    }

    public void actionStart(Context context) {
        Intent intent = new Intent(context, this.getClass());
        context.startActivity(intent);
    }
}