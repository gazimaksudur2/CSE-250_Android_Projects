package com.example.expandablerecyclerviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

public class ConfromOrderActivity extends AppCompatActivity {

    RecyclerView orderrecycler;
    ConfirmOrderActivityAdapter confirmOrderActivityAdapter;
    Activity context;
    UpdateSelectedItem updateSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confrom_order);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Confirm Order");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        orderrecycler = findViewById(R.id.order_recycler);
        confirmOrderActivityAdapter = new ConfirmOrderActivityAdapter(context);
        orderrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        orderrecycler.setAdapter(confirmOrderActivityAdapter);
    }
}