package com.example.taskme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TasksDbHelper helper;
    ArrayList<String> tasks_ids, task_name_list, task_description_list, date_list, time_list;
    TasksListAdapter tasksListAdapter;
    RecyclerView recyclerView;
    TextView monthTxt, yearTxt, sDay, sMonth, sYear;
    TextView no_tasks_found;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        Calendar cal = Calendar.getInstance();
        int yearString = cal.get(Calendar.YEAR);
        String monthString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

        int selectedDay = cal.get(Calendar.DAY_OF_MONTH);
        int selectedMonth = cal.get(Calendar.MONTH);
        int selectedYear = cal.get(Calendar.YEAR);


        // the db helper object
        helper = new TasksDbHelper(MainActivity.this);

        // arrays
        tasks_ids = new ArrayList<>();
        task_name_list = new ArrayList<>();
        task_description_list = new ArrayList<>();
        date_list = new ArrayList<>();
        time_list = new ArrayList<>();

        recyclerView = findViewById(R.id.tasksListView);
        yearTxt = findViewById(R.id.get_year);
        monthTxt = findViewById(R.id.get_month);
        sDay = findViewById(R.id.selected_day);
        sMonth = findViewById(R.id.selected_month);
        sYear = findViewById(R.id.selected_year);
        no_tasks_found = findViewById(R.id.no_tasks_found);
        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                sDay.setText(String.valueOf(dayOfMonth));
                sMonth.setText(String.valueOf(month));
                sYear.setText(String.valueOf(year));
            }
        });

        no_tasks_found.setVisibility(View.GONE);

        monthTxt.setText(monthString);
        yearTxt.setText(String.valueOf(yearString));

        sDay.setText(String.valueOf(selectedDay));
        sMonth.setText(String.valueOf(selectedMonth));
        sYear.setText(String.valueOf(selectedYear));

        storeDataInArray();
    }

    // Click event to add new task
    public void newEvent(View view) {
        String sday = sDay.getText().toString();
        String smonth = sMonth.getText().toString();
        String syear = sYear.getText().toString();

        Intent intent = new Intent(this, CreateEvent.class);
        intent.putExtra("sday",sday);
        intent.putExtra("smonth",smonth);
        intent.putExtra("syear",syear);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 2){
            Log.d("resultcode", String.valueOf(resultCode));
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
            if (recyclerView.getVisibility() != View.VISIBLE){
                no_tasks_found.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }
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
        tasksListAdapter = new TasksListAdapter(this, tasks_ids, task_name_list, task_description_list, date_list, time_list, recyclerView);
        recyclerView.setAdapter(tasksListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.bringToFront();
    }

    @Override
    protected void onResume() {
        super.onResume();
        storeDataInArray();
    }
}
