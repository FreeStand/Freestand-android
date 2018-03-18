package com.freestand.ranu.fsmark2.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.freestand.ranu.fsmark2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThankYou extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.insta_button)
    ImageButton instaButton;
    @BindView(R.id.fb_button) ImageButton fbButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        ButterKnife.bind(this);
        setClickListeners();
    }

    private void setClickListeners() {
        fbButton.setOnClickListener(this);
        instaButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int selectedId = view.getId();
        switch (selectedId) {
            case R.id.insta_button:
                Intent instaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com/freestandindia"));
                startActivity(instaIntent);
                break;
            case R.id.fb_button:
                Intent fbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/freestandindia"));
                startActivity(fbIntent);
                break;
        }
    }
}
