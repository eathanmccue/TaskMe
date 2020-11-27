package com.example.taskme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateEvent extends AppCompatActivity {

    public String name, description, date, time;
    TasksDbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_event);

        // the db helper object
        helper = TasksDbHelper.getsInstance(this);
    }

    public void saveButton(View view) {
        name = findViewById(R.id.createName).toString();
        description = findViewById(R.id.createDescription).toString();
        date = findViewById(R.id.createDate).toString();
        time = findViewById(R.id.createTime).toString();

        NewTaskDetails newTask = new NewTaskDetails();
        newTask.name = name;
        newTask.desc = description;
        newTask.date = date;
        newTask.time = time;

        newTask.is_goal = 1;
        newTask.is_important = 1;
        newTask.is_reminder = 1;
        newTask.is_repeat = 1;

        helper.addTask(newTask);

        setResult(2);
        finish();
    }
}