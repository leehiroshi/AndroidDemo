package com.example.flowlayouttest;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Auxiliary class for unit conversion
 */
public class DensityUtils {
	private DensityUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * dip converted to px
	 * 
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * dip converted to px
	 * 
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dpTopx(Context context, float dpValue) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context
		        .getResources().getDisplayMetrics());
	}

	/**
	 * px converted to dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * px converted to dip
	 * 
	 * @param context
	 * @param pxVal
	 * @return
	 */
	public static float pxTodp(Context context, float pxVal) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (pxVal / scale);
	}

	/**
	 * px converted to sp
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * px converted to sp
	 *
	 */
	public static float pxTosp(Context context, float pxVal) {
		return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
	}

	/**
	 * sp converted to px
	 * 
	 * @param context
	 * @param spValue
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * sp converted to px
	 * 
	 * @param context
	 * @param spValue
	 * @return
	 */
	public static int spTopx(Context context, float spValue) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context
		        .getResources().getDisplayMetrics());
	}

	public static int getDialogW(Context aty) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = aty.getResources().getDisplayMetrics();
		int w = dm.widthPixels - 100;
		// int w = aty.getWindowManager().getDefaultDisplay().getWidth() - 100;
		return w;
	}

	/**
	 * get Screen Width
	 * 
	 * @param aty
	 * @return
	 */
	public static int getScreenW(Context aty) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = aty.getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		// int w = aty.getWindowManager().getDefaultDisplay().getWidth();
		return w;
	}

	/**
	 * get Screen Height
	 * 
	 * @param aty
	 * @return
	 */
	public static int getScreenH(Context aty) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = aty.getResources().getDisplayMetrics();
		int h = dm.heightPixels;
		// int h = aty.getWindowManager().getDefaultDisplay().getHeight();
		return h;
	}
}