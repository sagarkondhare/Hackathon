package com.sagark.hackathon;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;
import java.util.TreeSet;

public class CommonMethods {
    public static void saveSetPreferences(Context context, String strKey, List<String> stringSet) {
        try {
            if (context != null) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (stringSet != null)
                    editor.putString(strKey, new Gson().toJson(stringSet));
                else
                    editor.putStringSet(strKey, new TreeSet<String>());
                editor.apply();
            }
        } catch (Exception e) {
            printStackTrace(e);
        }
    }

    public static void saveIntPreferences(Context context, String strKey, int intValue) {
        try {
            if (context != null) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(strKey, intValue);
                editor.apply();
            }
        } catch (Exception e) {
            printStackTrace(e);
        }
    }


    public static void saveBooleanPreferences(Context context, String strKey, boolean boolValue) {
        try {
            if (context != null) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(strKey, boolValue);
                editor.apply();
            }
        } catch (Exception e) {
            printStackTrace(e);
        }
    }

    public static void saveLongPreferences(Context context, String strKey, long ll_steps) {
        try {
            if (context != null) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong(strKey, ll_steps);
                editor.apply();
            }
        } catch (Exception e) {
            printStackTrace(e);
        }
    }

    public static void saveStringPreferences(Context context, String strKey, String strValue) {
        try {
            if (context != null) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (strValue != null)
                    editor.putString(strKey, strValue);
                else
                    editor.putString(strKey, "");
                editor.apply();
            }
        } catch (Exception e) {
            printStackTrace(e);
        }
    }

    public static Object getPreferences(Context context, String key, int preferenceDataType) {
        Object value = null;
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            switch (preferenceDataType) {
                case CommonEnviornment.PREFTYPE_BOOLEAN:
                    value = sharedPreferences.getBoolean(key, false);
                    break;
                case CommonEnviornment.PREFTYPE_INT:
                    value = sharedPreferences.getInt(key, 0);
                    break;
                case CommonEnviornment.PREFTYPE_STRING:
                    value = sharedPreferences.getString(key, "");
                    break;
                case CommonEnviornment.PREFTYPE_LONG:
                    value = sharedPreferences.getLong(key, 0L);
                    break;
                case CommonEnviornment.PREFTYPE_ARRAYLIST:
                    return sharedPreferences.getString(key, null);

            }
            //}
        } catch (Exception e) {
            printStackTrace(e);
            switch (preferenceDataType) {
                case CommonEnviornment.PREFTYPE_BOOLEAN:
                    value = false;
                    break;
                case CommonEnviornment.PREFTYPE_INT:
                    value = 0;
                    break;
                case CommonEnviornment.PREFTYPE_STRING:
                    value = "";
                    break;
                case CommonEnviornment.PREFTYPE_LONG:
                    value = 0L;
                    break;
            }
        }
        return value;
    }

    private static void printStackTrace(Exception e) {
        e.printStackTrace();
    }

    public static void shortToast(Context context, String message) {
        try {
            if (!TextUtils.isEmpty(message))
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            printStackTrace(e);
        }
    }
}
