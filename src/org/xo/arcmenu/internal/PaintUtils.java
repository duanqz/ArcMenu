package org.xo.arcmenu.internal;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.text.TextPaint;

/**
 * Paints used to draw arc menu.
 * 
 * @author duanqizhi
 * 
 * @hide
 */
public class PaintUtils {

	private static final Paint mPaint;
	private static final Paint mTextPaint;
	
	public static final ColorFilter COLOR_FILER_NORMAL = new LightingColorFilter(0xEE000FF, 0xEEAAEEFF);
	public static final ColorFilter COLOR_FILER_SELECTED = new LightingColorFilter(0xFFAA00FF, 0xEEEEEAA);;
	
	static {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setAlpha(100);
		mPaint.setDither(true);
		mPaint.setShadowLayer(5, -2, 5, Color.DKGRAY);
		
		mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setAlpha(100);
		mTextPaint.setDither(true);
		mTextPaint.setTextSize(32);
	}
	
	public static Paint getPaint(boolean selected) {
		if(selected) {
			mPaint.setColorFilter(COLOR_FILER_SELECTED);
			mPaint.setShadowLayer(15, -5, -5, Color.LTGRAY);
		} else {
			mPaint.setColorFilter(COLOR_FILER_NORMAL);
			mPaint.setShadowLayer(5, -2, 5, Color.DKGRAY);
		}
		return mPaint;
	}
	
	
	public static Paint getTextPaint() {
		return mTextPaint;
	}
}
