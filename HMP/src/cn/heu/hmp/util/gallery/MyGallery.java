package cn.heu.hmp.util.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class MyGallery extends Gallery {
	public MyGallery(Context context) {
		super(context);
	}
	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
		//����false ���Gallery��ק��������
		return false;
	}
	@Override
	public void setUnselectedAlpha(float unselectedAlpha) {
		unselectedAlpha = 1.0f;
		super.setUnselectedAlpha(unselectedAlpha);
	}
	
	public int getselection(){
		return super.getSelectedItemPosition();
	}
	
	
}
