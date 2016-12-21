package com.paychi.dima.paychi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paychi.dima.paychi.listeners.OnWishItemClickListener;
import com.paychi.dima.paychi.models.WishItem;

import java.util.List;

public class WishItemsAdapter extends BaseAdapter {
    public Context context;
    public List<WishItem> wishs;
    private LayoutInflater inflater;
    private OnWishItemClickListener listener;

    public WishItemsAdapter(Context context, List<WishItem> wishs, OnWishItemClickListener listener) {
        this.context = context;
        this.wishs = wishs;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return wishs.size();
    }

    @Override
    public Object getItem(int position) {
        return wishs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public WishItem getWish(int position) {
        return (WishItem) getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        WishItem wish = getWish(position);
        fillView(view, wish);
        return view;
    }

    private void fillView(View view, final WishItem wish) {
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(wish.getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onWishItemClick(wish);
            }
        });
    }
}
