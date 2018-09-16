package com.example.thebeast.asynctask;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button start,stop,async,list;
    TextView counter;
    static final String TAG=MainActivity.class.getSimpleName();
    boolean stopLoop;
    int count=0;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.id_start);
        stop=findViewById(R.id.id_stop);
        counter=findViewById(R.id.id_counter);
        async=findViewById(R.id.id_async);
        list=findViewById(R.id.id_list);
        handler=new Handler(getApplicationContext().getMainLooper());// handler gets access to the main looper or ui thread or message queue



        Log.i(TAG,"Thread id: "+Thread.currentThread().getId());

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        async.setOnClickListener(this);
        list.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.id_start:


                stopLoop=true;


                Thread thread=new Thread(new startThread());

                thread.start();




/*
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        while (stopLoop){

                            try {

                                Thread.sleep(1000);
                                count++;



                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                            Log.i(TAG,"Thread count: "+Thread.currentThread().getId()+" Count:"+count);



                        }



                    }
                }).start();*/


                break;

            case R.id.id_stop:
              stopLoop=false;
                break;

            case R.id.id_async:

                Intent intent=new Intent(getApplicationContext(),AsyncTask.class);
                startActivity(intent);

                break;


            case R.id.id_list:

                Intent intent2=new Intent(getApplicationContext(),List.class);
                startActivity(intent2);

                break;


        }
    }


    class startThread implements Runnable{

        @Override
        public void run() {

            while (stopLoop){

                try {

                    Thread.sleep(1000);
                    count++;



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        counter.setText("number: "+count);

                    }
                });


                Log.i(TAG,"Thread count: "+Thread.currentThread().getId()+" Count:"+count);


            }


        }
    }
}
