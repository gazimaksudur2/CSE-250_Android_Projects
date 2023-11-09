package com.example.sticky_note_application;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskDoneViewHolder extends RecyclerView.ViewHolder{
    TextView text_vw,date_txt,time_txt,deadline_vw,reminder_vw;
    public TaskDoneViewHolder(@NonNull View itemView) {
        super(itemView);
        text_vw = itemView.findViewById(R.id.notes);
        date_txt = itemView.findViewById(R.id.date);
        time_txt = itemView.findViewById(R.id.time);
        deadline_vw = itemView.findViewById(R.id.deadline);
        reminder_vw = itemView.findViewById(R.id.reminder);
    }

}
