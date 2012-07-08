package org.xo.arcmenu;

import android.graphics.drawable.Drawable;

/**
 * Interface for direct access to a previously created menu item.
 * 
 * @author duanqizhi
 */
public interface ArcMenuItem  {

    /**
     * Interface definition for a callback to be invoked when a menu item is
     * selected.
     */
	interface OnSelectedListener {
		void onSelected(ArcMenuItem item);
	}
	
    /**
     * Return the identifier for this menu item.  The identifier can not
     * be changed after the menu is created.
     *
     * @return The menu item's identifier.
     */
    public int getItemId();
	
    
    /**
     * Retrieve the current title of the item.
     *
     * @return The title.
     */
    public CharSequence getTitle();
    
	/**
	 * Change the title associated with this item.
	 * 
	 * @param title The new text to be displayed.
	 * @return This Item so additional setters can be called.
	 */
    public ArcMenuItem setTitle(CharSequence title);

    /**
     * Change the title associated with this item.
     * 
     * @param title The resource id of the new text to be displayed.
     * @return This Item so additional setters can be called.
     */
    public ArcMenuItem setTitle(int title);
    
    /**
     * Returns the icon for this item as a Drawable (getting it from resources if it hasn't been
     * loaded before).
     * 
     * @return The icon as a Drawable.
     */
    public Drawable getIcon();
    
    /**
     * Change the icon associated with this item. 
     * 
     * @param icon The new icon (as a Drawable) to be displayed.
     * @return This Item so additional setters can be called.
     */
    public ArcMenuItem setIcon(Drawable icon);

    /**
     * Change the icon associated with this item. 
     * <p>
     * 
     * @param iconRes The new icon (as a resource ID) to be displayed.
     * @return This Item so additional setters can be called.
     */
    public ArcMenuItem setIcon(int iconRes);
    
    /**
     * Retrieve the OnSelectedListener instance set before.
     * @return The OnSelectedListener 
     */
    public OnSelectedListener getOnSelectedListener();
    
    /**
     * Set a custom listener for invocation of this menu item. In most
     * situations.
     * 
     * @param l The object to receive invocations.
     * @return This Item so additional setters can be called.
     */
    public ArcMenuItem setOnSelectedListener(OnSelectedListener l);
	
}
