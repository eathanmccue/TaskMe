package com.example.taskme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.MyViewHolder> {

    Context context;

    ArrayList<String> tasks_ids, task_name_list, task_description_list, date_list, time_list;

    TasksDbHelper tasksDbHelper;

    TasksListAdapter(Context context, ArrayList<String> tasks_ids, ArrayList<String> task_name_list, ArrayList <String> task_description_list, ArrayList <String> date_list, ArrayList <String> time_list
    ){
        this.context = context;
        this.tasks_ids = tasks_ids;
        this.task_name_list = task_name_list;
        this.task_description_list = task_description_list;
        this.date_list = date_list;
        this.time_list = time_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_item_row, parent, false);

        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        tasksDbHelper = new TasksDbHelper(holder.itemView.getContext());
        Log.d("isimportanttag", String.valueOf(position));
        if (tasksDbHelper.isImportant(Integer.parseInt(String.valueOf(tasks_ids.get(position)))))
            holder.imageView.setVisibility(View.VISIBLE);
        else{
            holder.imageView.setVisibility(View.GONE);
        }

        holder.task_name.setText(String.valueOf(task_name_list.get(position)));
        holder.task_description.setText(String.valueOf(task_description_list.get(position)));
        holder.date.setText(String.valueOf(date_list.get(position)));
        holder.time.setText(String.valueOf(time_list.get(position)));
    }

    @Override
    public int getItemCount() {
        return task_name_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView task_name, task_description, date, time;
        CardView cardView;
        ImageView imageView;
        TasksDbHelper tasksDbHelper;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.task_name);
            task_description = itemView.findViewById(R.id.task_decription);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.important_image);

            cardView = itemView.findViewById(R.id.card_view);
            cardView.setCardBackgroundColor(Color.parseColor("#B222212E")); // as we are using older api level
            cardView.setElevation(0);
        }
    }
}
