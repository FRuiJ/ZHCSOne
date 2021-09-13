package com.example.zhcsone.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhcsone.Adp.MyRvAdapter;
import com.example.zhcsone.AdpData.SubwayListItem;
import com.example.zhcsone.JsonData.SubListData;
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

public class SubwayListAct extends AppCompatActivity {

    RecyclerView recyclerView;
    MyRvAdapter<SubwayListItem> myRvAdapter;
    Toolbar toolbar;
    String addUrl = "建国门";
    String url0 = "http://172.17.36.212:10002/prod-api/api/metro/list?currentName=";// 首页地铁站查询

    OkHttpClient okHttpClient = new OkHttpClient();
    List<SubwayListItem> listItems = new ArrayList<>();
    SubListData subListData;

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

    private void initRv() {
        SubwayListItem subwayListItem;
        for (int i = 0; i < subListData.getData().size(); i++) {
            SubListData.DataDTO dataDTO = subListData.getData().get(i);
            subwayListItem = new SubwayListItem(dataDTO.getLineName(),
                    dataDTO.getNextStep().getName(),
                    dataDTO.getReachTime() + "", dataDTO.getLineId() + "");
            listItems.add(subwayListItem);
        }
        myRvAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway);

        toolbar = findViewById(R.id.subway_list_tb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        recyclerView = findViewById(R.id.subway_list_rv);
        myRvAdapter = new MyRvAdapter<>(getApplication(), listItems, R.layout.subwaylist_item, new MyRvAdapter.CallBack<SubwayListItem>() {
            @Override
            public void item(MyRvAdapter.ViewHolder viewHolder, SubwayListItem data, int position) {
                TextView textView1 = (TextView) viewHolder.getControls(R.id.subway_list_tv1);
                TextView textView2 = (TextView) viewHolder.getControls(R.id.subway_list_tv2);
                TextView textView3 = (TextView) viewHolder.getControls(R.id.subway_list_tv3);
                textView1.setText(data.getTv1());
                textView2.setText("下一站" + data.getTv2());
                textView3.setText(data.getTv3() + "分钟后到达");
                viewHolder.itemView.setOnClickListener(v -> {
                    SubwayContextAct subwayContextAct = new SubwayContextAct();
                    subwayContextAct.actionStart(getApplicationContext(), data.getId(),
                            data.getTv1(), data.getTv2(), data.getTv3());
                });
            }
        });
        recyclerView.setAdapter(myRvAdapter);
        System.out.println(url0 + addUrl);
        Request request = new Request.Builder().url(url0 + addUrl).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("失败Sub");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                subListData = new Gson().fromJson(response.body().string(), SubListData.class);
                System.out.println(subListData.getData() + "043259-2359-234");
                handler.sendEmptyMessage(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 100, 1, "总览图页面");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        if (item.getItemId() == 100) {
            SubwayMapAct subwayMapAct = new SubwayMapAct();
            subwayMapAct.actionStart(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void actionStart(Context context) {
        Intent intent = new Intent(context, this.getClass());
        context.startActivity(intent);
    }
}