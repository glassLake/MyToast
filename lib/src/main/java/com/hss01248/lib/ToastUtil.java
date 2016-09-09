package com.hss01248.lib;

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
 * Created by hss01248 on 11/25/2015.
 */
public class ToastUtil {

    private static Toast mToast;
    private static Context context;
    private static Handler mainHandler;
    private static boolean isDebugMode = false;
    public static void init(Context context,Handler mainHandler,boolean isDebugMode){
        ToastUtil.context = context;
        ToastUtil.mainHandler = mainHandler;
        ToastUtil.isDebugMode = isDebugMode;
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

                    TextView textV = (TextView) layout.findViewById(R.id.toast_text);
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
