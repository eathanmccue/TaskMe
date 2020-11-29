package com.example.taskme;

public class NewTaskDetails {
    public String name, desc, date, time;
    public int is_important;

    public  NewTaskDetails(String name, String desc, String date, String time, int is_important){
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.is_important = is_important;

    }
}
