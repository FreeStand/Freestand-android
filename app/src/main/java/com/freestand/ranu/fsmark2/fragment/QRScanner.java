package com.freestand.ranu.fsmark2.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freestand.ranu.fsmark2.Activities.FeedbackScreen;
import com.freestand.ranu.fsmark2.R;
import com.freestand.ranu.fsmark2.data.model.checkqr.CheckQr;
import com.freestand.ranu.fsmark2.data.network.rest.ApiInterface;
import com.freestand.ranu.fsmark2.di.ComponentFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by prateek on 14/1/18.
 */

public class QRScanner extends BaseFragment implements ZXingScannerView.ResultHandler{

    @BindView(R.id.scan_view) ZXingScannerView mScannerView;
    @Inject Retrofit retrofitClient;
    String surveyId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    int setViewId() {
        return R.layout.fragment_qr_scanner;
    }

    @Override
    void onFragmentCreated() {
//        getData();
    }

    @Override
    void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    void getComponentFactory() {
        ComponentFactory.getComponentFactory().getNetComponent().inject(this);
    }


    @Override
    public void handleResult(Result result) {
        Log.v("hey ", result.getText()); // Prints scan results
        Log.v("this ", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        callResult(result);
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }

    private void callResult(Result result) {
        surveyId = result.getText().substring(0,5);
        String tempGender = result.getText().substring(5,6);
        String location = result.getText().substring(6);
        Log.e("print ", surveyId + " " + tempGender + " " + location);
        Map map = new HashMap();
        map.put("uid", FirebaseAuth.getInstance().getUid());
        map.put("lid", location);
        map.put("sid", surveyId);
        map.put("category", "General");
        getData(map);
    }

    private void getData(Map map) {
        ApiInterface apiService = retrofitClient.create(ApiInterface.class);
//         Map map = new HashMap();
//         map.put("uid", FirebaseAuth.getInstance().getUid());
//         map.put("lid", "MAIT");
//         map.put("sid", "s0120");
//         map.put("category", "Male");
        Call<CheckQr> call = apiService.getQrScannedRespose(map);
        call.enqueue(new Callback<CheckQr>() {
            @Override
            public void onResponse(Call<CheckQr>call, Response<CheckQr> response) {
                Log.e("response ", response.toString());
                CheckQr checkQr = response.body();
                if(checkQr != null && checkQr.getStatus() != null && checkQr.getStatus().equals("valid")) {
                    Intent intent = new Intent(getActivity(), FeedbackScreen.class);
                    intent.putExtra("question_list", (Serializable) checkQr.getDict().getQuestions());
                    intent.putExtra("direction", "com.freestand.ranu.fsmark2.Activities.Welcome");
                    startActivity(intent);
                }
                Log.d("hello ", "Number of alerts received: " + response.toString());
            }

            @Override
            public void onFailure(Call<CheckQr>call, Throwable t) {
                // Log error here since request failed
                Log.e(" ", t.toString());
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        PermissionListener dialogPermissionListener =
                                DialogOnDeniedPermissionListener.Builder
                                        .withContext(getContext())
                                        .withTitle("Camera permission")
                                        .withMessage("Camera permission is needed to take pictures of your cat")
                                        .withButtonText(android.R.string.ok)
                                        .build();
                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
}


