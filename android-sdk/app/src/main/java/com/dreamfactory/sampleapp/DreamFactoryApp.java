package com.dreamfactory.sampleapp;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Nirmel on 6/3/16.
 */
public class DreamFactoryApp extends Application {

    private static Context context;

    public static String SESSION_TOKEN;

    public static String INSTANCE_URL;

    public static String DB_SVC;

    public static String API_KEY;

    public void onCreate() {
        super.onCreate();

        DreamFactoryApp.context = getApplicationContext();

        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);

            Bundle bundle = ai.metaData;

            SESSION_TOKEN= bundle.getString("sessionToken");
            INSTANCE_URL= bundle.getString("instanceUrl");
            DB_SVC= bundle.getString("dbSvc");
            API_KEY= bundle.getString("apiKey");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(DreamFactoryApp.class.getSimpleName(), "Error while reading application meta data", e);
        }
    }

    public static Context getAppContext() {
        return DreamFactoryApp.context;
    }
}
