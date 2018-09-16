package com.example.thebeast.asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    String[]clubs={"Chelsea","Manchester","Liverpool","Newcastle","Arsenal","Wignan",
                    "Everton","Mancity","Tohttenum","Bolton"};
    ProgressBar progressBar;
    ListView listView;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView=findViewById(R.id.id_list);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));

        myAsyncTask=new MyAsyncTask();
        myAsyncTask.execute();


    }


    class MyAsyncTask extends android.os.AsyncTask<String, String, String> {
        ArrayAdapter<String>arrayAdapter;

        @Override
        protected void onPreExecute() {
           arrayAdapter= (ArrayAdapter<String>) listView.getAdapter();//gets adapter from list view




        }

        @Override
        protected String doInBackground(String... strings) {

            for(String Clubs:clubs){

                publishProgress(Clubs);


                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            return "ALL clubs have been updated";
        }


        @Override
        protected void onProgressUpdate(String... values) {

            arrayAdapter.add(values[0]);


        }

        @Override
        protected void onPostExecute(String string) {
            Toast.makeText(getApplicationContext(), string,Toast.LENGTH_LONG).show();

        }

    }





}
