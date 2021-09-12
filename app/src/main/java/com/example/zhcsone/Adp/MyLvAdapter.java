package com.example.zhcsone.Adp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class MyLvAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> list;
    private LayoutInflater layoutInflater;
    private int layoutId;
    public CallBack<T> callBack;

    public MyLvAdapter(Context context, List<T> list, int layoutId, CallBack<T> callBack) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.callBack = callBack;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutId, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        callBack.item(viewHolder, list.get(position), position);
        return convertView;
    }

    public interface CallBack<T> {
        public void item(ViewHolder viewHolder, T data, int position);
    }

    public static class ViewHolder {
        View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        public Object getControls(int id) {
            return view.findViewById(id);
        }
    }
}
