package com.example.taskme;

public class NewTaskDetails {
    public String name, desc, date, time;
    public int is_important, is_goal, is_repeat, is_reminder;

    public  NewTaskDetails(String name, String desc, String date, String time, int is_reminder, int is_repeat, int is_important, int is_goal){
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.is_repeat = is_repeat;
        this.is_reminder = is_reminder;
        this.is_important = is_important;
        this.is_goal = is_goal;
    }
}
