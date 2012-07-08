package org.xo.arcmenu;


/**
 * Interface for managing the items in a arc menu.
 * 
 * @author duanqizhi
 * 
 */
public interface ArcMenu {

    /**
     * Add a new item to the menu. This item displays the given title for its
     * label.
     * 
     * @param titleRes Resource identifier of title string.
     * @return The newly added menu item.
     */
	ArcMenuItem add(int titleRes);
	
    /**
     * Add a new item to the menu. This item displays the given title for its
     * label.
     * 
     * @param itemId Unique item ID.
     * @param titleRes Resource identifier of title string.
     * @return The newly added menu item.
     */
	ArcMenuItem add(int itemId, int titleRes);
	
    /**
     * Add a new item to the arc menu. This item displays the given title for its
     * label.
     * 
     * @param title The text to display for the item.
     * @return The newly added menu item.
     */
	ArcMenuItem add(CharSequence title);
	
    /**
     * Add a new item to the menu. This item displays the given title for its
     * label.
     * 
     * @param itemId Unique item ID.
     * @param title The text to display for the item.
     * @return The newly added menu item.
     */
	ArcMenuItem add(int itemId, CharSequence title);

}
