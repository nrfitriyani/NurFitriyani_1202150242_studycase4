package org.nurfitriani.studycase4.nurfitriani_1202150242_studycase4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] names = {"Dina","Arla","Lutfi","Sulaiman","Raja","Bethari","Kusuma","Mega","Agung","Batari","Kasih"
            ,"Yuliana","Wayan","Sulaiman","Sari","Raja","Guntur","Widya","Krisna","Yohanes","Buana","Eko","Indah","Brosni"}; //data
    private ListView listView;
    private Button startAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAsyncTask = (Button)findViewById(R.id.button); //initial Button
        listView = (ListView)findViewById(R.id.listView); //initial list

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));

        startAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //click trigger
                new MyTask().execute(); //call method
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // what's going on the menu is in this method
        MenuInflater menuInflater = getMenuInflater(); //inflate the menu to the activity
        menuInflater.inflate(R.menu.imagesearch_menu,menu); //initial the menu

        MenuItem item = menu.findItem(R.id.search_menu); //initial the button, in the menu
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) { //when the menu is clicked.
                Intent intent = new Intent(getApplicationContext(),SearchImageActivity.class); // Go to another class
                startActivity(intent);
                return true;
            }
        });

        return true;
    }

    private class MyTask extends AsyncTask<Void,String,String> { //to load the list data.

        ArrayAdapter<String> adapter;
        ProgressBar progressBar;
        int count;
        private ProgressDialog progressDialog;

        @Override
        protected String doInBackground(Void... voids) { //when the AsyncTask loaded
            for (String Name: names){ //load the String data above
                publishProgress(Name);
                try { //don't worry about this. This handle when the for fails
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "All the names were added Succesfully"; //if the data all loaded, return this message
        }

        @Override
        protected void onPreExecute() { //before the AsyncTask Executed
            adapter = (ArrayAdapter<String>)listView.getAdapter();
            progressBar = (ProgressBar)findViewById(R.id.progressbar); //initial progress bar.
            progressBar.setMax(16);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.VISIBLE);
            count = 0;

            progressDialog = new ProgressDialog(MainActivity.this); //initial the progress dialog.
            progressDialog.setTitle("Memuat Data");
            progressDialog.setCancelable(true);
            progressDialog.show();//show the progress dialog
        }

        @Override
        protected void onProgressUpdate(String... values) { //when the data updated
            adapter.add(values[0]);
            count++;
            progressBar.setProgress(count); //to make a progress di progressBar
            float percentage = (count/names.length)*100; //calculation of the percentage
//            Log.d("Count: ", String.valueOf(count));
            progressDialog.setMessage("sedang memuat.. " + percentage+ " %"); //percentage for progressDialognya (loading..)
        }

        @Override
        protected void onPostExecute(String result) { //after the data executed
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show(); //initial a quick little message
            progressBar.setVisibility(View.GONE); //progressbar disappear

            progressDialog.hide();
        }
    }
}
