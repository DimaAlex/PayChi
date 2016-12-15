package com.paychi.dima.paychi.KuSu.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.paychi.dima.paychi.KuSu.models.Dialog;
import com.paychi.dima.paychi.R;
import com.paychi.dima.paychi.models.User;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private final Activity context;

    public UserAdapter(Activity context, List<User> objects) {
        super(context, R.layout.i_user, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.i_user, null, true);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        return convertView;
    }

    static class ViewHolder {
        TextView name;
    }
}
