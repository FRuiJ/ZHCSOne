package com.example.zhcsone.Ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhcsone.Adp.MyLvAdapter;
import com.example.zhcsone.Adp.MyPageAdp;
import com.example.zhcsone.Adp.MyRvAdapter;
import com.example.zhcsone.JsonData.OneEditText1;
import com.example.zhcsone.JsonData.OneNews;
import com.example.zhcsone.JsonData.OneNewsFen;
import com.example.zhcsone.JsonData.OnePager1;
import com.example.zhcsone.JsonData.OneSeverList1;
import com.example.zhcsone.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainOneFragment extends Fragment {

    private static final String TAG = "MainOneFragment";
    int x;
    int y = 0;

    ViewPager viewPager1, viewPager2;
    ImageView hotIv1, hotIv2;
    TextView hotTv1, hotTv2;
    EditText editText1;
    RadioGroup radioGroup;
    RadioButton radioButton;
    LinearLayout linearLayout1, linearLayout2;
    TabLayout tabLayout;

    List<ImageView> imageViewList = new ArrayList<>();
    OkHttpClient okHttpClient = new OkHttpClient();
    List<OneSeverList1.RowsDTO> rowsDTOS;
    List<Bitmap> listIcon = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    radioButton = new RadioButton(getContext());
                    RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(25, 25);
                    layoutParams.setMargins(25, 0, 25, 8);
                    radioButton.setLayoutParams(layoutParams);
                    radioButton.setButtonDrawable(null);
                    radioButton.setBackgroundResource(R.drawable.dain_sele);
                    radioGroup.addView(radioButton);
                    if (imageViewList.size() == onePager1.getRows().size() - 1 && y == 0) {
                        intiViewPager1B();
                        y++;
                    }
                    break;
                case 1:
                    viewPager1.setCurrentItem(x);
                    break;
                case 2:
                    initIcon();
                    break;
                case 3:
                    initLinearLayout1A();
                    break;
                case 4:
                    initNewsDataA();// 加载新闻网络数据
                    break;
                case 5:
                    initNewsView();// 加载新闻控件
                    break;
                case 6:
                    break;
            }
        }
    };


    OnePager1 onePager1;
    OneEditText1 oneEditText1;
    OneSeverList1 oneSeverList1;
    OneNews oneNews;
    OneNewsFen oneNewsFen;
    MainWebAct mainWebAct;
    String IP = "http://172.17.36.212:10002";
    String url0 = "http://172.17.36.212:10002/prod-api/press/press/list?title="; // 获取新闻列表
    String url1 = "http://172.17.36.212:10002/prod-api/api/rotation/list"; // 开屏广告和轮播图片
    String url2 = "http://172.17.36.212:10002/prod-api/api/service/list"; // 图标入口
    String url4 = "http://172.17.36.212:10002/prod-api/press/press/list"; // 新闻列表
    String url5 = "http://172.17.36.212:10002/prod-api/press/category/list"; // 新闻分类

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_one, container, false);
        initA(view);
        intiViewPager1A();
        initEditTextA();
        initLinearLayout1();
        initNewsData();
        return view;
    }

    private void initNewsDataA() {
        Request request1 = new Request.Builder().url(url4).get().build();
        Call call1 = okHttpClient.newCall(request1);
        call1.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                oneNews = new Gson().fromJson(response.body().string(), OneNews.class);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(5);
            }
        });
    }// 加载新闻网络数据

    private void initNewsView() {
        // 加载热点控件
        int cont = 2;
        for (int i = 0; i < oneNews.getRows().size(); i++) {
            if (oneNews.getRows().get(i).getHot().equals("Y") && cont == 2) {
                Glide.with(getContext()).load(IP + oneNews.getRows().get(i).getCover()).into(hotIv1);
                hotTv1.setText(oneNews.getRows().get(i).getTitle());
                int finalI = i;
                hotIv1.setOnClickListener(v -> {
                    mainWebAct = new MainWebAct();
                    mainWebAct.actionStart(getContext(), oneNews.getRows().get(finalI).getContent());
                });
                cont--;
                continue;
            }
            if (oneNews.getRows().get(i).getHot().equals("Y") && cont == 1) {
                Glide.with(getContext()).load(IP + oneNews.getRows().get(i).getCover()).into(hotIv2);
                hotTv2.setText(oneNews.getRows().get(i).getTitle());
                int finalI1 = i;
                hotIv2.setOnClickListener(v -> {
                    mainWebAct = new MainWebAct();
                    mainWebAct.actionStart(getContext(), oneNews.getRows().get(finalI1).getContent());
                    System.out.println(oneNews.getRows().get(finalI1).getContent());
                });
                cont--;
            }
        }


        List<LinearLayout> linearLayoutList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < oneNewsFen.getData().size(); i++) {
            int sort = oneNewsFen.getData().get(i).getId();
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            for (int j = 0; j < oneNews.getRows().size(); j++) {

                int type = Integer.parseInt(oneNews.getRows().get(j).getType());

                if (sort == type) {
                    OneNews.RowsDTO rowsJ = oneNews.getRows().get(j);
                    LayoutInflater layoutInflater = getLayoutInflater();
                    View view = layoutInflater.inflate(R.layout.news_list, null);
                    TextView textView1 = view.findViewById(R.id.news_title);
                    TextView textView2 = view.findViewById(R.id.news_content);
                    TextView textView3 = view.findViewById(R.id.news_good);
                    TextView textView4 = view.findViewById(R.id.news_time);
                    textView1.setText(rowsJ.getTitle());
                    textView2.setText(rowsJ.getContent());
                    textView3.setText("评论数：" + rowsJ.getReadNum());
                    textView4.setText(rowsJ.getPublishDate());
                    ImageView imageView = view.findViewById(R.id.news_iv);
                    Glide.with(getContext()).load(IP + rowsJ.getCover()).into(imageView);
                    int finalJ = j;
                    view.setOnClickListener(v -> {
                        mainWebAct = new MainWebAct();
                        mainWebAct.actionStart(getContext(), oneNews.getRows().get(finalJ).getContent());
                        System.out.println(oneNews.getRows().get(finalJ).getContent());
                    });
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    layoutParams.setMargins(0, 100, 0, 10);
//                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.addView(view);
                }// 新闻分类

            }
            stringList.add(oneNewsFen.getData().get(i).getName());
            linearLayoutList.add(linearLayout);
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewPager2, false);
        MyPageAdp<LinearLayout> myPageAdp = new MyPageAdp<>(linearLayoutList, stringList);
        viewPager2.setAdapter(myPageAdp);
    }// 加载新闻控件

    private void initNewsData() {
        Request request = new Request.Builder().url(url5).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("错误initNewsData");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                oneNewsFen = new Gson().fromJson(response.body().string(), OneNewsFen.class);
                handler.sendEmptyMessage(4);
            }
        });
    }// 加载新闻分类


    private void initIcon() {
        synchronized (this) {
            System.out.println(rowsDTOS.size());
            for (int i = 0; i < rowsDTOS.size(); i++) {
                Request request = new Request.Builder().url(IP + rowsDTOS.get(i).getImgUrl()).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        System.out.println("失败initIcon");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        listIcon.add(bitmap);
                    }
                });
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        handler.sendEmptyMessage(3);
    }// 准备两行图标图片

    private void initLinearLayout1A() {
        for (int i = 0; i < 5; i++) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View ll1 = layoutInflater.inflate(R.layout.sever_icon, null);
            ImageView imageView = ll1.findViewById(R.id.one_icon_iv);
            imageView.setImageBitmap(listIcon.get(i));
            TextView textView = ll1.findViewById(R.id.one_icon_tv);
            textView.setText(rowsDTOS.get(i).getServiceName());
            ll1.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            int finalI = i;
            ll1.setOnClickListener(v -> {
                Toast toast = Toast.makeText(getContext(), "" + rowsDTOS.get(finalI).getId(), Toast.LENGTH_SHORT);
                toast.show();
            });
            linearLayout1.addView(ll1);
        }
        for (int i = 5; i < 10; i++) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View ll1 = layoutInflater.inflate(R.layout.sever_icon, null);
            ImageView imageView = ll1.findViewById(R.id.one_icon_iv);
            imageView.setImageBitmap(listIcon.get(i));
            TextView textView = ll1.findViewById(R.id.one_icon_tv);
            textView.setText(rowsDTOS.get(i).getServiceName());
            ll1.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            int finalI = i;
            ll1.setOnClickListener(v -> {
                Toast toast = Toast.makeText(getContext(), "" + rowsDTOS.get(finalI).getId(), Toast.LENGTH_SHORT);
                toast.show();
            });
            linearLayout2.addView(ll1);
        }
    }// 将图标加载进两行列表里

    public void initLinearLayout1() {
        Request request = new Request.Builder().url(url2).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("失败initLinearLayout1");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                oneSeverList1 = new Gson().fromJson(response.body().string(), OneSeverList1.class);
                rowsDTOS = oneSeverList1.getRows();
                Collections.sort(rowsDTOS);
                handler.sendEmptyMessage(2);
            }
        });
    }// 第一次加载两行图标数据

    private void initEditTextA() {
        editText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    MainNewsList mainNewsList = new MainNewsList();
                    mainNewsList.actionStart(getContext(), url0 + editText1.getText().toString());
                }
                return false;
            }
        });
    }// 搜索框

    private void initA(View view) {
        viewPager1 = view.findViewById(R.id.one_vp);
        editText1 = view.findViewById(R.id.one_et);
        radioGroup = view.findViewById(R.id.one_rg);
        linearLayout1 = view.findViewById(R.id.one_ll1);
        linearLayout2 = view.findViewById(R.id.one_ll2);
        tabLayout = view.findViewById(R.id.one_tab);
        viewPager2 = view.findViewById(R.id.one_vp2);
        hotIv1 = view.findViewById(R.id.one_hot_iv1);
        hotIv2 = view.findViewById(R.id.one_hot_iv2);
        hotTv1 = view.findViewById(R.id.one_hot_tv1);
        hotTv2 = view.findViewById(R.id.one_hot_tv2);
    }

    public void intiViewPager1A() {
        Request request = new Request.Builder().url(url1).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("url1加载失败");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                onePager1 = new Gson().fromJson(response.body().string(), OnePager1.class);
                synchronized (this) {
                    for (int i = 1; i < onePager1.getRows().size(); i++) {// i = 1,过滤开屏广告
                        final String name = Thread.currentThread().getName();
                        String advImg = onePager1.getRows().get(i).getAdvImg();
                        Request requestI = new Request.Builder().url(IP + advImg).build();
                        System.out.println("+{}|?:" + IP + advImg);
                        Call callI = okHttpClient.newCall(requestI);
                        int finalI = i;
                        callI.enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                System.out.println("++++++++==============");
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                synchronized (this) {
                                    InputStream inputStream = Objects.requireNonNull(response.body()).byteStream();
                                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                    ImageView imageViewI = new ImageView(getContext());
                                    imageViewI.setLayoutParams(new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                    imageViewI.setImageBitmap(bitmap);
                                    imageViewI.setOnClickListener(v -> {
                                        // 点击事件
                                        Toast toast = Toast.makeText(getContext(), "点击了" + finalI, Toast.LENGTH_SHORT);
                                        toast.show();
                                    });
                                    imageViewList.add(imageViewI);

                                    handler.sendEmptyMessage(0);

                                }

                            }
                        });

                    }
                }

            }
        });

    }// 加载轮播图片数据

    private void intiViewPager1B() {
        MyPageAdp myPageAdp = new MyPageAdp(imageViewList);
        System.out.println(imageViewList.size() + "===========================");
        viewPager1.setAdapter(myPageAdp);
        radioGroup.check(radioGroup.getChildAt(0).getId());
        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x = viewPager1.getCurrentItem();
                    x++;
                    if (x >= imageViewList.size()) x = 0;
                    handler.sendEmptyMessage(1);
                }
            }
        }).start();
    }// 导入轮播数据
}