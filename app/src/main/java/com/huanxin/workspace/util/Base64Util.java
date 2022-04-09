package com.huanxin.workspace.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Base64Util {
    public static String base2Str(String string) {
        return new String(Base64.decode(string.getBytes(), Base64.DEFAULT));
    }

    public static String str2Base(String string) {
        return Base64.encodeToString(string.getBytes(), Base64.DEFAULT);
    }

    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
