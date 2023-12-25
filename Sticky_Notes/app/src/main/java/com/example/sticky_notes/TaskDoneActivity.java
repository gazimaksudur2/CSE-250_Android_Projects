package com.example.sticky_notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.TreeSet;

public class TaskDoneActivity extends AppCompatActivity {
    private ImageView empty_imageview;
    private TextView no_data;
    private Button done_nil,go_home;
    RecyclerView recyclerView;
    TaskDoneCustomAdapter taskDoneCustomAdapter;
    ArrayList<String> id, date, time, notes, deadline, reminder;
    public static ArrayList<String> done_id;
    MyDatabaseHelper myDB;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_done);
        recyclerView = findViewById(R.id.recyclerView_task);

        empty_imageview = findViewById(R.id.empty_imageview_task);
        no_data = findViewById(R.id.no_data_task);
        go_home = findViewById(R.id.go_home);
        done_nil = findViewById(R.id.task_done_nil);

        context = TaskDoneActivity.this;
        myDB = MainActivity.myDB;
        id = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        notes = new ArrayList<>();
        deadline = new ArrayList<>();
        reminder = new ArrayList<>();

        //here I'm facing some major bug here something that I've changed
        done_id = MyDatabaseHelper.done_id;

//        Toast.makeText(context, "done id count : "+done_id.size()+"& note appears "+date.size()+"pcs & done->"+done_id, Toast.LENGTH_SHORT).show();

        storeDataInArrays();

        taskDoneCustomAdapter = new TaskDoneCustomAdapter(TaskDoneActivity.this,context, id, date, time, notes, deadline, reminder);
        recyclerView.setAdapter(taskDoneCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TaskDoneActivity.this));

        go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(TaskDoneActivity.this, "in main done task "+done_id.size(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TaskDoneActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        done_nil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done_id.clear();
                Toast.makeText(TaskDoneActivity.this, "All done_id Re-sticked!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TaskDoneActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(MyDatabaseHelper.done_id.size() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                String temp;
                temp = cursor.getString(0);
                for(String a : MyDatabaseHelper.done_id){
                   if(a.equals(temp)){
                        id.add(cursor.getString(0));
                        date.add(cursor.getString(1));
                        time.add(cursor.getString(2));
                        notes.add(cursor.getString(3));
                        deadline.add(cursor.getString(4));
                        reminder.add(cursor.getString(5));
                        break;
                    }
                }
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
//        if(item.getItemId() == R.id.go_home){
//            Toast.makeText(this, "in main done task "+done_id.size(), Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(TaskDoneActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure to delete "+done_id.size()+" Note from task done?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                for(String a : done_id){
                    myDB.deleteOneRow(a);
                }

                done_id.clear();
                MyDatabaseHelper.done_id.clear();

                //Refresh Activity
                Intent intent = new Intent(TaskDoneActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(TaskDoneActivity.this, "Nothing Done!!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}
