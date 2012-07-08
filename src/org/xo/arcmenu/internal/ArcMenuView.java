package org.xo.arcmenu.internal;

import java.util.Collection;
import java.util.Iterator;

import org.xo.arcmenu.ArcMenuItem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

/**
 * Draw the view of arc menu. 
 * 
 * @author duanqizhi
 * 
 * @hide
 */
public class ArcMenuView extends View {

	private static final int START_ANGLE = 120;
	private static final int SWEEP_ANGLE = 120;
	
	/** Menu item drawables. */
	private ArcMenuItemDrawable[] mItemDrawables;
	
	/** The Oval bounding the arc. */
	private Rect mOval;
	
	/** Clipping region */
	private Path mClipRegion;
	
	/** Sweep angle of each item. */
	private int mItemSweep;
	
    /**
     * a Listener used to dispatch menu item selected events.
     * This field should be made private, so it is hidden from the SDK.
     * {@hide}
     */
	private OnItemSelectedListener mOnItemSelectedListener;
	
	/**
	 * The selected menu item index.
	 * {@hide}
	 */
	private int mSelectedItemIdx = -1;
	
	public ArcMenuView(Context context, Rect oval) {
		super(context);
		mOval = oval;
		
		createClippingRegion();
	}
	
	private void createClippingRegion() {
		mClipRegion = new Path();
		mClipRegion.addCircle(mOval.centerX()+30, mOval.centerY(), mOval.centerY()>>2, Direction.CW);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// Draw clipping region
		canvas.clipPath(mClipRegion, Region.Op.DIFFERENCE);
		
		// Draw menu items
		for(Drawable dr : mItemDrawables) {
			dr.draw(canvas);
		}
		
	}
	
	/**
	 * Update the arc menu UI with menu items.
	 * @param menuItems collection of menu items
	 */
    public void update(Collection<ArcMenuItem> menuItems) {
    	int numOfItems = menuItems.size();
    	if(numOfItems <= 0) {
    		return;
    	}
    	
    	// Create item drawables
		mItemSweep = SWEEP_ANGLE / numOfItems;
		mItemDrawables = new ArcMenuItemDrawable[numOfItems];
		ArcMenuItemDrawable item;
		Iterator<ArcMenuItem> iter = menuItems.iterator();
		int i = 0;
		while(iter.hasNext()) {
			item = new ArcMenuItemDrawable(iter.next());
			item.setAngleAndOval(START_ANGLE + mItemSweep*i, mItemSweep, new RectF(mOval));
			mItemDrawables[i] = item;
			
			i++;
		}
    }
    
    
	/**
     * Register a callback to be invoked when this menu item is selected. 
     *
     * @param l The callback that will run
     */
	public void setOnItemSelectedListener(ArcMenuView.OnItemSelectedListener l) {
		mOnItemSelectedListener = l;
	}
	
    /**
     * Call this view's OnItemSelectedListener, if it is defined.
     *
     * @return True there was an assigned OnItemSeletectedListener that was called, false
     *         otherwise is returned.
     */
	public boolean performOnItemSelected() {
		setVisibility(GONE);
		if(mOnItemSelectedListener != null) {
			mOnItemSelectedListener.onItemSelected(mSelectedItemIdx);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			int index = getTouchedItem((int) event.getX(), (int) event.getY());
			if(index < 0 || index >= mItemDrawables.length) {
				return true;
			}
			
			mItemDrawables[index].setSelected(true);
			mSelectedItemIdx = index;
			invalidate();
			return true;
		case MotionEvent.ACTION_UP:
			performOnItemSelected();
			return true;
		}
		
		return super.onTouchEvent(event);
	}
	
	/**
	 * Return the touched item index.
	 */
	private int getTouchedItem(final int xOnView, final int yOnView) {
		// Compute the (x, y) on coordinate where the origin is the center of the rectangle.
		int x = xOnView - mOval.centerX(),
		    y = yOnView - mOval.centerY();
		
		int angle = (int) Math.toDegrees(Math.atan2(y, x));
		angle = (360+angle) % 360;
		
		return (angle-START_ANGLE) / mItemSweep;
	}
	
    /**
     * Interface definition for a callback to be invoked when a menu item is selected.
     */
	public interface OnItemSelectedListener {
        /**
         * Called when a menu item has been selected.
         *
         * @param itemIdx The menu item index that was selected.
         */
		void onItemSelected(int itemIdx);
	}

}
