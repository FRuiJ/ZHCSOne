package com.example.zhcsone.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zhcsone.Adp.MyPageAdp;
import com.example.zhcsone.LocalData.StartData;
import com.example.zhcsone.R;

import java.util.ArrayList;
import java.util.List;

public class StartAct extends AppCompatActivity {

    int[] listIv = new int[]{R.drawable.tupian1, R.drawable.tupian2,
            R.drawable.tupian3, R.drawable.tupian4, R.drawable.tupian5};
    MyPageAdp myPageAdp;
    ImageView imageView;
    RadioButton radioButton;
    List<ImageView> imageViewList1 = new ArrayList<>();
    Button button1, button2;
    ViewPager viewPager;
    RadioGroup radioGroup;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initA();
        myPageAdp = new MyPageAdp(imageViewList1);
        viewPager.setAdapter(myPageAdp);
        radioGroup.check(radioGroup.getChildAt(0).getId());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position).getId());
                if (position == imageViewList1.size()-1){
                    linearLayout.setVisibility(View.VISIBLE);
                }else {
                    linearLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        button1.setOnClickListener(v -> {

        });
        button2.setOnClickListener(v -> {
            MainActivity mainActivity = new MainActivity();
            mainActivity.actionStart(this);
            StartData.setBoolean(this, StartData.STA, true);
            finish();
        });
    }

    private void initA() {
        button1 = findViewById(R.id.start_btn1);
        button2 = findViewById(R.id.start_btn2);
        viewPager = findViewById(R.id.start_vp);
        radioGroup = findViewById(R.id.start_rg);
        linearLayout = findViewById(R.id.start_ll);
        for (int i = 0; i < listIv.length; i++) {
            imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(listIv[i]);
            imageViewList1.add(imageView);

            radioButton = new RadioButton(this);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(25, 25);
            layoutParams.setMargins(25,0,25,8);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setButtonDrawable(null);
            radioButton.setBackgroundResource(R.drawable.dain_sele);
            radioGroup.addView(radioButton);
        }
    }

    public void actionStart(Context context) {
        Intent intent = new Intent(context, this.getClass());
        context.startActivity(intent);
    }
}