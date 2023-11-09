package com.example.sticky_note_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskDoneAdapter extends RecyclerView.Adapter<TaskDoneViewHolder>{

    Context context;
    List<Notes> notes;

    public TaskDoneAdapter(Context context,List<Notes> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public TaskDoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskDoneViewHolder(LayoutInflater.from(context).inflate(R.layout.noteview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskDoneViewHolder holder, int position) {
        holder.text_vw.setText(notes.get(position).text());
        holder.date_txt.setText(notes.get(position).date());
        holder.time_txt.setText(notes.get(position).time());
        holder.deadline_vw.setText(notes.get(position).deadline());
        holder.reminder_vw.setText(notes.get(position).deadline());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
