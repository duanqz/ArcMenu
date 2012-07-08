package org.xo.arcmenu.internal;

import org.xo.arcmenu.ArcMenuItem;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Arc Menu Item Drawable.
 * 
 * @author duanqizhi
 *
 * @hide
 */
public class ArcMenuItemDrawable extends Drawable {

	private boolean mSelected;
	
	private float mStart;
	private float mSweep;
	private RectF mOval;
	
	private ArcMenuItem mArcMenuItem;
	
	public ArcMenuItemDrawable(ArcMenuItem arcMenuItem) {
		mArcMenuItem = arcMenuItem;
	}
	
	/**
	 * Set start and sweep angle of the arc menu item and the oval bounds the menu item.
	 * @param startAngle
	 * @param sweepAngle
	 * @param oval
	 */
	public void setAngleAndOval(int startAngle, int sweepAngle, RectF oval) {
		mStart = startAngle;
		mSweep = sweepAngle;
		mOval = oval;
	}
	
	/**
	 * Set the item selected status.
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		mSelected = selected;
	}
	
	@Override
	public void draw(Canvas canvas) {
		drawItemShape(canvas);
		drawTitle(canvas);
		drawIcon(canvas);
	}
	
	private void drawItemShape(Canvas canvas) {
		canvas.drawArc(mOval, mStart, mSweep, true, PaintUtils.getPaint(mSelected));
		if(mSelected) {
			mSelected = false;
		}
	}
	
	private void drawTitle(Canvas canvas) {
		canvas.save();
		canvas.translate(mOval.centerX(), mOval.centerY());
		canvas.rotate(mStart+mSweep/2-180);
		canvas.translate(50-mOval.centerX(), 16);
		canvas.drawText(mArcMenuItem.getTitle().toString(), 0, 0, PaintUtils.getTextPaint());
		canvas.restore();
	}
	
	private void drawIcon(Canvas canvas) {
		
	}

	@Override
	public void setAlpha(int alpha) { }

	@Override
	public void setColorFilter(ColorFilter cf) { }

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}
}
