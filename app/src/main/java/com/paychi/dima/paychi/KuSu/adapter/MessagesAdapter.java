package com.paychi.dima.paychi.KuSu.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paychi.dima.paychi.KuSu.models.MessageItem;
import com.paychi.dima.paychi.R;
import com.paychi.dima.paychi.models.User;

import java.util.Calendar;
import java.util.List;

public class MessagesAdapter extends ArrayAdapter<MessageItem> {

    private final Activity context;
    private final User user;

    public MessagesAdapter(Activity context, List<MessageItem> objects, User user) {
        super(context, R.layout.i_dialog, objects);
        this.context = context;
        this.user = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (getItem(position).getUser().getGId() == user.getGId())
            convertView = context.getLayoutInflater().inflate(R.layout.i_message, null, true);
        else
            convertView = context.getLayoutInflater().inflate(R.layout.i_message_my, null, true);

        holder = new ViewHolder();
        holder.name = (TextView) convertView.findViewById(R.id.textUser);
        holder.date = (TextView) convertView.findViewById(R.id.textDate);
        holder.lastMessage = (TextView) convertView.findViewById(R.id.textMessage);
        holder.main = (LinearLayout) convertView.findViewById(R.id.ll_main);

        holder.lastMessage.setText(getItem(position).getMessage().getText());
        holder.name.setText(getItem(position).getUser().getName());
        holder.date.setText(getItem(position).getMessage().getDate());
        if (getItem(position).getMessage().isSystem())
            holder.main.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));

        return convertView;
    }

    public long getLastId() {
        return getItem(getCount()-1).getMessage().getId();
    }

    static class ViewHolder {
        TextView name;
        TextView lastMessage;
        TextView date;

        LinearLayout main;
    }
}
