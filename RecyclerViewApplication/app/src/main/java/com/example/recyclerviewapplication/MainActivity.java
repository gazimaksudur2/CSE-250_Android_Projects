package com.example.recyclerviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.id_recycler);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Gazi","gazimaksudur03@gmail.com",R.drawable.user));
        items.add(new Item("Gazi Ahmadullah","gaziahmadullah23@gmail.com",R.drawable.me));
        items.add(new Item("Gazi himel","gazihimel084@gmail.com",R.drawable.woman));
        items.add(new Item("Gazi shaplur","gazishaplur928@gmail.com",R.drawable.profile));
        items.add(new Item("Gazi halim","gazihalim2834@gmail.com",R.drawable.myuser));
        items.add(new Item("Gazi nurul huda","gazinurulhuda02@gmail.com",R.drawable.me));
        items.add(new Item("Gazi lutfur","gazilutfur2034@gmail.com",R.drawable.user));
        items.add(new Item("Gazi lotifun nisa","gazilotifunnisa200@gmail.com",R.drawable.girl));
        items.add(new Item("Gazi Asgar","gaziasgar209@gmail.com",R.drawable.myuser));
        items.add(new Item("Gazi arafat","gaziarafat02349@gmail.com",R.drawable.me));
        items.add(new Item("Gazi locaModrich","gazilocamodrich92@gmail.com",R.drawable.profile));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
    }
}