package com.example.sticky_notes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


/*
    implement the widget of that application  ---> done
    implement the clock and clock access for each button ----> done
    implement delete operation from database -----> done
    implement new Task done activity for done tasks
    implement refresh mainactivity using up scrolling recyclerview

    then your app will look so good

 */
public class MainActivity extends AppCompatActivity{
    private ImageView empty_imageview;
    private FloatingActionButton add_task, make_widget;
    private TextView no_data;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ArrayList<String> id, date, time, notes, deadline, reminder;
    public static ArrayList<String> done_id;// that will make a bomb busting
//    public static TreeSet<String> strSet = new TreeSet<>();
    StickyNote note = new StickyNote();
    public static MyDatabaseHelper myDB;

    // here is another issue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_main);

        add_task = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        make_widget = findViewById(R.id.crt_widget);

        myDB = new MyDatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        notes = new ArrayList<>();
        deadline = new ArrayList<>();
        reminder = new ArrayList<>();
        done_id = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, id, date, time, notes, deadline, reminder);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteAddActivity.class);
                startActivity(intent);
            }
        });

        make_widget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WidgetViewActivity.class);
                startActivity(intent);
            }
        });
    }

//    public MyDatabaseHelper shareMyDB(){
//        return myDB;
//    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                boolean fl = false;
                String temp;
                temp = cursor.getString(0);
                for(String a : done_id){
                    if(a==temp){
                        fl = true;
                        break;
                    }
                }
                if(fl){continue;}
                id.add(temp);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        if(item.getItemId() == R.id.task_done){
            TaskDoneActivity.done_id = done_id;
            Toast.makeText(this, "in main done task "+done_id.size(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, TaskDoneActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all "+(id.size()+done_id.size())+" Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Nothing Done!!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

//    public void refresh(){
//        Intent intent = new Intent(MainActivity.this, MainActivity.class);
//        startActivity(intent);
//        recreate();
//        finish();
//    }
}