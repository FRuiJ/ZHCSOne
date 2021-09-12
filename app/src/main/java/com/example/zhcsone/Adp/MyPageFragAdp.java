package com.example.zhcsone.Adp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyPageFragAdp extends FragmentPagerAdapter {
    List<Fragment> list;


    public MyPageFragAdp(@NonNull @NotNull FragmentManager fm, int behavior, List<Fragment> list) {
        super(fm, behavior);
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {

    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
