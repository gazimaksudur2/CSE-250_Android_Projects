package com.example.mysqlapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_activity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button update_button, delete_button;
    String id,title,author,pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input_2);
        author_input = findViewById(R.id.author_input_2);
        pages_input = findViewById(R.id.pages_input_2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.del_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getandsetintentdata method
        ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update_activity.this);
                myDB.updateData(id, title, author, pages);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")&&getIntent().hasExtra("title")&&getIntent().hasExtra("author")&&getIntent().hasExtra("pages")){
            //Setting data in Intent
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);

            // Getting Data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
        }else{
            Toast.makeText(this, "NO Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + title + " ?");
        builder.setMessage("Are you sure you want to delete "+title+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update_activity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}