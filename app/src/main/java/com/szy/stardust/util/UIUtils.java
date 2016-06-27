package com.szy.stardust.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;


import com.szy.stardust.base.BaseApplication;
import com.szy.stardust.define.GeneralStatusCode;

import java.text.SimpleDateFormat;

public class UIUtils {
	private static String TAG = UIUtils.class.getName();
	/**
	 * 获取字符串数组
	 * 
	 * @param id
	 * @return
	 */
	public static String[] getStringArray(int id) {
		return getResource().getStringArray(id);
	}

	public static Resources getResource() {
		return getContext().getResources();
	}

	/** dip转换px */
	public static int dip2px(int dip) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

	/** px转换dip */

	public static int px2dip(int px) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	/**
	 * 获取上下文
	 * 
	 * @return
	 */
	public static Context getContext() {
		return BaseApplication.application;
	}

	/**
	 * 获取资源目录的颜色
	 * 
	 * @param id
	 * @return
	 */
	public static int getColor(int id) {
		return getResource().getColor(id);
	}

	public static View inflate(int resource) {
		return View.inflate(getContext(), resource, null);
	}

	public static void runOnUiThread(Runnable runnable) {
		// 当前线程的id
		int myTid = android.os.Process.myTid();
		System.out.println("UIUtils:" + myTid);
		// 判断是否在主线程中
		if (myTid == BaseApplication.getMainId()) {
			runnable.run();
		} else {
			// 获取Handler
			Handler handler = BaseApplication.getHandler();
			handler.post(runnable);
		}
	}
	
	public static String setDataSimple(String replaceTime){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long parseLong = Long.parseLong(replaceTime);
		
		String format = simpleDateFormat.format(parseLong);
		return format;
	}


	/**
	 * 获取屏幕宽高
     */
	public static DisplayMetrics getCurrentDisplayMetrics(){
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResource().getDisplayMetrics();
		float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
		int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
		float xdpi = dm.xdpi;
		float ydpi = dm.ydpi;
		Log.e(TAG + " DisplayMetrics", "xdpi=" + xdpi + "; ydpi=" + ydpi);
		Log.e(TAG + " DisplayMetrics", "density=" + density + "; densityDPI=" + densityDPI);
		int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
		int screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）
		Log.e(TAG + " DisplayMetrics() ", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);

		return dm;
	}



	public static void startActivity(Activity context,Class<?> className){
		startActivity(context,className,-1,null,false);
	}
	public static void startActivity(Class<?> className,boolean newTask){
		startActivity(null,className,-1,null,newTask);
	}
	public static void startActivityForResult(Activity context ,Class<?> className){
		startActivity(context,className,GeneralStatusCode.DEFAULT_CODE,null,false);
	}
	public static void startActivityForResult(Activity context ,Class<?> className,int requestCode){
		startActivity(context,className,requestCode,null,false);
	}
	public static void startActivityForResult(Activity context ,Class<?> className,int requestCode,Bundle bundle){
		startActivity(context,className,requestCode,bundle,false);
	}




	/**
	 * 开启activity  并进行数据的传递
	 * @param activity		上下文对象
	 * @param className	    要打开的类的字节码
	 * @param requestCode 	打开activity是发送的请求码
	 * @param bundle		新打开的界面需要传递的数据
	 * @param newTask		是否需要新建任务栈
	 */
	private static void startActivity(Activity activity, Class<?> className, int requestCode , Bundle bundle,Boolean newTask) {



		Intent intent = new Intent();
		//判断是否需要新建任务栈
		if (!newTask) {
			if (activity == null){
				throw new IllegalArgumentException("Activity参数不能为null");
			}
			intent.setClass(getContext(), className);

			//判断是否有需要传递的bundle数据
			if (null != bundle){
				intent.putExtras(bundle);
			}

			//判断打开类型是否需要返回值
			if( requestCode < GeneralStatusCode.DEFAULT_CODE){
				//不在意返回结果 直接打开
				activity.startActivity(intent);
			}else {
				//在意结果  需要startActivityForResult打开
				activity.startActivityForResult(intent, requestCode);
			}

		} else {
			// 在服务或者广播中调用 原因除了Activity 默认没有任务栈
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setClass(getContext(), className);
			getContext().startActivity(intent);
		}
	}


}
