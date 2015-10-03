package com.praim.test.app.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceHelper {
    private static final String SCORE = "score";

    public static int loadUserScore(Context context) {
        SharedPreferences preference = getSharedPreference(context.getApplicationContext());
        return preference.getInt(SCORE, 0);
    }

    public static void saveUserScore(Context context, int score) {
        SharedPreferences preference = getSharedPreference(context);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(SCORE, score);
        editor.apply();
    }

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
