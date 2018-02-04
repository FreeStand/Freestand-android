package com.freestand.ranu.fsmark2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.model.Alert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 04/02/18.
 */
public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.MyViewHolder> {

    private List<Alert> alertList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, description;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            description = (TextView) view.findViewById(R.id.description);
        }
    }


    public AlertAdapter(List<Alert> alertList) {
        this.alertList = alertList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alert, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Alert alert = alertList.get(position);
        holder.title.setText(alert.getTitle());
        holder.description.setText(alert.getBody());
        holder.date.setText(alert.getDate());
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }
}