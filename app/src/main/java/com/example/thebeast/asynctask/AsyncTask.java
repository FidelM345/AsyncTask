package com.example.thebeast.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncTask extends AppCompatActivity implements View.OnClickListener {
    Button start, stop;
    TextView counter;
    static final String TAG = MainActivity.class.getSimpleName();
    boolean stopLoop;
    int count = 0;

    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask);

        start = findViewById(R.id.id_start);
        stop = findViewById(R.id.id_stop);
        counter = findViewById(R.id.id_counter);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.id_start:


                stopLoop = true;

                myAsyncTask=new MyAsyncTask();
                myAsyncTask.execute(count);


                break;

            case R.id.id_stop:
               // stopLoop = false;
                myAsyncTask.cancel(true);
                break;

        }



    }


    class MyAsyncTask extends android.os.AsyncTask<Integer, Integer, Integer> {
        int customCounter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            customCounter = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {

            customCounter = integers[0];

            while (stopLoop) {

                try {

                    Thread.sleep(1000);
                    customCounter++;
                    publishProgress(customCounter);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if(isCancelled()){
                    break;
                }

            }


            return customCounter;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            counter.setText("" + values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            counter.setText("" + integer);
            count = integer;

        }

       /* @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);

            counter.setText("" + integer);
        }*/
    }
}