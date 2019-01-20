package com.example.assemalturifi.multithreadings.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

//step16
public class HandlerUtils {

    public static void sendMessage(Handler handler, String value){

        Bundle bundle=new Bundle();
        Message message=new Message();
        bundle.putString("value","someVAlue");
        message.setData(bundle);
        handler.sendMessage(message);
    }


    public static String getString(Message message){
        return message.getData().getString("value");
    }
}
