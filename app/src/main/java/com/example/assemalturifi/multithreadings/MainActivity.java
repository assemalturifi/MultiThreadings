package com.example.assemalturifi.multithreadings;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.assemalturifi.multithreadings.jobs.MyAsyncTask;
import com.example.assemalturifi.multithreadings.jobs.MyEventHandler;
import com.example.assemalturifi.multithreadings.jobs.MyRunnable;
import com.example.assemalturifi.multithreadings.jobs.MyThread;
import com.example.assemalturifi.multithreadings.utils.HandlerUtils;
import com.example.assemalturifi.multithreadings.utils.ThreadsUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


//step3
public class MainActivity extends AppCompatActivity implements Handler.Callback{
    private static final String TAG = "MainActivity";

    //step1
    private TextView tvMain;
    private Handler handler;
    private String b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step2
        tvMain = findViewById(R.id.textView);

        //step4
        handler = new Handler(this);

    }
    //step8
    private void printCurrentThread(){
        Log.d(TAG, "onThread:Current Thread: "+Thread.currentThread().getName());
    }

    //step33
    public void onThread(View view) {
        ThreadsUtils.print(Thread.currentThread());

        MyThread myThread = new MyThread(5, tvMain);
        myThread.start();


    }

    //step34
    public void onRunnable(View view) {
        ThreadsUtils.print(Thread.currentThread());

        MyRunnable myRunnable = new MyRunnable(5,handler);
        Thread thread = new Thread(myRunnable);
        thread.start(); //created thread, and runnable runs in it
        //myRunnable.run() would run on the currently running thread (this time it's main)


    }

    //step35
    public void onAsynctask(View view) {
        ThreadsUtils.print(Thread.currentThread());
        MyAsyncTask myAsyncTask = new MyAsyncTask(tvMain);
        myAsyncTask.execute("Initial params from activity");
    }

    //step37
    public void onStartEvent(View view) {
        MyEventHandler myEventHandler=new MyEventHandler();
        myEventHandler.start();
    }

    //step5
    @Override
    protected void onStart() {
        super.onStart();

        //step38
        EventBus.getDefault().register(this);
    }

    //step6
    @Override
    protected void onStop() {
        super.onStop();
        //step39
        EventBus.getDefault().unregister(this);
    }

    //step7
    //thread class
    @Override
    public boolean handleMessage(Message msg) {
        //step36
        tvMain.setText(HandlerUtils.getString(msg));

        return false;
    }

    //step40
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyMessageEvent myMessageEVent){
        tvMain.setText(myMessageEVent.getData());
    }


}
