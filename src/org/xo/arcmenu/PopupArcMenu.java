package org.xo.arcmenu;


import android.view.View;

/**
 * Display and dismiss the arc menu.
 * 
 * @author duanqizhi
 *
 */
public interface PopupArcMenu extends ArcMenu {

	/**
	 * <p> Display the arc menu at the specified location.</p>
     * 
	 * @param parent  a parent view to handle touch event. If {@link #setTouchForward(boolean)}
	 * is set to be true, all touch event on parent view will be forwarded to arc menu.
	 * @param x x location offset to LEFT
	 * @param y y location offset to TOP
	 */
	void showAtLocation(View parent, int x, int y);
	
    /**
	 * <p>
	 * Dispose of the arc men. This method can be invoked only after
	 * {@link #showAtLocation(View, int, int)} has been executed. Failing
	 * that, calling this method will have no effect.
	 * </p>
	 */
	void dismiss();
	
    /**
     * <p>Set arc menu to receive parent's touch event.</p>
     *   
     * @param touchForward if true, the touch event from parent will dispatch to arc menu.
     */
	void setTouchForward(boolean touchForward);
	
	/**
	 * <p>Listener that is called when this popup window is dismissed.</p>
	 */
	interface OnDismissListener {
	    /**
	     * Called when this popup window is dismissed.
	     */
	    public void onDismiss();
	}
}
