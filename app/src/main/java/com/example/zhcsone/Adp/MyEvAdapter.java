package com.example.zhcsone.Adp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

public class MyEvAdapter<T> extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<T> listGroup;
    private List<List<T>> listChild;
    private int layoutGroupId;
    private int layoutChildId;
    private CallBack<T> callBack;

    public MyEvAdapter(Context context, List<T> listGroup, List<List<T>> listChild, int layoutGroupId, int layoutChildId, CallBack<T> callBack) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listGroup = listGroup;
        this.listChild = listChild;
        this.layoutGroupId = layoutGroupId;
        this.layoutChildId = layoutChildId;
        this.callBack = callBack;
    }

    interface CallBack<T> {
        public void groupItem(GroupViewHolder groupViewHolder, T dataGroup, int groupPosition);

        public void childItem(ChildViewHolder childViewHolder, T dataChild, int groupPosition, int childPosition);
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listChild.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutGroupId, parent, false);
            convertView.setTag(new GroupViewHolder(convertView));
        }
        GroupViewHolder groupViewHolder = (GroupViewHolder) convertView.getTag();
        callBack.groupItem(groupViewHolder, listGroup.get(groupPosition), groupPosition);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutChildId, parent, false);
            convertView.setTag(new ChildViewHolder(convertView));
        }
        ChildViewHolder childViewHolder = (ChildViewHolder) convertView.getTag();
        callBack.childItem(childViewHolder, listChild.get(groupPosition).get(childPosition), groupPosition, childPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public static class GroupViewHolder {
        View groupView;

        public GroupViewHolder(View view) {
            this.groupView = view;
        }

        public Object getGroupControls(int id) {
            return groupView.findViewById(id);
        }
    }

    public static class ChildViewHolder {
        View childView;

        public ChildViewHolder(View view) {
            this.childView = view;
        }

        public Object getChildControls(int id) {
            return childView.findViewById(id);
        }
    }
}
