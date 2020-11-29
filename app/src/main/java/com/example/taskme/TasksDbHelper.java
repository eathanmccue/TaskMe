package com.example.taskme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TasksDbHelper extends SQLiteOpenHelper {
    // db info
    private static final String DATABASE_NAME = "TasksDb";
    private static final int DATABASE_VERSION = 1;

    // table name
    private static final String TABLE_NAME = "tasks";

    // Other
    Cursor data;
    SQLiteDatabase db;

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
                "is_important INT)";

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
        contentValues.put("is_important", newTaskDetails.is_important);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getTasks(){
        db = this.getReadableDatabase();
        data = db.rawQuery("SELECT * FROM tasks", null);
        return data;

    }

    public boolean isImportant(int id){
        db = this.getReadableDatabase();
        data = db.rawQuery("SELECT * FROM tasks where id = "+id, null);


        return data.getString(7).equals("1");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }
}
