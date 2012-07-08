package org.xo.arcmenu;

import org.xo.arcmenu.internal.PopupArcMenuBuilder;

import android.content.Context;

/**
 * Factory to create arc menus.
 * 
 * @author duanqizhi
 *
 */
public class ArcMenuFactory {

	/**
	 * Create popup arc menu.
	 * @param context
	 * @return a new popup arc menu.
	 */
	public static PopupArcMenu createPopupArcMenu(Context context) {
		return new PopupArcMenuBuilder(context);
	}
}
