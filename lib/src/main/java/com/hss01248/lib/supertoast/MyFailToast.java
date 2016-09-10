package com.hss01248.lib.supertoast;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.hss01248.lib.R;

/**
 * Created by Administrator on 2016/9/10.
 */
public class MyFailToast extends SuperToast {
    public MyFailToast(@NonNull Context context) {
        super(context);
    }

    public MyFailToast(@NonNull Context context, @NonNull Style style) {
        super(context, style);
    }

    protected MyFailToast(@NonNull Context context, @Style.Type int type) {
        super(context, type);
    }

    protected MyFailToast(@NonNull Context context, @NonNull Style style, @Style.Type int type) {
        super(context, style, type);
    }

    protected MyFailToast(@NonNull Context context, @NonNull Style style, @Style.Type int type, @IdRes int viewGroupID) {
        super(context, style, type, viewGroupID);
    }

    @Override
    protected View onCreateView(Context context, LayoutInflater layoutInflater, int type) {
        return layoutInflater.inflate(R.layout.toast_layout_fail, null);
    }

    @Override
    protected void onPrepareShow() {
        super.onPrepareShow();
        mView.setBackgroundResource(R.drawable.bg);
    }
}
