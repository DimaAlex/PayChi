package com.paychi.dima.paychi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paychi.dima.paychi.listeners.OnWishListClickListener;
import com.paychi.dima.paychi.models.WishList;
import com.paychi.dima.paychi.models.WishListWrapper;

import java.util.List;

public class WishListAdapter extends BaseAdapter{
    public Context context;
    public List<WishListWrapper> lists;
    private LayoutInflater inflater;
    private OnWishListClickListener listener;

    public WishListAdapter(Context context, List<WishListWrapper> lists, OnWishListClickListener listener) {
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

    public WishListWrapper getList(int position) {
        return (WishListWrapper) getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        WishList list = getList(position).getList();
        fillView(view, list);
        return view;
    }

    private void fillView(View view, final WishList list) {
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(list.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onWishListClick(list);
            }
        });
    }
}
