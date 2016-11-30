package com.qust.calltip;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * @author qzyd 2013-7-8 上午11:05:03 功能:设置来电弹屏移动位置
 */
public class MoveRelativeLayout extends RelativeLayout {

	private float x;
	private float y;
	private float startX;
	private float startY;
	private WindowManager.LayoutParams params = null;
	private WindowManager wm = (WindowManager) getContext().getApplicationContext().getSystemService("window");

	public void setParames(WindowManager.LayoutParams params) {
		this.params = params;
	}

	/**
	 * @param context
	 */
	public MoveRelativeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 相对于屏幕坐标
		x = event.getRawX();
		y = event.getRawY() - 25;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			updateViewPosition();
			break;
		case MotionEvent.ACTION_UP:
			updateViewPosition();
			startX = startY = 0;
			break;

		}
		return true;
	}

	// 更新位置窗口参数
	private void updateViewPosition() {
		try {
			if (null != wm && null != params) {
				params.x = (int) (x - startX);
				params.y = (int) (y - startY);
				wm.updateViewLayout(this, params);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(VIEW_LOG_TAG, "==== 更新来电弹屏位置失败 ======");
		}
	}
}
