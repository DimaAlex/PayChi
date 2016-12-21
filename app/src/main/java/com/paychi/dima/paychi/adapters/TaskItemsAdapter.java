package com.paychi.dima.paychi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paychi.dima.paychi.listeners.OnTaskItemClickListener;
import com.paychi.dima.paychi.models.TaskItem;
import com.paychi.dima.paychi.models.TaskItemWrapper;

import java.util.List;

public class TaskItemsAdapter extends BaseAdapter {
    public Context context;
    public List<TaskItemWrapper> tasks;
    private LayoutInflater inflater;
    private OnTaskItemClickListener listener;

    public TaskItemsAdapter(Context context, List<TaskItemWrapper> tasks, OnTaskItemClickListener listener) {
        this.context = context;
        this.tasks = tasks;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public TaskItemWrapper getTask(int position) {
        return (TaskItemWrapper) getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        TaskItem task = getTask(position).getTask();
        fillView(view, task);
        return view;
    }

    private void fillView(View view, final TaskItem task) {
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(task.getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTaskItemClick(task);
            }
        });
    }
}
