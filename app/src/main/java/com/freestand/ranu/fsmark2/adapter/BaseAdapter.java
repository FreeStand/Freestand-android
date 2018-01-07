package com.freestand.ranu.fsmark2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 4/1/18.
 */


public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<T> items;

    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent);

    public abstract void onBindData(RecyclerView.ViewHolder holder, T val);

    public BaseAdapter(Context context, ArrayList<T> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = setViewHolder(parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindData(holder,items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems( ArrayList<T> savedCardItemz){
        items = savedCardItemz;
        this.notifyDataSetChanged();
    }

    public T getItem(int position){
        return items.get(position);
    }

    //its use

    /* adapter = new BaseAdapter<DataModel>(context,modelList) {
        @Override
        public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
            final View view =            LayoutInflater.from(context).inflate(R.layout.item_view_holder, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(context, view);
            return viewHolder;
        }

        @Override
        public void onBindData(RecyclerView.ViewHolder holder1, DataModel val) {
                DataModel userModel = val;

                ItemViewHolder holder = (ItemViewHolder)holder1;
                holder.name.setText(userModel.getName());
                holder.fatherName.setText(userModel.getFatherName());
        }
    };
    mRecyclerView.setAdapter(adapter);*/
}