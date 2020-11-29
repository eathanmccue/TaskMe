package com.example.taskme;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.MyViewHolder> {

    Context context;

    ArrayList<String> task_name_list, task_description_list, date_list, time_list;

    TasksListAdapter(Context context, ArrayList<String> task_name_list, ArrayList <String> task_description_list, ArrayList <String> date_list, ArrayList <String> time_list
    ){
        this.context = context;
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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.task_name);
            task_description = itemView.findViewById(R.id.task_decription);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

            cardView = itemView.findViewById(R.id.card_view);
            cardView.setCardBackgroundColor(Color.parseColor("#B222212E"));
            cardView.setElevation(0);
        }
    }
}
