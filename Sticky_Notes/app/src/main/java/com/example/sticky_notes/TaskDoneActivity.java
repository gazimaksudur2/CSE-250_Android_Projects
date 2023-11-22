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
    RecyclerView recyclerView;
    TaskDoneCustomAdapter taskDoneCustomAdapter;
    ArrayList<String> id, date, time, notes, deadline, reminder;
    public static ArrayList<String> done_id;
//    public static TreeSet<String> Set = new TreeSet<>();
    MyDatabaseHelper myDB;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_done);
        recyclerView = findViewById(R.id.recyclerView_task);

        empty_imageview = findViewById(R.id.empty_imageview_task);
        no_data = findViewById(R.id.no_data_task);

        context = TaskDoneActivity.this;
        myDB = MainActivity.myDB;
        id = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        notes = new ArrayList<>();
        deadline = new ArrayList<>();
        reminder = new ArrayList<>();

        //here I'm facing some major bug
        storeDataInArrays();

        taskDoneCustomAdapter = new TaskDoneCustomAdapter(TaskDoneActivity.this,context, id, date, time, notes, deadline, reminder);
        recyclerView.setAdapter(taskDoneCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TaskDoneActivity.this));
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(done_id.size() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                boolean fl = true;
                String temp;
                temp = cursor.getString(0);
//                for(String a : done_id){
//                    if(a==temp){
//                        fl = false;
//                        break;
//                    }
//                }
//                if(fl){continue;}
                id.add(cursor.getString(0));
                date.add(cursor.getString(1));
                time.add(cursor.getString(2));
                notes.add(cursor.getString(3));
                deadline.add(cursor.getString(4));
                reminder.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

//    void addBook(String id, String date, String time, String note, String deadline, String reminder){
//        NoteAddTaskDone noteAddTaskDone = new NoteAddTaskDone(TaskDoneActivity.this,id,date,time,note,deadline,reminder);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            Toast.makeText(context, "Total note available "+id.size(), Toast.LENGTH_SHORT).show();
//            confirmDialog();
        }
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
