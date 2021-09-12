package com.example.zhcsone.Adp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRvAdapter<T> extends RecyclerView.Adapter<MyRvAdapter.ViewHolder> {
    private Context context;
    private List<T> list;
    private LayoutInflater layoutInflater;
    private int layoutId;
    private CallBack<T> callBack;

    public MyRvAdapter(Context context, List<T> list, int layoutId, CallBack<T> callBack) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.callBack = callBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MyRvAdapter.ViewHolder holder, int position) {
        callBack.item(holder, list.get(position), position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface CallBack<T> {
         void item(ViewHolder viewHolder, T data, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        public Object getControls(int id) {
            return view.findViewById(id);
        }
    }
}
