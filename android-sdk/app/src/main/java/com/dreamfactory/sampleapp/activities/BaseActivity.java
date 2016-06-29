package com.dreamfactory.sampleapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Created by Nirmel on 6/8/2016.
 *
 * Base activity for all other activities in application
 */
public abstract class BaseActivity extends Activity {

    public BaseActivity() {
    }

    /**
     * Log error message
     *
     * @param message
     */
    public void logError(String message) {
        Log.e(this.getClass().getSimpleName(), message);
    }

    /**
     * Log error message with included stacktrace
     *
     * @param message
     * @param t
     */
    public void logError(String message, Throwable t) {
        Log.e(this.getClass().getSimpleName(), message, t);
    }

    /**
     * Show and log error message with included stacktrace
     *
     * @param message
     * @param t
     */
    public void showError(String message, Throwable t) {
        showError(message + " " + (t.getMessage() != null ? t.getMessage() : ""));

        logError(message, t);
    }

    /**
     * Show error message
     *
     * @param message
     */
    public void showError(String message) {
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
