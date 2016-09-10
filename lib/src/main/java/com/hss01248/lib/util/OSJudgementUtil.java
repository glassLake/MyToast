package com.hss01248.lib.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public final class OSJudgementUtil {

	/**
	 * 因为判断是否为小米系统是有一个读文件的过程，所以不每次都读取判断，只第一次才判断
	 */
	public static boolean mIsJudgementBefore = false;

	public static boolean mIsMIUIOS = false;

	public static String MiuiOsCode = "";
	public static String MiuiOsName = "";

	/**
	 * 判断是否是MIUI系统
	 *
	 * @return true 是小米平台
	 */
	public static boolean isMIUI() {
		try {
			if (!mIsJudgementBefore) {
				mIsJudgementBefore = true;
				Properties properties = new Properties();
				properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));

				MiuiOsCode = properties.getProperty("ro.miui.ui.version.code", "");
				MiuiOsName = properties.getProperty("ro.miui.ui.version.name", "");

				mIsMIUIOS = (!TextUtils.isEmpty(MiuiOsCode)) || (!TextUtils.isEmpty(MiuiOsCode))
				            || (!TextUtils.isEmpty(properties.getProperty("ro.miui.internal.storage", null)));
			}
		} catch (Exception e) {
		}
		return mIsMIUIOS;
	}

}