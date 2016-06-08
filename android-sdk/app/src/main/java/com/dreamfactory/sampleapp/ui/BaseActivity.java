package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Created by Nirmel on 6/8/2016.
 */
public class BaseActivity extends Activity {

    public BaseActivity() {
    }

    protected void logError(String message) {
        Log.e(this.getClass().getSimpleName(), message);
    }

    protected void logError(String message, Throwable t) {
        Log.e(this.getClass().getSimpleName(), message, t);
    }

    protected void showError(String message, Throwable t) {
        showError(message + " " + (t.getMessage() != null ? t.getMessage() : ""));

        logError(message, t);
    }

    protected void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        logError(message);
    }
}
