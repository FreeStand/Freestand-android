package com.freestand.ranu.fsmark2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by prateek on 6/1/18.
 */

public class SampleAdapter extends BaseAdapter<Object> {
    public SampleAdapter(Context context, ArrayList<Object> items) {
        super(context, items);
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    @Override
    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindData(RecyclerView.ViewHolder holder, Object val) {}
}
