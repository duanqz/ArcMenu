package org.xo;

import org.xo.arcmenu.ArcMenuFactory;
import org.xo.arcmenu.ArcMenuItem;
import org.xo.arcmenu.ArcMenuItem.OnSelectedListener;
import org.xo.arcmenu.PopupArcMenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ArcMenuActivity extends Activity {

	private static final String TAG = "ArcMenuActivity";
	private TextView mText;
	private Button mBtn1, mBtn2;
	private PopupArcMenu mArcMenu1, mArcMenu2;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mText = (TextView) findViewById(R.id.text);
        
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				createArcMenuIfNull();
	    		mArcMenu1.dismiss();
	    		mArcMenu2.dismiss();
	    		int location[] = new int[2];
	    		v.getLocationOnScreen(location);
	    		mArcMenu1.showAtLocation(mBtn1, location[0], location[1]);
				return true;
			}
		});
        
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				createArcMenuIfNull();
				mArcMenu1.dismiss();
	    		mArcMenu2.dismiss();
	    		int location[] = new int[2];
	    		v.getLocationOnScreen(location);
	    		mArcMenu2.showAtLocation(mBtn2, location[0], location[1]);
			}
        	
        });
    }
    
	private void createArcMenuIfNull() {
    	if(mArcMenu1 == null) {
	    	mArcMenu1 = ArcMenuFactory.createPopupArcMenu(this);
	    	OnSelectedListener l = new ArcMenuItemSelectedListener();
			mArcMenu1.add(R.string.edit).setOnSelectedListener(l);
			mArcMenu1.add(R.string.delete).setOnSelectedListener(l);
			mArcMenu1.add(R.string.reset);
			mArcMenu1.add(R.string.cancel).setOnSelectedListener(l);
			mArcMenu1.setTouchForward(true);
			Log.d(TAG, "Creat a new arc menu");
    	}
    	
    	if(mArcMenu2 == null) {
    		mArcMenu2 = ArcMenuFactory.createPopupArcMenu(this);
	    	OnSelectedListener l = new ArcMenuItemSelectedListener();
			mArcMenu2.add("更多...").setOnSelectedListener(l);
			mArcMenu2.add("妹妹思").setOnSelectedListener(l);
			mArcMenu2.add("哥哥想").setOnSelectedListener(l);
			Log.d(TAG, "Creat a new arc menu");
    	}
    }
    
    private class ArcMenuItemSelectedListener implements OnSelectedListener {

		@Override
		public void onSelected(ArcMenuItem item) {
			mText.setText(item.getTitle() + " is selected");
		}
    	
    }
}
