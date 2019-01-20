package com.example.assemalturifi.multithreadings.jobs;

import com.example.assemalturifi.multithreadings.MyMessageEvent;

import org.greenrobot.eventbus.EventBus;

//step32
public class MyEventHandler extends Thread {

    @Override
    public void run() {
        super.run();
        MyMessageEvent myMassageEvent = new MyMessageEvent("Default data");

        EventBus.getDefault().post(myMassageEvent);

        //task running

        myMassageEvent.setData("Task Running");
        EventBus.getDefault().post(myMassageEvent);

        //task completed

        myMassageEvent.setData("Task completed");
        EventBus.getDefault().post(myMassageEvent);
    }
}
