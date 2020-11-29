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

        NewTaskDetails newTask = new NewTaskDetails(name.getText().toString(), description.getText().toString(), date.getText().toString(), time.getText().toString(), 1, 1 ,1,1 );

        helper.addTask(newTask);

        setResult(2);
        finish();
    }
}