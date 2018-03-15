package com.freestand.ranu.fsmark2.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.freestand.ranu.fsmark2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Location extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.card_partner) CardView partnerCard;
    @BindView(R.id.card_elsewhere) CardView elseWhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        partnerCard.setOnClickListener(this);
        elseWhere.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == partnerCard.getId()) {

        } else if(view.getId() == elseWhere.getId()) {

        }
    }
}
