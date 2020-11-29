package com.example.taskme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> tasksList = new ArrayList<String>();
    ArrayAdapter<String> tasksListAdapter;
    ListView tasksListView;

    TasksDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        tasksListView = (ListView) findViewById(R.id.tasksListView);
        tasksListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                tasksList);

        tasksListView.setAdapter(tasksListAdapter);

        // the db helper object
        helper = new TasksDbHelper(MainActivity.this);

    }
    // Real event
    public void newEvent(View view) {
        Intent intent = new Intent(this, CreateEvent.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 2){
            displayTasks();
        }
    }

    public void displayTasks(){
        Cursor data = helper.getTasks();

        if (data.getCount() > 0){
            while(data.moveToNext()){
                Log.d("cursordata", data.getString(1));
            }
        }

    }
}
