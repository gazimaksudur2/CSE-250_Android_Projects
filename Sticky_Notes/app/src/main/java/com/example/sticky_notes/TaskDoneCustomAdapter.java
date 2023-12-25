package com.example.sticky_notes;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskDoneCustomAdapter extends RecyclerView.Adapter<TaskDoneCustomAdapter.MyViewHolder_task> {

    private Context context;
    private Activity activity;
    private ArrayList note_id, note_date, note_time, note_txt, note_deadline, note_reminder;
    static String a,b,c,d,e,f;

    TaskDoneCustomAdapter(Activity activity, Context context, ArrayList note_id, ArrayList note_date, ArrayList note_time,
                          ArrayList note_txt, ArrayList note_deadline, ArrayList note_reminder){
        this.activity = activity;
        this.context = context;
        this.note_id = note_id;
        this.note_date = note_date;
        this.note_time = note_time;
        this.note_txt = note_txt;
        this.note_deadline = note_deadline;
        this.note_reminder = note_reminder;
    }
    @NonNull
    @Override
    public MyViewHolder_task onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.noteview_task_done, parent, false);

        return new MyViewHolder_task(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder_task holder, final int position) {
        holder.note_date_txt.setText(String.valueOf(note_date.get(position)));
        holder.note_time_txt.setText(String.valueOf(note_time.get(position)));
        holder.note_passage_txt.setText(String.valueOf(note_txt.get(position)));
        holder.note_deadline_txt.setText(String.valueOf(note_deadline.get(position)));
        holder.note_reminder_txt.setText(String.valueOf(note_reminder.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = String.valueOf(note_id.get(position));
                b = String.valueOf(note_date.get(position));
                c = String.valueOf(note_time.get(position));
                d = String.valueOf(note_txt.get(position));
                e = String.valueOf(note_deadline.get(position));
                f = String.valueOf(note_reminder.get(position));
                confirmDialog1();
            }
        });
    }


    void confirmDialog1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Action on existing Note");
        builder.setMessage("What to do....?");
        builder.setPositiveButton("Re-Stick", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper.done_id.remove(a);
                activity.recreate();
                Toast.makeText(context, "Re-sticked id --> "+a, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                confirmDelete();
            }
        });
        builder.create().show();
    }

    void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete " + d + " ?");
        builder.setMessage("Are you sure you want to delete " + d + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                myDB.deleteOneRow(a);
                MyDatabaseHelper.done_id.remove(a);
                Toast.makeText(context, "deleted id --> "+a, Toast.LENGTH_SHORT).show();
                activity.recreate();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Nothing Deleted!!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return note_id.size();
    }


    class MyViewHolder_task extends RecyclerView.ViewHolder {

        TextView note_id_txt, note_date_txt, note_time_txt, note_passage_txt, note_deadline_txt, note_reminder_txt;
        LinearLayout mainLayout;

        MyViewHolder_task(@NonNull View itemView) {
            super(itemView);
            note_date_txt = itemView.findViewById(R.id.date);
            note_time_txt = itemView.findViewById(R.id.time);
            note_passage_txt = itemView.findViewById(R.id.notes);
            note_deadline_txt = itemView.findViewById(R.id.deadline);
            note_reminder_txt = itemView.findViewById(R.id.reminder);
            mainLayout = itemView.findViewById(R.id.mainLayout_task_done);

            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anime);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
