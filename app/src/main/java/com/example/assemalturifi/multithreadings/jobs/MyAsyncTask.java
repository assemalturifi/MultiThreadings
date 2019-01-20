package com.example.assemalturifi.multithreadings.jobs;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.assemalturifi.multithreadings.utils.ThreadsUtils;

//step25
public class MyAsyncTask extends AsyncTask<String,Integer,String> {
    //step26
    private static final String TAG = "MyAsyncTask";
    TextView tvMian;

    //step27
    public MyAsyncTask(TextView tvMian) {
        this.tvMian = tvMian;
    }

    //step28
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: " + ThreadsUtils.print(Thread.currentThread()));
        tvMian.setText("Task starting");
    }

    //step30
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: " + ThreadsUtils.print(Thread.currentThread()));
        Log.d(TAG, "onPostExecute: Result: " + s);

        tvMian.setText("Task completed");

    }
    //step29
    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: " + ThreadsUtils.print(Thread.currentThread()));

        Log.d(TAG, "doInBackground: Params: " + strings[0]);

//        try{
//            MyTask.start(5, Thread.currentThread().getName());
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }

        //my task
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            publishProgress(i);
        }

        return "Background task result";
    }

    //step31
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + ThreadsUtils.print(Thread.currentThread()));
        tvMian.setText(String.valueOf(values[0]));
    }
}
