package com.example.taskme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateEvent extends AppCompatActivity {

    public EditText name, description, date, time;
    TasksDbHelper helper;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.create_event);

        cardView = findViewById(R.id.card_view);
        cardView.setCardBackgroundColor(Color.parseColor("#86161522"));
        cardView.setElevation(0);

        // the db helper object
        helper = new TasksDbHelper(CreateEvent.this);
    }

    public void saveButton(View view) {
        name = findViewById(R.id.createName);
        description = findViewById(R.id.createDescription);
        date = findViewById(R.id.createDate);
        time = findViewById(R.id.createTime);

        if (name.getText().toString().trim().isEmpty() || description.getText().toString().trim().isEmpty() || date.getText().toString().trim().isEmpty() || time.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "You must fill up all fields!", Toast.LENGTH_LONG).show();
            return;
        }

        NewTaskDetails newTask = new NewTaskDetails(name.getText().toString(), description.getText().toString(), date.getText().toString(), time.getText().toString(), 1 );

        helper.addTask(newTask);

        setResult(2);
        finish();
    }
}