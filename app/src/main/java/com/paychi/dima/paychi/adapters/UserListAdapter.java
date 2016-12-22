package com.paychi.dima.paychi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.paychi.dima.paychi.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22.12.2016.
 */

public class UserListAdapter extends BaseAdapter {

    private static DefaultCheckListener listener;

    private Context context;
    private LayoutInflater inflater;
    private List<User> users;
    private List<User> markedUsers;

    public UserListAdapter(Context context, List<User> users) {
        listener = new DefaultCheckListener(this);
        this.context = context;
        this.users = users;
        this.markedUsers = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    public User getUser(int position) {
        return (User) getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_selectable_list_item, parent, false);
        }
        fillView(view, position);
        return view;
    }

    private void fillView(View view, int position) {
        User user = getUser(position);
        CheckedTextView ctvUserName = (CheckedTextView) view.findViewById(android.R.id.text1);
        ctvUserName.setText(user.getName());
        ctvUserName.setChecked(false);
        view.setTag(position);
        view.setOnClickListener(listener);
    }

    public List<User> getMarkedUsers() {
        return markedUsers;
    }

    private static class DefaultCheckListener implements View.OnClickListener {

        UserListAdapter adapter;

        public DefaultCheckListener(UserListAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onClick(View view) {
            CheckedTextView ctvUserName = (CheckedTextView) view.findViewById(android.R.id.text1);
            Integer position =  (Integer) view.getTag();
            User user = adapter.getUser(position);
            if(ctvUserName.isChecked()) {
                ctvUserName.setChecked(false);
                adapter.markedUsers.remove(user);
            } else {
                ctvUserName.setChecked(true);
                adapter.markedUsers.add(user);
            }
        }
    }
}
