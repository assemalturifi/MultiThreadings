package com.example.assemalturifi.multithreadings.jobs;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import com.example.assemalturifi.multithreadings.MyMessageEvent;
import com.example.assemalturifi.multithreadings.MyTask;
import com.example.assemalturifi.multithreadings.utils.ThreadsUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//step9
public class MyThread extends Thread{
    //step10
    private static final String TAG = "MyThread";

    int iterations;
    Handler handler = new Handler(Looper.getMainLooper());
    TextView tvMain;

    //step11
    public MyThread(int iterations, TextView tvMain) {
        this.iterations = iterations;
        this.tvMain = tvMain;
    }

    //step13
    @Override
    public void run() {
        super.run();

        //step14
        //start the task
        try{
            MyTask.start(iterations, Thread.currentThread().getName());

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }


        //step17
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: " + ThreadsUtils.print((currentThread())));
                tvMain.setText("Update from myThread class");

            }
        });

        //step18
        //use post delayed
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Log.d(TAG, "run: " + ThreadsUtils.print((currentThread())));
                tvMain.setText("Delayed Update");
            }
        },2000);


    }
    //step20
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(MyMessageEvent myMassageEvent){
        Log.d(TAG,"onMessageEvent: "+ThreadsUtils.print(Thread.currentThread()));
        Log.d(TAG,"onMessageEvent: "+ myMassageEvent.getData());

        EventBus.getDefault().unregister(this);
    }


}
