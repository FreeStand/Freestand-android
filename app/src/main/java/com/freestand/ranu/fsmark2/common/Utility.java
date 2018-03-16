package com.freestand.ranu.fsmark2.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

/**
 * Created by prateek on 25/02/18.
 */

public class Utility {
    public static class DialogClass {
        public static ProgressDialog dialog;

        public static void showPleaseWait(Context context, String message) {
            message = TextUtils.isEmpty(message) ? "" : message;
            dialog = new ProgressDialog(context); // this = YourActivity
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(message);
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        public static void dismissPleaseWait() {
            dialog.dismiss();
        }
    }
}
