package com.example.zhcsone.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhcsone.JsonData.SubContextData;
import com.example.zhcsone.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SubwayContextAct extends AppCompatActivity {

    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;
    SubContextData subContextData;
    RadioGroup radioGroup;
    Intent intent;
    String url0 = "http://172.17.36.212:10002/prod-api/api/metro/line/";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initTextView();
                    initRg();
                    break;
            }
        }
    };

    private void initRg() {
        List<SubContextData.DataDTO.MetroStepListDTO> metroStepList = subContextData.getData().getMetroStepList();
        RadioButton radioButton;
        for (int i = 0; i < metroStepList.size(); i++) {
            radioButton = new RadioButton(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(20, 0, 20, 0);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setButtonDrawable(R.drawable.dain_sele);
            radioButton.setText(metroStepList.get(i).getName());
            radioGroup.addView(radioButton);
            if (metroStepList.get(i).getName().equals(intent.getStringExtra("next"))) {
                radioGroup.check(radioGroup.getChildAt(i-1).getId());
            }
        }
    }

    private void initTextView() {
        SubContextData.DataDTO data = subContextData.getData();
        System.out.println(data.getFirst());
        int a = Integer.parseInt(intent.getStringExtra("nextTime"));
        int b = a * 2;
        textView1.setText("始：" + data.getFirst());
        textView2.setText("时间：" + data.getStartTime());
        textView3.setText("终：" + data.getEnd());
        textView4.setText("时间" + data.getEndTime());
        textView5.setText("即将到站：" + intent.getStringExtra("next"));
        textView6.setText("剩余时间" + a + "分钟");
        for (int i = 0; i < data.getMetroStepList().size(); i++) {
            if (data.getMetroStepList().get(i).getName().equals(intent.getStringExtra("next"))) {
                textView7.setText("即将到站：" + data.getMetroStepList().get(i + 1).getName());
                break;
            }
        }
        textView8.setText("剩余时间" + b + "分钟");
        textView9.setText("还有" + 1200 * a + "米");
        textView10.setText("还有" + 1200 * b + "米");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway_context);
        initView();
        intent = getIntent();
        Toolbar toolbar = findViewById(R.id.sub_tb);
        toolbar.setTitle(intent.getStringExtra("name"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        requestIni();// 网络请求
    }

    private void initView() {
        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        textView3 = findViewById(R.id.tv3);
        textView4 = findViewById(R.id.tv4);
        textView5 = findViewById(R.id.tv5);
        textView6 = findViewById(R.id.tv6);
        textView7 = findViewById(R.id.tv7);
        textView8 = findViewById(R.id.tv8);
        textView9 = findViewById(R.id.tv9);
        textView10 = findViewById(R.id.tv10);
        radioGroup = findViewById(R.id.rg1);
    }

    private void requestIni() {
        OkHttpClient okHttpClient = new OkHttpClient();
        String newUrl = url0 + intent.getStringExtra("id");
        System.out.println(newUrl);
        Request request = new Request.Builder().url(newUrl).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("SubwayContextAct失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                subContextData = new Gson().fromJson(response.body().string(), SubContextData.class);
                handler.sendEmptyMessage(0);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void actionStart(Context context, String id, String name, String next, String nextTime) {
        Intent intent = new Intent(context, this.getClass());
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("next", next);
        intent.putExtra("nextTime", nextTime);
        context.startActivity(intent);
    }
}