package com.freestand.ranu.fsmark2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.model.FAQ.Faq;
import com.freestand.ranu.fsmark2.data.model.alert.Alert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 11/02/18.
 */

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.MyViewHolder> {

    private List<Faq> faqList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question, date, answer;

        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            answer = (TextView) view.findViewById(R.id.description);
        }
    }


    public FaqAdapter(List<Faq> faqList) {
        this.faqList = faqList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alert, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Faq faq = faqList.get(position);
        holder.question.setText(faq.getQuestion());
        holder.date.setText("");
        holder.answer.setText(faq.getAnswer());
    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }
}