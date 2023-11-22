package com.example.sticky_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteAddActivity extends MainActivity implements ExampleDialog.ExampleDialogListener {

    String date, time, passage, deadline, reminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        openDialog(NoteAddActivity.this);
    }

    @Override
    public void applyTexts(String date, String time, String passage, String deadline, String reminder) {
        this.date = date;
        this.time = time;
        this.passage = passage;
        this.deadline = deadline;
        this.reminder = reminder;

        MyDatabaseHelper myDB = new MyDatabaseHelper(NoteAddActivity.this);
        if(date.isEmpty() || time.isEmpty() || passage.isEmpty() || deadline.isEmpty() || reminder.isEmpty()){
            Toast.makeText(NoteAddActivity.this, "Please enter valid info!!", Toast.LENGTH_SHORT).show();
            finish();return;
        }

        myDB.addBook(date, time, passage, deadline, reminder);
        Intent intent = new Intent(NoteAddActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void btnNoAction() {
        Intent intent = new Intent(NoteAddActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "No Action Attempted!!", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void openDialog(Context context){
        ExampleDialog exampleDialog = new ExampleDialog(context);
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }
}