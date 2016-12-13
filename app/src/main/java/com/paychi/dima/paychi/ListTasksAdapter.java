package com.paychi.dima.paychi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paychi.dima.paychi.models.TaskList;
import com.paychi.dima.paychi.models.TaskListWrapper;

import java.util.List;

public class ListTasksAdapter extends BaseAdapter {

    public Context context;
    public List<TaskListWrapper> lists;
    private LayoutInflater inflater;
    private OnTaskListClickListener listener;

    public ListTasksAdapter(Context context, List<TaskListWrapper> lists, OnTaskListClickListener listener) {
        this.context = context;
        this.lists = lists;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public TaskListWrapper getList(int position) {
        return (TaskListWrapper) getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        TaskList list = getList(position).getList();
        fillView(view, list);
        return view;
    }

    private void fillView(View view, final TaskList list) {
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(list.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTaskListClick(list);
            }
        });
    }
}
