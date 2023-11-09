package com.example.sticky_note_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskDoneActivity extends AppCompatActivity {

    private Button btn_back, btn_del;
    private Context context;
    static List<Notes> notes = new ArrayList<Notes>();
    private static TaskDoneAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_done_layout);


        RecyclerView recyclerView = findViewById(R.id.id_recycler_done_task);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskDoneAdapter(this,notes);
        recyclerView.setAdapter(adapter);

        btn_back = findViewById(R.id.btn_back);
        btn_del = findViewById(R.id.btn_delete_done_tasks);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.clear();
                adapter.notifyItemInserted(notes.size()-1);
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    static void add_notes(Notes note){
        notes.add(note);
        adapter.notifyItemInserted(notes.size()-1);
//        notes.remove(notes.size()-1);
    }
}
