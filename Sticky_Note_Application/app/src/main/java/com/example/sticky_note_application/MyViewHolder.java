package com.example.sticky_note_application;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView text_vw,date_txt,time_txt,deadline_txt,reminder_txt;
    Button btn_done;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        text_vw = itemView.findViewById(R.id.notes);
        date_txt = itemView.findViewById(R.id.date);
        time_txt = itemView.findViewById(R.id.time);
        deadline_txt = itemView.findViewById(R.id.deadline);
        reminder_txt = itemView.findViewById(R.id.reminder);

        btn_done = itemView.findViewById(R.id.id_btn_done_task);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Notes note = new Notes(time_txt.getText().toString(),text_vw.getText().toString(),date_txt.getText().toString(),deadline_txt.getText().toString(),reminder_txt.getText().toString());
//                TaskDoneActivity.add_notes(note);
//                MainActivity.del_notes(note);
            }
        });
    }
}
