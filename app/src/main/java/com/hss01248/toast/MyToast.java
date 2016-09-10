package com.hss01248.toast;

import android.app.Application;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.hss01248.lib.CustomToastUtil;
import com.hss01248.lib.ToastUtil;
import com.hss01248.lib.util.OSJudgementUtil;


/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class MyToast  {

    private static boolean isUseCustomToast;
    private static boolean isMiuiOs;

    public static void init(@NonNull Application context, @NonNull Handler mainHandler, boolean isDebugMode,boolean isUseCustomToast){


        //判断是不是miui以及miui的版本号:
        isMiuiOs =  OSJudgementUtil.isMIUI();

        Log.e("toast","code:"+OSJudgementUtil.MiuiOsCode);//code:6
        Log.e("toast","name:"+OSJudgementUtil.MiuiOsName);//name:V8  :miui8
        if (isMiuiOs){
            if ( TextUtils.isDigitsOnly(OSJudgementUtil.MiuiOsCode)){
                int code = Integer.parseInt(OSJudgementUtil.MiuiOsCode);
                if (code < 6){//miui8以下的,使用自定义toast
                    isUseCustomToast = true;
                }else {//miui8以上的,使用系统的toast,因为它屏蔽了Type_toast,调用会崩溃或不弹出
                    isUseCustomToast = false;
                }
            }
        }


        //初始化
        MyToast.isUseCustomToast = isUseCustomToast;
        if (isUseCustomToast){
            CustomToastUtil.init(context,mainHandler,isDebugMode);
        }else {
            ToastUtil.init(context,mainHandler,isDebugMode);
        }

    }

    public static void showToast(String text){
        if (isUseCustomToast){
            CustomToastUtil.showToast(text);
        }else {
            ToastUtil.showToast(text);
        }
    }

    public static void cancelToast() {
        if (isUseCustomToast){
            CustomToastUtil.cancelToast();
        }else {
            ToastUtil.cancelToast();
        }
    }

    public static void showDebugToast(final String text) {

        if (isUseCustomToast){
            CustomToastUtil.showDebugToast(text);
        }else {
            ToastUtil.showDebugToast(text);
        }
    }

    public static void showLongToast(final String text) {

        if (isUseCustomToast){
            CustomToastUtil.showLongToast(text);
        }else {
            ToastUtil.showLongToast(text);
        }
    }


    public static void showSuccessToast(String text){
        if (isUseCustomToast){
            CustomToastUtil.showSuccessToast(text);
        }else {
            ToastUtil.showSuccessToast(text);
        }
    }

    public static void showFailToast(String text){

        if (isUseCustomToast){
            CustomToastUtil.showFailToast(text);
        }else {
            ToastUtil.showFailToast(text);
        }
    }
}
