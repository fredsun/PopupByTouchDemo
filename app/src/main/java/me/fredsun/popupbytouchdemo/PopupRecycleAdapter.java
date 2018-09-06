package me.fredsun.popupbytouchdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fred on 2018/9/6.
 */

public class PopupRecycleAdapter extends RecyclerView.Adapter {
    private PopupRecycleHolder.ItemActionListener listener;
    List<String> data = new ArrayList<>();

    public PopupRecycleAdapter(List<String> data, PopupRecycleHolder.ItemActionListener listener) {
        this.data = data;
        this.listener = listener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popup_recycle, parent, false);
        PopupRecycleHolder itemHolder = new PopupRecycleHolder(inflate);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PopupRecycleHolder){
            PopupRecycleHolder itemHolder = (PopupRecycleHolder) holder;
            itemHolder.setData(listener);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
