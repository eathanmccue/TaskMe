package com.example.taskme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CreateEvent extends AppCompatActivity {

    public EditText name, description, date, time;
    CheckBox checkBox;
    TasksDbHelper helper;
    ImageButton imageButton;
    int task_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.create_event);


        name = findViewById(R.id.createName);
        description = findViewById(R.id.createDescription);
        date = findViewById(R.id.createDate);
        time = findViewById(R.id.createTime);
        checkBox = findViewById(R.id.checkbox);
        imageButton = findViewById(R.id.img_btn);



        imageButton.setVisibility(View.GONE); // not shown by default

        // the db helper object
        helper = new TasksDbHelper(CreateEvent.this);


        Intent intent = getIntent();
        task_id = intent.getIntExtra("taskId", -1);

        if (task_id != -1){
            // now display the data inside the fields
            displayData(task_id);
        }

    }

    private void displayData(int task_id) {
        // call the method from the db
        Cursor data = helper.getSingleTask(task_id);
        String tn = data.getString(1);
        String td = data.getString(2);
        String da = data.getString(3);
        String ti = data.getString(4);
        String imp = data.getString(5);

        name.setText(tn);description.setText(td);date.setText(da);time.setText(ti);
        if (imp.equals("1"))
            checkBox.setChecked(true);
        else
            checkBox.setChecked(false);

        Button btn = findViewById(R.id.btn);
        btn.setText("Update");
        imageButton.setVisibility(View.VISIBLE);

    }

    public void saveButton(View view) {


        if (name.getText().toString().trim().isEmpty() || description.getText().toString().trim().isEmpty() || date.getText().toString().trim().isEmpty() || time.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "You must fill up all fields!", Toast.LENGTH_LONG).show();
            return;
        }

        if(name.getText().toString().length() > 30){
            Toast.makeText(this, "Title is too long!", Toast.LENGTH_LONG).show();
            return;
        }
        int is_important = 0;

        if (checkBox.isChecked()){
            is_important = 1;
        }


        if (task_id != -1){
            //update
            helper.updateTask(task_id, name.getText().toString(), description.getText().toString(), date.getText().toString(), time.getText().toString(), String.valueOf(is_important));
            Toast.makeText(this, "Task was successfully updated!", Toast.LENGTH_LONG).show();
        }
        else{
            // insert
            NewTaskDetails newTask = new NewTaskDetails(name.getText().toString(), description.getText().toString(), date.getText().toString(), time.getText().toString(), is_important);

            helper.addTask(newTask);
        }


        setResult(2);
        finish();
    }

    public void deleteTask(View view) {
        if (helper.deleteTask(task_id)){
            Toast.makeText(this, "Task was deleted successfully!", Toast.LENGTH_LONG).show();
            setResult(2);
            finish();
        }

    }

}