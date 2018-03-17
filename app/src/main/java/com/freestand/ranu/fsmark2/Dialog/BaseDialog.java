package com.freestand.ranu.fsmark2.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

import com.freestand.ranu.fsmark2.R;

/**
 * Created by prateek on 7/1/18.
 */
public abstract class BaseDialog {
    private Context context;
    public BaseDialog(Context context) {
        this.context = context;
    }
    public void showDialog(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(getViewId());
        setActions();
        dialog.show();
    }
    abstract int getViewId();
    abstract void setActions();
}
