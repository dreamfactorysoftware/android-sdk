package com.dreamfactory.sampleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {
    static public final class Prefs {
        public static SharedPreferences get(Context context) {
            return context.getSharedPreferences("_dreamf_pref", 0);
        }
    }

    static public String getString(Context context, String key) {
        SharedPreferences settings = Prefs.get(context);
        return settings.getString(key, "");
    }

    static public String getString(Context context, String key, String defaultString) {
        SharedPreferences settings = Prefs.get(context);
        return settings.getString(key, defaultString);
    }

    static public synchronized void putString(Context context, String key, String value) {
        SharedPreferences settings = Prefs.get(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }
}