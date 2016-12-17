package com.paychi.dima.paychi.KuSu.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paychi.dima.paychi.KuSu.models.Dialog;
import com.paychi.dima.paychi.OnTaskListClickListener;
import com.paychi.dima.paychi.R;
import com.paychi.dima.paychi.models.TaskList;
import com.paychi.dima.paychi.models.TaskListWrapper;

import java.util.List;

public class DialogsAdapter extends ArrayAdapter<Dialog> {

    private final Activity context;

    public DialogsAdapter(Activity context, List<Dialog> objects) {
        super(context, R.layout.i_dialog, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.i_dialog, null, true);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.title);
            holder.lastMessage = (TextView) convertView.findViewById(R.id.message);
            holder.count = (TextView) convertView.findViewById(R.id.count);
            holder.many4 = (TextView) convertView.findViewById(R.id.many4);
            holder.twain = convertView.findViewById(R.id.twain);
            holder.many = convertView.findViewById(R.id.many);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.count.setText(""+getItem(position).getNot_readed());
        holder.count.setVisibility(getItem(position).getNot_readed() == 0 ? View.GONE : View.VISIBLE);

        holder.many4.setText(""+getItem(position).getCount());
        holder.name.setText(getItem(position).getName());
        holder.lastMessage.setText(getItem(position).getText());

        if (getItem(position).is_private()){
            holder.twain.setVisibility(View.VISIBLE);
            holder.many.setVisibility(View.GONE);
        }else {
            holder.twain.setVisibility(View.GONE);
            holder.many.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView lastMessage;
        TextView count;
        TextView many4;
        View twain;
        View many;
    }
}
