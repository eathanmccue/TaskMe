package com.example.taskme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CreateEvent extends AppCompatActivity {

    public EditText name, description, date, time;
    TasksDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_event);

        // the db helper object
        helper = new TasksDbHelper(CreateEvent.this);
    }

    public void saveButton(View view) {
        name = findViewById(R.id.createName);
        description = findViewById(R.id.createDescription);
        date = findViewById(R.id.createDate);
        time = findViewById(R.id.createTime);

        NewTaskDetails newTask = new NewTaskDetails();
        newTask.name = name.getText().toString();
        newTask.desc = description.getText().toString();
        newTask.date = date.getText().toString();
        newTask.time = time.getText().toString();

        newTask.is_goal = 1;
        newTask.is_important = 1;
        newTask.is_reminder = 1;
        newTask.is_repeat = 1;

        helper.addTask(newTask);

        setResult(2);
        finish();
    }
}