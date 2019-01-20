package com.example.assemalturifi.multithreadings;

import android.util.Log;

//step12
public class MyTask {
    public static final String TAG=MyTask.class.getSimpleName()+"_TAG";

    public static void start(int iterations, String thread) throws InterruptedException{
        Log.d(TAG,"start: Task starting on Thread: "+thread);

        for(int i=0;i<iterations;i++){

            Thread.sleep(500);
            Log.d(TAG,"start: itearations: "+i);

        }
        Log.d(TAG,"start: Task completed on Thread: "+thread);
    }
}
