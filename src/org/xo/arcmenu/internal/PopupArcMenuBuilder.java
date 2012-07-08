package org.xo.arcmenu.internal;

import org.xo.arcmenu.ArcMenuItem;
import org.xo.arcmenu.ArcMenuItem.OnSelectedListener;
import org.xo.arcmenu.PopupArcMenu;
import org.xo.arcmenu.internal.ArcMenuView.OnItemSelectedListener;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Implementation of popup arc menu. 
 * 
 * @author duanqizhi
 * 
 * @hide
 */
public final class PopupArcMenuBuilder extends AbsArcMenu implements OnItemSelectedListener, PopupArcMenu {

	private static final int WIDTH = 250;
	private static final int HEIGHT = 500;
	private static final int PADDING = 30;
	
	private WindowManager mWindowManager;
	
	private ArcMenuView mArcMenuView;
	
	private boolean mIsShowing;
	
	private boolean mIsTouchForward;
	
	private OnDismissListener mOnDismissListener;
	
	private OnTouchForwardListener mOnTouchForwardListener;
	
	private WindowManager.LayoutParams mLayoutParams;
	
	/**
	 * @param context
	 */
	public PopupArcMenuBuilder(Context context) {
		super(context);
		
		mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		mArcMenuView = new ArcMenuView(context, new Rect(PADDING, 0, (WIDTH<<1)-PADDING, HEIGHT));
		mArcMenuView.setOnItemSelectedListener(this);
		
		mOnTouchForwardListener = new OnTouchForwardListener();
		
		mLayoutParams = new WindowManager.LayoutParams();
		mLayoutParams.width = WIDTH;
		mLayoutParams.height = HEIGHT;
		mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
		mLayoutParams.format = PixelFormat.TRANSLUCENT;
	}
	
    /**
     * <p>Indicate whether this arc menu is showing on screen.</p>
     *
     * @return true if the arc menu is showing, false otherwise
     */
    public boolean isShowing() {
        return mIsShowing;
    }
    
    /**
     * <p>Indicate whether this arc menu is touch event forward target.</p>
     *
     * @return true if the arc menu is touch event forward target, false otherwise
     */
    public boolean isTouchForward() {
    	return mIsTouchForward;
    }
    
    @Override
    public void setTouchForward(boolean touchForward) {
    	mIsTouchForward = touchForward;
    }
    
    @Override
    public void dismiss() {
		if (isShowing()) {
			try {
				mWindowManager.removeView(mArcMenuView);
			} finally {
				mIsShowing = false;
	
				if (mOnDismissListener != null) {
					mOnDismissListener.onDismiss();
				}
			}
		}
	}

	@Override
	public void onItemSelected(int itemIdx) {
		ArcMenuItem item = mMenuItems.get(itemIdx); 
		if(item != null) {
			OnSelectedListener l = item.getOnSelectedListener();
			if(l != null) {
				l.onSelected(item);
			}
		}
	}

	@Override
	public void showAtLocation(View parent, int x, int y) {
        if (isShowing() || parent == null) {
            return;
        }

        mIsShowing = true;
        
        preparePopup(parent);
        
        // Figure out the position to popup.
        final WindowManager.LayoutParams p = mLayoutParams;
        p.x = x - WIDTH + PADDING;
        p.y = y - (HEIGHT>>1);
        
        invokePopup(p);
    }
	
	private void preparePopup(View parent) {
		mArcMenuView.setVisibility(View.VISIBLE);
		
		// Check if menu item changed.
		if(mIsMenuItemChanged) {
			mArcMenuView.update(mMenuItems.values());
			mIsMenuItemChanged = false;
		}
		// Check if as a touch forward target.
		if (mIsTouchForward) {
			parent.setOnTouchListener(mOnTouchForwardListener);
		}
	}
    
    private void invokePopup(WindowManager.LayoutParams p) {
        p.packageName = mContext.getPackageName();
        mWindowManager.addView(mArcMenuView, p);
    }
    
    /**
     * Forward touch event to arc menu if it is shown.
     * {@hide}
     */
    private class OnTouchForwardListener implements View.OnTouchListener {

    	private int[] mArcMenuLoc = new int[2];
    	
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(mArcMenuView.isShown()) {
				/*
				 * We have to figure the new event position on popup arc menu, 
				 * because the original event binding to parent view is not adapt
				 * to ArcMenuView.
				 */
				mArcMenuView.getLocationOnScreen(mArcMenuLoc);
				event.setLocation(event.getRawX() - mArcMenuLoc[0], 
						          event.getRawY() - mArcMenuLoc[1]);
				return mArcMenuView.dispatchTouchEvent(event);
			}
			
			return v.onTouchEvent(event);
		}
    }

}
