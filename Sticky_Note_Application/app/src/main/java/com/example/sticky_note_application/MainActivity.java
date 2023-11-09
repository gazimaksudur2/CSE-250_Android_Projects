package com.example.sticky_note_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    private Button add_task, done_task;
    static List<Notes> notes = new ArrayList<Notes>();

    private static MyAdapter adapter;
//
//    static {
//        notes.add(new Notes("Time01","Note01","Date01","Deadline01","Reminder01"));
//        notes.add(new Notes("Time02","Note02","Date02","Deadline02","Reminder02"));
//        notes.add(new Notes("Time03","Note03","Date03","Deadline03","Reminder03"));
//        notes.add(new Notes("Time04","Note04","Date04","Deadline04","Reminder04"));
//        notes.add(new Notes("Time05","Note05","Date05","Deadline05","Reminder05"));
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_task = findViewById(R.id.btn_add_task);
        done_task = findViewById(R.id.id_btn_done_task_page);

        RecyclerView recyclerView = findViewById(R.id.userRecyclerView);

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
                adapter.notifyItemInserted(notes.size()-1);
//                recyclerView.smoothScrollToPosition(notes.size() - 1);
            }
        });

        done_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskDoneActivity();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this,notes);
        recyclerView.setAdapter(adapter);
    }

    private void openTaskDoneActivity() {
        Intent intent = new Intent(this,TaskDoneActivity.class);
        startActivity(intent);
    }

    @Override
    public void applyTexts(String date, String time, String passage, String deadline, String reminder) {
        notes.add(new Notes(time,passage,date,deadline,reminder));
        adapter.notifyItemInserted(notes.size()-1);
    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }
    static void del_notes(Notes note){
        for(Notes nts : notes){
            if(nts.check(note)){
                notes.remove(note);
                adapter.notifyItemInserted(notes.size()-1);
                return;
            }
        }
//        printToast();
    }
}