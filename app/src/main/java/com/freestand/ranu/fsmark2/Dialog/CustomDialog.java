package com.freestand.ranu.fsmark2.Dialog;

import android.content.Context;

import com.freestand.ranu.fsmark2.R;

/**
 * Created by prateek on 7/1/18.
 */

public class CustomDialog extends BaseDialog{

    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    int getViewId() {
        return R.layout.activity_after_login;
    }

    @Override
    void setActions() {

    }

}
