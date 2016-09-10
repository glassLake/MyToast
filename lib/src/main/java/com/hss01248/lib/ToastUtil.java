package com.hss01248.lib;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 *
 * todo 边缘雾化效果是怎么实现的?
 *
 * MIUI上可能是出于“绿化”的考虑，在维护Toast队列的时候，Toast只能在自己进程运行在顶端的时候才能弹出来，否则就“invisible to user”。
 *
 * 解决:
 * 彻底理解Toast原理和解决小米MIUI系统上没法弹Toast的问题
 *
 * http://www.cnblogs.com/lao-liang/p/5372125.html
 * http://caizhitao.com/2016/02/09/android-toast-compat/
 * https://github.com/zhitaocai/ToastCompat_Deprecated
 *
 * 相关问题:
 * https://github.com/zhitaocai/ToastCompat_Deprecated/issues/1
 *
 * Created by hss01248 on 11/25/2015.
 *
 * MIUI 8 会禁用了 TYPE_TOAST 类型
 *
 * //todo 自由控制显示时长
 */
public class ToastUtil {

    private static Toast mToast;
    private static Context context;
    private static Handler mainHandler;
    private static boolean isDebugMode = false;

    //必须是ApplicationContext，不然在小米3或小米Note（Android 4.4.4）无法起作用！
    public static void init(Application context, Handler mainHandler, boolean isDebugMode){
        ToastUtil.context = context;
        ToastUtil.mainHandler = mainHandler;
        ToastUtil.isDebugMode = isDebugMode;

        //
    }

    public static void showToast(String text){
        showToast(text,false);
    }

    private static void showToast( final String text, final boolean isLong) {

       if (mainHandler != null){
           mainHandler.post(new Runnable() {
               @Override
               public void run() {
                   if (mToast == null) {
                       mToast = Toast.makeText(context, text, isLong ?Toast.LENGTH_LONG :Toast.LENGTH_SHORT);
                   }else {
                       mToast.setText(text);
                       mToast.setDuration(isLong ?Toast.LENGTH_LONG :Toast.LENGTH_SHORT);
                   }
                   mToast.show();
               }
           });
       }



    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public static void showDebugToast(final String text) {
       if (!isDebugMode){
           return;
       }
        showToast(text,false);
    }

    public static void showLongToast(final String text) {
       showToast(text,true);
    }


    private static void showImageCneterToast( final String text, final int picId, final boolean isLong){
        if (mainHandler != null) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View layout = inflater.inflate(R.layout.toast_layout, null);

                    ImageView image = (ImageView) layout.findViewById(R.id.toast_image);
                    image.setImageResource(picId);

                    TextView textV = (TextView) layout.findViewById(R.id.message);
                    textV.setText(text);

                    final Toast toast = new Toast(context);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration((isLong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
                    toast.setView(layout);

                    toast.show();
                }
            });
        }



    }

    public static void showSuccessToast(String text){
        showImageCneterToast(text, R.mipmap.ic_done_white_36dp,false);
    }

    public static void showFailToast(String text){
        showImageCneterToast(text, R.mipmap.ic_error_outline_white_36dp,false);
    }
}
