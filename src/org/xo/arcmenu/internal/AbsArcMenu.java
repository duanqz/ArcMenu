package org.xo.arcmenu.internal;

import java.util.HashMap;
import java.util.Map;

import org.xo.arcmenu.ArcMenu;
import org.xo.arcmenu.ArcMenuItem;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Abstract Arc Menu.
 * 
 * @author duanqizhi
 * 
 * @hide
 */
public abstract class AbsArcMenu implements ArcMenu {

	private static final int MAX_ITEM_NUM = 5;
	
	protected Context mContext;
	protected Map<Integer, ArcMenuItem> mMenuItems;
	protected boolean mIsMenuItemChanged;
	
	public AbsArcMenu(Context context) {
		mContext = context;
		mMenuItems = new HashMap<Integer, ArcMenuItem>(MAX_ITEM_NUM);
		
	}
	
	@Override
	public ArcMenuItem add(int titleRes) {
		return createArcMenuItem(mMenuItems.size(), mContext.getString(titleRes));
	}
	
	@Override
	public ArcMenuItem add(int itemId, int titleRes) {
		return createArcMenuItem(itemId, mContext.getString(titleRes));
	}
	
	@Override
	public ArcMenuItem add(CharSequence title) {
		return createArcMenuItem(mMenuItems.size(), title);
	}
	
	@Override
	public ArcMenuItem add(int itemId, CharSequence title) {
		return createArcMenuItem(itemId, title);
	}
	
	protected ArcMenuItem createArcMenuItem(final int itemId, final CharSequence title) {
		if(mMenuItems.size() == MAX_ITEM_NUM) {
			throw new IllegalArgumentException("The number of menu item reach the maximum " + MAX_ITEM_NUM);
		}
		
		if(mMenuItems.containsKey(itemId)) {
			throw new IllegalArgumentException("The item is already exist.");
		}
		
		/*
		 * Create a anonymous inner class of ArcMenuItem.
		 */
		ArcMenuItem item = new ArcMenuItem() {
			private int mItemId;
			private CharSequence mTitle;
			private Drawable mIcon;
			private OnSelectedListener mOnSelectedLis;
			
			{
				mItemId = itemId;
				mTitle = title;
			}
			
			@Override
			public int getItemId() {
				return mItemId;
			}

			@Override
			public CharSequence getTitle() {
				return mTitle;
			}
			
			@Override
			public ArcMenuItem setTitle(CharSequence title) {
				mTitle = title;
				return this;
			}

			@Override
			public ArcMenuItem setTitle(int title) {
				mTitle = mContext.getString(title);
				return this;
			}

			@Override
			public Drawable getIcon() {
				return mIcon;
			}
			
			@Override
			public ArcMenuItem setIcon(Drawable icon) {
				mIcon = icon;
				return this;
			}

			@Override
			public ArcMenuItem setIcon(int iconRes) {
				mIcon = mContext.getResources().getDrawable(iconRes);
				return this;
			}

			@Override
			public OnSelectedListener getOnSelectedListener() {
				return mOnSelectedLis;
			}

			@Override
			public ArcMenuItem setOnSelectedListener(OnSelectedListener l) {
				mOnSelectedLis = l;
				return this;
			}

		};
		
		mMenuItems.put(itemId, item);
		mIsMenuItemChanged = true;
		
		return item;
	}

}
