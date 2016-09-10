package com.hss01248.lib;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;

import com.hss01248.lib.supertoast.MyFailToast;
import com.hss01248.lib.supertoast.MySuccessToast;
import com.hss01248.lib.supertoast.Style;
import com.hss01248.lib.supertoast.SuperToast;


/**
 *
 * todo 边缘雾化效果是怎么实现的?
 * Created by hss01248 on 11/25/2015.
 */
public class CustomToastUtil {

    private static SuperToast mTextToast;
    private static SuperToast mSuccessToast;
    private static SuperToast mFailToast;
    private static Context context;
    private static Handler mainHandler;
    private static boolean isDebugMode = false;
    public static void init(Context context,Handler mainHandler,boolean isDebugMode){
        CustomToastUtil.context = context;
        CustomToastUtil.mainHandler = mainHandler;
        CustomToastUtil.isDebugMode = isDebugMode;
    }

    public static void showToast(String text){
        showToast(text,false);
    }

    private static void showToast( final String text, final boolean isLong) {

       if (mainHandler != null){
           final int duration =  isLong ? Style.DURATION_LONG :Style.DURATION_SHORT;
           mainHandler.post(new Runnable() {
               @Override
               public void run() {
                   if (mTextToast == null) {

                      // mTextToast = CustomToast.makeText(context, text,duration);
                       mTextToast = new SuperToast(context).setText(text).setDuration(duration);
                   }else {

                       if ( mTextToast.isShowing()){
                           mTextToast.dismiss();
                       }


                       mTextToast.setText(text);
                       mTextToast.setDuration(duration);
                   }
                   mTextToast.show();
               }
           });
       }



    }

    public static void cancelToast() {
        if (mTextToast != null) {
            mTextToast.dismiss();
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


    private static void showImageCneterToast(final String text, final boolean isSuccess, final boolean isLong){
        if (mainHandler != null) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    if (mTextToast != null && mTextToast.isShowing()){
                        mTextToast.dismiss();
                    }




                    if (isSuccess && mSuccessToast == null){
                        mSuccessToast =  new MySuccessToast(context);
                    }else {
                        if (mSuccessToast.isShowing()){
                            mSuccessToast.dismiss();
                        }

                        if (mFailToast != null && mFailToast.isShowing()){
                            mFailToast.dismiss();
                        }
                    }

                    if (!isSuccess && mFailToast == null){
                        mFailToast = new MyFailToast(context);
                    }else {
                        if (mFailToast.isShowing()){
                            mFailToast.dismiss();
                        }

                        if (mSuccessToast != null && mSuccessToast.isShowing()){
                            mSuccessToast.dismiss();
                        }
                    }
                    final int duration =  isLong ? Style.DURATION_LONG :Style.DURATION_SHORT;
                    final SuperToast toast = isSuccess ? mSuccessToast : mFailToast;//new CustomToast(context);
                   // toast.set(layout);
                    toast.setText(text);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(duration);

                    toast.show();
                }
            });
        }



    }

    public static void showSuccessToast(final String text){
       showImageCneterToast(text,true,false);
    }

    public static void showFailToast(final String text){
        showImageCneterToast(text,false,false);
    }
}
