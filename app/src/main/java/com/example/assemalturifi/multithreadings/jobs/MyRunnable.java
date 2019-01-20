package com.example.assemalturifi.multithreadings.jobs;

import android.os.Handler;

import com.example.assemalturifi.multithreadings.MyTask;
import com.example.assemalturifi.multithreadings.utils.HandlerUtils;

//step21
public class MyRunnable implements  Runnable{
    //step22
    int iterations;
    Handler handler;

    //step23
    public MyRunnable(int iterations, Handler handler) {
        this.iterations = iterations;
        this.handler = handler;
    }

    //step24
    @Override
    public void run() {
        HandlerUtils.sendMessage(handler,"Task starting");

        //start the task

        try{
            MyTask.start(iterations,Thread.currentThread().getName());
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        HandlerUtils.sendMessage(handler,"Task completed");

    }
}
