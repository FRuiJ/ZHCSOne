package com.example.zhcsone.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.zhcsone.R;

public class SubwayContextAct extends AppCompatActivity {

    String url0 = "http://172.17.36.212:10002/prod-api/api/metro/line/";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String url = intent.getStringExtra("id");

        setContentView(R.layout.activity_subway_context);
        Toolbar toolbar = findViewById(R.id.sub_tb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle(intent.getStringExtra("name"));


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void actionStart(Context context, String id, String name) {
        Intent intent = new Intent(context, this.getClass());
        intent.putExtra("id", url0 + id);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }
}