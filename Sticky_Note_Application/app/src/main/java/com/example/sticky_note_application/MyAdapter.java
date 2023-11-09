package com.example.sticky_note_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Notes> notes;

    public MyAdapter(Context context, List<Notes> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.noteview,parent,false);
//        MyViewHolder viewHolder = new MyViewHolder(view);
//        return viewHolder;
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.noteview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text_vw.setText(notes.get(position).text());
        holder.date_txt.setText(notes.get(position).date());
        holder.time_txt.setText(notes.get(position).time());
        holder.deadline_txt.setText(notes.get(position).deadline());
        holder.reminder_txt.setText(notes.get(position).reminder());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
