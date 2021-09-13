package com.example.zhcsone.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhcsone.JsonData.SubAllData;
import com.example.zhcsone.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.RealCall;

public class SubwayMapAct extends AppCompatActivity {

    SubAllData subAllData;
    LinearLayout linearLayout;
    int[] setTextColor = new int[]{Color.parseColor("#f52443"), Color.parseColor("#c66f35"),
            Color.parseColor("#eddd9e"), Color.parseColor("#7d7d00"),
            Color.parseColor("#c8e6c6"),};
    String urlTv = "http://172.17.36.212:10002/prod-api/api/metro/line/list";
    String urlIv = "http://172.17.36.212:10002/prod-api/profile/upload/image/2021/05/08/554f2392-1e1c-4449-b95c-327a5f7ec91d.jpeg";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initView();
                    break;
            }
        }
    };

    private void initView() {
        for (int i = 0; i < subAllData.getData().size(); i++) {
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 10, 0, 10);
            textView.setLayoutParams(layoutParams);
            textView.setTextColor(setTextColor[i % setTextColor.length]);
            textView.setText(subAllData.getData().get(i).getLineName());
            linearLayout.addView(textView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway_map);
        ImageView imageView = findViewById(R.id.map_iv);
        Glide.with(this).load(urlIv).into(imageView);
        linearLayout = findViewById(R.id.map_ll);
        initOk();
    }

    private void initOk() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(urlTv).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("失败SubwayMapAct");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                subAllData = new Gson().fromJson(response.body().string(), SubAllData.class);
                handler.sendEmptyMessage(0);
            }
        });
    }

    @Override
    protected void onResume() {
        // 横屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    public void actionStart(Context context) {
        Intent intent = new Intent(context, this.getClass());
        context.startActivity(intent);
    }
}