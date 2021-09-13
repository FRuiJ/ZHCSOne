package com.example.zhcsone.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.zhcsone.Adp.MyOkhttp;
import com.example.zhcsone.JsonData.UserMainData;
import com.example.zhcsone.R;

public class UserOneAct extends AppCompatActivity {

    UserMainData userMainData;
    ImageView imageView1;
    EditText editText1, editText2, editText3;
    String url = "http://172.17.36.212:10002/prod-api/api/common/user"; // 修改个人信息
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjVjNzMwMDcyLTZjMjItNDA5ZS05YzJhLWR" +
            "lNzA5MDM1MDU0OSJ9.KhR4ur4a-vHsRideth12b9HGYsYVpY5_rM4LrOtYcVQR0xu2lfSL7Iu-m-fACNXbMHwJEkCYnk5Vvjj7cuY3Vw";
    String ch = "http://172.17.36.212:10002/prod-api";
    String url1 = " http://172.17.36.212:10002/prod-api/api/common/user/getInfo"; // 查询个人基本信息

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
        MyOkhttp myOkhttp = new MyOkhttp();
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