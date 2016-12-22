package com.paychi.dima.paychi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paychi.dima.paychi.R;
import com.paychi.dima.paychi.listeners.OnTaskListClickListener;
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
            view = inflater.inflate(R.layout.item_list_tasks, parent, false);
        TaskListWrapper listWrapper = getList(position);
        fillView(view, listWrapper);
        return view;
    }

    private void fillView(View view, final TaskListWrapper listWrapper) {
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(listWrapper.getList().getName());

        TextView tvInProgress = (TextView) view.findViewById(R.id.tv_in_progress);
        tvInProgress.setText(String.format("В процессе - %d", listWrapper.getInProgress()));

        TextView tvDone = (TextView) view.findViewById(R.id.tv_done);
        tvDone.setText(String.format("Выполненных - %d", listWrapper.getDone()));

        TextView tvPraised = (TextView) view.findViewById(R.id.tv_praised);
        tvPraised.setText(String.format("Похваленных - %d", listWrapper.getPraised()));

        TextView tvTotal = (TextView) view.findViewById(R.id.tv_total);
        tvTotal.setText(String.format("Всего - %d", listWrapper.getTotal()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTaskListClick(listWrapper);
            }
        });
    }
}
