package com.example.taskme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateEvent extends AppCompatActivity {

    public EditText name;
    public EditText description;
    public EditText date;
    public EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_event);
    }

    public void saveButton(View view) {
        name = findViewById(R.id.createName);
        description = findViewById(R.id.createDescription);
        date = findViewById(R.id.createDate);
        time = findViewById(R.id.createTime);

        finish();
    }
}