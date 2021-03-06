package com.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;

/**
 * 轻量持久化
 */
public class SharePersistent {

    public static final String PREFS_NAME = Constant.TAG;


    public static void savePreferenceEn(Context context, String key, String value) {

        try {

            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();

            editor.putString(key, AESTool.encrpt(value));
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String getPreferenceDe(Context context, String key) {

        try {
            SharedPreferences settings = context
                    .getSharedPreferences(PREFS_NAME, 0);


            return AESTool.decrypt(settings.getString(key, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "";
    }


    public static void savePreference(Context context, String key, String value) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static void savePreference(Context context, String key, int value) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setObjectValue(Context context, String key,
                                      Object value) {
        try {
            SharedPreferences.Editor editor = context
                    .getSharedPreferences(PREFS_NAME, 0)
                    .edit();
            // 将map转换为byte[]
            ByteArrayOutputStream toByte = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(toByte);
            oos.writeObject(value);
            // 对byte[]进行Base64编码
            String payCityMapBase64 = new String(Base64Coder.encode(toByte
                    .toByteArray()));
            // 存储
            editor.putString(key, payCityMapBase64);

            editor.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.e("wzy", "save object user error!:" + ex.getLocalizedMessage());
        }
    }

    public static synchronized Object getObjectValue(Context context, String key) {
        SharedPreferences pre = context
                .getSharedPreferences(PREFS_NAME, 0);
        String str = pre.getString(key, null);
        if (str == null)
            return null;
        byte[] base64Bytes = Base64Coder.decode(str);

        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
        } catch (StreamCorruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            return ois.readObject();
        } catch (OptionalDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String getPerference(Context context, String key) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(key, "");
    }

    public static int getInt(Context context, String key) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(key, 0);
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defaultVal) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(key, defaultVal);
    }
}
