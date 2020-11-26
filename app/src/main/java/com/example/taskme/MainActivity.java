package com.example.taskme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> tasksList = new ArrayList<String>();
    ArrayAdapter<String> tasksListAdapter;

    ListView tasksListView;
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

    }

    public void newEvent(View view) {
        Intent intent = new Intent(this, CreateEvent.class);
        startActivity(intent);
    }

    public void addEvent(View view) {
        tasksList.add("New item!");
        tasksListAdapter.notifyDataSetChanged();
    }
}
