package com.example.taskme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    // Real event
    public void newEvent(View view) {
        Intent intent = new Intent(this, CreateEvent.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 2){
            tasksList.add("New item!");
            tasksListAdapter.notifyDataSetChanged();
        }
    }
}
