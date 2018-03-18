package com.freestand.ranu.fsmark2.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.freestand.ranu.fsmark2.Constants;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.FirebaseDatabaseHelper;
import com.freestand.ranu.fsmark2.data.sharedpf.SharedPrefsHelper;
import com.freestand.ranu.fsmark2.di.ComponentFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserSignUP extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    @BindView(R.id.interested_in) RadioGroup interestedIn;
    @BindView(R.id.done) Button done;
    @BindView(R.id.age_group) Spinner ageGroup;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female) RadioButton female;
    @Inject SharedPrefsHelper sharedPrefsHelper;
    FirebaseAuth mAuth;

    private final String[] AGES = { "Below 18", "18-24", "25-30", "31-36", "37-42", "43-49", "Above 50" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        ComponentFactory.getComponentFactory().getAppComponent(this.getApplication()).inject(this);
        ButterKnife.bind(this);
        attachClickListeners();
        mAuth = FirebaseAuth.getInstance();
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,AGES);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        ageGroup.setAdapter(aa);

    }

    private void attachClickListeners() {
        ageGroup.setOnItemSelectedListener(this);
        done.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if(view.getId() == done.getId()) {
            if(interestedIn.getCheckedRadioButtonId() == -1 ) {
                Toast.makeText(getBaseContext(), "Select Interested In", Toast.LENGTH_LONG).show();
            } else {
                String gender = "";
                if(interestedIn.getCheckedRadioButtonId() == male.getId()) {
                    gender = "Male";
                } else if (interestedIn.getCheckedRadioButtonId() == female.getId()) {
                    gender = "Female";
                } else {
                    gender = "Others";
                }
                saveAndSendData(gender);
                Intent intent = new Intent(getBaseContext(), Location.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(intent);
                finish();
            }
        }
    }

    private void saveAndSendData(String gender) {
        Map<String, Object> userMainInfo = new HashMap<>();

        userMainInfo.put("email", ""+ mAuth.getCurrentUser().getEmail());
        sharedPrefsHelper.put(Constants.USER_EMAIL, mAuth.getCurrentUser().getEmail());

        userMainInfo.put("name", mAuth.getCurrentUser().getDisplayName());
        sharedPrefsHelper.put(Constants.USER_NAME, mAuth.getCurrentUser().getDisplayName());

        userMainInfo.put("gender", gender);
        sharedPrefsHelper.put(Constants.USER_GENDER, gender);

        userMainInfo.put("photoURL", makePhotoUrl(AccessToken.getCurrentAccessToken().getUserId()));
        sharedPrefsHelper.put(Constants.USER_PHOTO_URL, makePhotoUrl(AccessToken.getCurrentAccessToken().getUserId()));

        userMainInfo.put("dob", ageGroup.getSelectedItem().toString());
        sharedPrefsHelper.put(Constants.USER_DOB, ageGroup.getSelectedItem().toString());

        userMainInfo.put("fcmToken", FirebaseInstanceId.getInstance().getToken());
        FirebaseDatabaseHelper.getInstance().setValue(userMainInfo);

    }
    private String makePhotoUrl(@NonNull String fbId) {
        return  "https://graph.facebook.com/" + fbId + "/picture?type=large";
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(),AGES[i] ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
