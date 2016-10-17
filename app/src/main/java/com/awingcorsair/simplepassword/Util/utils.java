package com.awingcorsair.simplepassword.Util;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mao on 2016/9/18.
 */
public class Utils {
    public static void copy(String content, Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    public boolean getFlag(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info",Context.MODE_PRIVATE);
        boolean flag=sharedPreferences.getBoolean("lock_flag",false);
        return flag;
    }

    public void setFlagA(Context context,boolean flag){
        SharedPreferences preferences=context.getSharedPreferences("info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("lock_flag_add",flag);
        editor.commit();
    }

    public boolean getFlagA(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info",Context.MODE_PRIVATE);
        boolean flag=sharedPreferences.getBoolean("lock_flag_add",true);
        return flag;
    }

    public void setFlag(Context context,boolean flag){
        SharedPreferences preferences=context.getSharedPreferences("info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("lock_flag",flag);
        editor.commit();
    }

    public String getPassword(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("info",Context.MODE_PRIVATE);
        String password=sharedPreferences.getString("mainPassword","null");
        return password;
    }

    public void setPassword(Context context,String password){
        SharedPreferences preferences=context.getSharedPreferences("info",context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("mainPassword",password);
        editor.commit();
    }


}
