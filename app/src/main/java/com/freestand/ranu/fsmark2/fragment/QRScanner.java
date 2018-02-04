package com.freestand.ranu.fsmark2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freestand.ranu.fsmark2.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.barcode.BarcodeReader;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by prateek on 14/1/18.
 */

public class QRScanner extends Fragment implements ZXingScannerView.ResultHandler{
    @BindView(R.id.scan_view) ZXingScannerView mScannerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qr_scanner, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void handleResult(Result result) {
        Log.v("hey ", result.getText()); // Prints scan results
        Log.v("this ", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
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


