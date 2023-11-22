package com.example.sticky_notes;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WidgetViewActivity extends AppCompatActivity {
    private EditText noteEdt;
    private Button increaseSizeBtn,decreaseSizeBtn,boldBtn,underlineBtn,italicBtn;
    private TextView sizeTV;
    static StickyNote note = new StickyNote();
    float currentSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_view);

        noteEdt = findViewById(R.id.idEdt);
        increaseSizeBtn = findViewById(R.id.idBtnIncreaseSize);
        decreaseSizeBtn = findViewById(R.id.idBtnReducesize);
        boldBtn = findViewById(R.id.idBtnBold);
        underlineBtn = findViewById(R.id.idBtnUnderline);
        italicBtn = findViewById(R.id.idBtnitalic);
        currentSize = noteEdt.getTextSize();
        sizeTV = findViewById(R.id.idTVsize);
        sizeTV.setText(""+currentSize);

        noteEdt.setText(note.getstick(WidgetViewActivity.this));

        increaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTV.setText(""+currentSize);
                currentSize++;
                noteEdt.setTextSize(currentSize);
            }
        });

        decreaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTV.setText(""+currentSize);
                currentSize--;
                noteEdt.setTextSize(currentSize);
            }
        });

        boldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                italicBtn.setTextColor(getResources().getColor(R.color.white));
                italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                boolean bl = (noteEdt.getTypeface().getStyle()== Typeface.BOLD);
                // that gives the perfect result on my emulator but not on my Redmi note 10s
                if(noteEdt.getTypeface().isBold()){
                    boldBtn.setTextColor(getResources().getColor(R.color.white));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
//                    noteEdt.setText(noteEdt.getText()+" true");
                    noteEdt.setTypeface(Typeface.DEFAULT);
//                    noteEdt.setText("it is already bold");
                }else{
                    boldBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.white));
//                    noteEdt.setText(noteEdt.getText()+" false");
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }
        });

        underlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteEdt.getPaintFlags()==8){
                    underlineBtn.setTextColor(getResources().getColor(R.color.white));
                    underlineBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    noteEdt.setPaintFlags(noteEdt.getPaintFlags()&(~Paint.UNDERLINE_TEXT_FLAG));
                }else{
                    underlineBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    underlineBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });

        italicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boldBtn.setTextColor(getResources().getColor(R.color.white));
                boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteEdt.getTypeface().isItalic()){
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    italicBtn.setTextColor(getResources().getColor(R.color.white));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }else{
                    italicBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }
            }
        });
    }

    public void saveButton(View view) {
        note.setStick(noteEdt.getText().toString(),WidgetViewActivity.this);
        updateWidget();
        Toast.makeText(WidgetViewActivity.this,"Note has been updated..",Toast.LENGTH_SHORT).show();
    }

    private void updateWidget(){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(WidgetViewActivity.this);
        RemoteViews remoteViews = new RemoteViews(WidgetViewActivity.this.getPackageName(),R.layout.widget_layout);
        ComponentName thisWidget = new ComponentName(WidgetViewActivity.this,AppWidget.class);
        remoteViews.setTextViewText(R.id.idTVWidget,noteEdt.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);
    }
}