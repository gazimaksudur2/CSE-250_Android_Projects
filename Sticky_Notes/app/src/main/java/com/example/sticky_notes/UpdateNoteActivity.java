package com.example.sticky_notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateNoteActivity extends MainActivity implements ExampleDialog.ExampleDialogListener {
    String id, date, time, passage, deadline, reminder;
    CustomAdapter customAdapter = new CustomAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //First we call this
        id = customAdapter.getId();
        date = customAdapter.getDate();
        time = customAdapter.getTime();
        passage = customAdapter.getPassage();
        deadline = customAdapter.getDeadline();
        reminder = customAdapter.getReminder();

        openDialog(UpdateNoteActivity.this,date, time, passage, deadline, reminder);
    }


    @Override
    public void applyTexts(String date, String time, String passage, String deadline, String reminder) {
        this.date = date;
        this.time = time;
        this.passage = passage;
        this.deadline = deadline;
        this.reminder = reminder;

        MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateNoteActivity.this);
        if(date.isEmpty() || time.isEmpty() || passage.isEmpty() || deadline.isEmpty() || reminder.isEmpty()){
            Toast.makeText(UpdateNoteActivity.this, "Please enter valid info!!", Toast.LENGTH_SHORT).show();
            finish();return;
        }

        myDB.updateData(id, date, time, passage, deadline, reminder);
        Intent intent = new Intent(UpdateNoteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void btnNoAction() {
        Intent intent = new Intent(UpdateNoteActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Nothing Updated!!", Toast.LENGTH_SHORT).show();
        finish();
    }


    public void openDialog(Context context, String date, String time, String passage, String deadline, String reminder){
        ExampleDialog exampleDialog = new ExampleDialog(context,id, date, time, passage, deadline, reminder);
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }
        public interface getInfo{
        String getId();
        String getDate();
        String getTime();
        String getPassage();
        String getDeadline();
        String getReminder();
    }
}