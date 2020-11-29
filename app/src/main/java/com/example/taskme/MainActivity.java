package com.example.taskme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TasksDbHelper helper;
    ArrayList<String> tasks_ids, task_name_list, task_description_list, date_list, time_list;
    TasksListAdapter tasksListAdapter;
    RecyclerView recyclerView;

    TextView no_tasks_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        // the db helper object
        helper = new TasksDbHelper(MainActivity.this);

        // arrays
        tasks_ids = new ArrayList<>();
        task_name_list = new ArrayList<>();
        task_description_list = new ArrayList<>();
        date_list = new ArrayList<>();
        time_list = new ArrayList<>();

        recyclerView = findViewById(R.id.tasksListView);
        no_tasks_found = findViewById(R.id.no_tasks_found);

        no_tasks_found.setVisibility(View.GONE);
        storeDataInArray();

    }

    // Click event to add new task
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

        // clear the arrays
        tasks_ids.clear();
        task_name_list.clear();
        task_description_list.clear();
        date_list.clear();
        time_list.clear();

        if (data.getCount() > 0){
            while(data.moveToNext()){

                // create an array of tasks objects
                tasks_ids.add(data.getString(0));
                task_name_list.add(data.getString(1));
                task_description_list.add(data.getString(2));
                date_list.add(data.getString(3));
                time_list.add(data.getString(4));

            }
            inflateRecyclerView();
        }
        else{
            no_tasks_found.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }
    private void inflateRecyclerView() {
        tasksListAdapter = new TasksListAdapter(this, tasks_ids, task_name_list, task_description_list, date_list, time_list);
        recyclerView.setAdapter(tasksListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.bringToFront();
    }
}
