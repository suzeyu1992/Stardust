package com.szy.stardust.base;

import android.app.Application;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: suzeyu on 16/6/6 16:06
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  复写总application
 */
public class BaseApplication extends Application {
    private final String TAG = this.getClass().getSimpleName();
    public static BaseApplication application;//  程序运行 最早调用的方法
    private static int MainId;
    private static Handler handler;
    public static final String sp_config = "config";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        application = this;
        MainId = android.os.Process.myTid();
        System.out.println("BaseApplication:" + MainId);
        handler = new Handler();// 在主线程的Handler
        // 设置没有捕获异常的处理器
        //  当程序上线的时候 在添加没有捕获异常的处理器
//        Thread.currentThread().setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
    }


    public static Handler getHandler() {
        return handler;
    }

    private class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        //  当发生异常没有捕获   调用该方法
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            //  死前的遗言
            System.out.println("程序发生异常了,当时被哥发现了...");
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("'log_'yyyyMMdd_HH:mm:ss");
            File fileDir = new File(Environment.getExternalStorageDirectory() + "/tigger/");
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            File file = new File(Environment.getExternalStorageDirectory() + "/tigger/", (dateFormat.format(date) + ".txt"));
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    String crashReport = getCrashReport(ex);
                    Log.d(TAG,"全局捕捉的异常:" + ex.toString());

                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(crashReport.getBytes());
                    fileOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            ex.printStackTrace();// 把日志输出到控制台
            //ex.printStackTrace(err)  通过流写到文件中 ,   然后可以通过网络传输到服务器中
            // 自杀  杀死自己进程
            android.os.Process.killProcess(android.os.Process.myPid());
        }


    }

    public static int getMainId() {
        return MainId;
    }

    /**
     * 获取APP崩溃异常报告
     * @param ex
     * @return
     */
    private String getCrashReport(Throwable ex) {
        StringBuffer exceptionStr = new StringBuffer();


        exceptionStr.append("Exception:  " + ex.getMessage() + "\r\n"
        );
        StackTraceElement[] elements = ex.getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            exceptionStr.append(elements[i].toString() + "\r\n"
            );
        }
        return exceptionStr.toString();
    }
}
