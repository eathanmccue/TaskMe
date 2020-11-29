package com.example.taskme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TasksDbHelper helper;
    ArrayList<String> task_name_list, task_description_list, date_list, time_list;
    TasksListAdapter tasksListAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        // the db helper object
        helper = new TasksDbHelper(MainActivity.this);
        task_name_list = new ArrayList<>();
        task_description_list = new ArrayList<>();
        date_list = new ArrayList<>();
        time_list = new ArrayList<>();

        recyclerView = findViewById(R.id.tasksListView);

        storeDataInArray();

    }

    // Real event
    public void newEvent(View view) {
        Intent intent = new Intent(this, CreateEvent.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 2){
            storeDataInArray();
        }
    }

    public void storeDataInArray(){
        Cursor data = helper.getTasks();

        task_name_list.clear();
        task_description_list.clear();
        date_list.clear();
        time_list.clear();

        if (data.getCount() > 0){
            while(data.moveToNext()){
                // create an array of tasks objects
                task_name_list.add(data.getString(1));
                task_description_list.add(data.getString(2));
                date_list.add(data.getString(3));
                time_list.add(data.getString(4));
            }
            inflateRecyclerView();
        }

    }
    private void inflateRecyclerView() {
        tasksListAdapter = new TasksListAdapter(this, task_name_list, task_description_list, date_list, time_list);
        recyclerView.setAdapter(tasksListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.bringToFront();
    }
}
