package com.awingcorsair.simplepassword.Util;

import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by Mao on 2016/9/18.
 */
public class utils {
    public static void copy(String content, Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }
}
