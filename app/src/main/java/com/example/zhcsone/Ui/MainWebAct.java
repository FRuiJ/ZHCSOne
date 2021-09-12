package com.example.zhcsone.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.zhcsone.R;

public class MainWebAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web);
        Toolbar toolbar = findViewById(R.id.main_web_t);
        WebView webView = findViewById(R.id.main_web_w);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Intent intent = getIntent();
        String html = intent.getStringExtra("html");
        webView.loadData(Html.fromHtml(html).toString(), "text/html", "UTF-8");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void actionStart(Context context, String s) {
        Intent intent = new Intent(context, this.getClass());
        intent.putExtra("html", s);
        context.startActivity(intent);
    }
}