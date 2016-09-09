package com.hss01248.toast;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.hss01248.lib.ToastUtil;


/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class MyToast  {

    public static void init( @NonNull Context context, @NonNull Handler mainHandler, boolean isDebugMode){
       ToastUtil.init(context,mainHandler,isDebugMode);
    }

    public static void showToast(String text){
        ToastUtil.showToast(text);
    }

    public static void cancelToast() {
        ToastUtil.cancelToast();
    }

    public static void showDebugToast(final String text) {
        ToastUtil.showDebugToast(text);
    }

    public static void showLongToast(final String text) {
        ToastUtil.showLongToast(text);
    }


    public static void showSuccessToast(String text){
        ToastUtil.showSuccessToast(text);
    }

    public static void showFailToast(String text){
        ToastUtil.showFailToast(text);
    }
}
