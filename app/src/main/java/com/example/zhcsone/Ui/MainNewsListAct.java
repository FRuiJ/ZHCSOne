package com.example.zhcsone.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhcsone.Adp.MyRvAdapter;
import com.example.zhcsone.JsonData.OneEditText1;
import com.example.zhcsone.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainNewsListAct extends AppCompatActivity {

    String tv1 = "tv1";
    String iv1 = "iv1";
    OneEditText1 oneEditText1;
    List<Map<String, String>> mapList = new ArrayList<>();
    OkHttpClient okHttpClient = new OkHttpClient();
    MyRvAdapter<Map<String, String>> myRvAdapter;

    String IP = "http://172.17.36.212:10002";
    RecyclerView recyclerView;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initRv();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_news_list);
        Toolbar toolbar = findViewById(R.id.news_list_tb);
        recyclerView = findViewById(R.id.news_list_rv);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        myRvAdapter = new MyRvAdapter<>(this, mapList, R.layout.news_list, new MyRvAdapter.CallBack<Map<String, String>>() {
            @Override
            public void item(MyRvAdapter.ViewHolder viewHolder, Map<String, String> data, int position) {
                TextView textView = (TextView) viewHolder.getControls(R.id.news_title);
                textView.setText(data.get(tv1));
                ImageView imageView = (ImageView) viewHolder.getControls(R.id.news_iv);
                Glide.with(getApplicationContext()).load(data.get(iv1)).into(imageView);
            }
        });
        recyclerView.setAdapter(myRvAdapter);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        initHttp(url);
    }

    private void initRv() {
        List<OneEditText1.RowsDTO> rows = oneEditText1.getRows();
        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put(tv1, rows.get(i).getTitle());
            map.put(iv1, IP + rows.get(i).getCover());
            mapList.add(map);
        }
        myRvAdapter.notifyDataSetChanged();
    }

    private void initHttp(String url) {
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("新闻列表失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                oneEditText1 = new Gson().fromJson(response.body().string(), OneEditText1.class);
                handler.sendEmptyMessage(0);
            }
        });
    }// 网络请求

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void actionStart(Context context, String url) {
        Intent intent = new Intent(context, this.getClass());
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}