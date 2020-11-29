package com.example.taskme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TasksDbHelper extends SQLiteOpenHelper {
    // db info
    private static final String DATABASE_NAME = "TasksDb";
    private static final int DATABASE_VERSION = 1;

    // instance
    private static TasksDbHelper sInstance;

    // table name
    private static final String TABLE_NAME = "tasks";

    public TasksDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                "task_name TEXT, " +
                "task_desc TEXT,  " +
                "date TEXT,  " +
                "time TEXT,  " +
                "is_reminder INT,  " +
                "is_repeat INT,  " +
                "is_important INT,  " +
                "is_goal INT)";

        db.execSQL(create_table);
    }

    // not the methods to insert, delete data from table.
    public void addTask(NewTaskDetails newTaskDetails){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("task_name", newTaskDetails.name);
        contentValues.put("task_desc", newTaskDetails.desc);
        contentValues.put("date", newTaskDetails.date);
        contentValues.put("time", newTaskDetails.time);
        contentValues.put("is_goal", newTaskDetails.is_goal);
        contentValues.put("is_important", newTaskDetails.is_important);
        contentValues.put("is_reminder", newTaskDetails.is_reminder);
        contentValues.put("is_repeat", newTaskDetails.is_repeat);
        contentValues.put("is_goal", newTaskDetails.is_goal);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getTasks(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM tasks", null);
        return data;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }
}
