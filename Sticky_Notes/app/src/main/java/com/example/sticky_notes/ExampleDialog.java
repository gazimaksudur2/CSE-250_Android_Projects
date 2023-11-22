package com.example.sticky_notes;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {

    private EditText editDate, editTime, editPassage, editDeadline, editReminder;
    private String id, date, time, passage, deadline, reminder, title;
    private Button increaseSizeBtn,decreaseSizeBtn,boldBtn,underlineBtn,italicBtn;
    private ExampleDialogListener listener;
    private Button btn_date_gen, btn_time_gen, btn_deadline_gen, btn_reminder_gen;
    private TextView sizeTV ;
    float currentSize;
    Context context;

    public ExampleDialog(Context context){this.context = context;}
    public boolean flag = false;
    public ExampleDialog(Context context, String id, String date, String time, String passage, String deadline, String reminder) {
        this.context = context;
        this.date = date;
        this.time = time;
        this.passage = passage;
        this.deadline = deadline;
        this.reminder = reminder;
        flag = true;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        editDate = view.findViewById(R.id.edit_date);
        editTime = view.findViewById(R.id.edit_time);
        editPassage = view.findViewById(R.id.edit_passage);
        editDeadline = view.findViewById(R.id.edit_deadline);
        editReminder = view.findViewById(R.id.edit_reminder);

        if(flag){
            editDate.setText(date);
            editTime.setText(time);
            editPassage.setText(passage);
            editDeadline.setText(deadline);
            editReminder.setText(reminder);
            title = "Update Your Note Here";
        }else{
            title = "Create Your Note Here";
        }

        increaseSizeBtn = view.findViewById(R.id.idBtnIncreaseSize);
        decreaseSizeBtn = view.findViewById(R.id.idBtnReducesize);
        boldBtn = view.findViewById(R.id.idBtnBold);
        underlineBtn = view.findViewById(R.id.idBtnUnderline);
        italicBtn = view.findViewById(R.id.idBtnitalic);
        btn_time_gen = view.findViewById(R.id.btn_time_gen);
        btn_date_gen = view.findViewById(R.id.btn_date_gen);
        btn_deadline_gen = view.findViewById(R.id.btn_deadline_gen);
        btn_reminder_gen = view.findViewById(R.id.btn_reminder_gen);
        currentSize = editPassage.getTextSize();
        sizeTV = view.findViewById(R.id.idTVsize);
        sizeTV.setText(""+currentSize);

        increaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTV.setText(""+currentSize);
                currentSize++;
                editPassage.setTextSize(currentSize);
            }
        });

        decreaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeTV.setText(""+currentSize);
                currentSize--;
                editPassage.setTextSize(currentSize);
            }
        });

        boldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                italicBtn.setTextColor(getResources().getColor(R.color.white));
                italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                boolean bl = (editPassage.getTypeface().getStyle()== Typeface.BOLD);
                // that gives the perfect result on my emulator but not on my Redmi note 10s
                if(editPassage.getTypeface().isBold()){
                    boldBtn.setTextColor(getResources().getColor(R.color.white));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
//                    editPassage.setText(noteEdt.getText()+" true");
                    editPassage.setTypeface(Typeface.DEFAULT);
//                    editPassage.setText("it is already bold");
                }else{
                    boldBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.white));
//                    editPassage.setText(noteEdt.getText()+" false");
                    editPassage.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }
        });

        underlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editPassage.getPaintFlags()==8){
                    underlineBtn.setTextColor(getResources().getColor(R.color.white));
                    underlineBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    editPassage.setPaintFlags(editPassage.getPaintFlags()&(~Paint.UNDERLINE_TEXT_FLAG));
                }else{
                    underlineBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    underlineBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    editPassage.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });

        italicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boldBtn.setTextColor(getResources().getColor(R.color.white));
                boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(editPassage.getTypeface().isItalic()){
                    editPassage.setTypeface(Typeface.DEFAULT);
                    italicBtn.setTextColor(getResources().getColor(R.color.white));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }else{
                    italicBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    editPassage.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }
            }
        });

        btn_date_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog(editDate);
            }
        });

        btn_time_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClockDialog(editTime);
            }
        });

        btn_deadline_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog(editDeadline);
            }
        });

        btn_reminder_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClockDialog(editReminder);
            }
        });

        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.btnNoAction();
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        date = editDate.getText().toString().trim();
                        time = editTime.getText().toString().trim();
                        passage = editPassage.getText().toString().trim();
                        deadline = editDeadline.getText().toString().trim();
                        reminder = editReminder.getText().toString().trim();

                        listener.applyTexts(date,time,passage,deadline,reminder);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement ExampleDialogListener");
        }
    }

    public void openDateDialog(EditText text){
//        String temp = text.getText().toString().trim();
//        int day,mon,yr;
//        day = 25;
//        mon = 05;
//        yr = 2023;
//        if(temp.length()==10){
//            day = Integer.parseInt(temp.substring(0,1));
//            mon = Integer.parseInt(temp.substring(5,6));
//            yr = Integer.parseInt(temp.substring(10,13));
//        }
//        String format = "%0" + 2 + "d";
//        String format1 = "%0" + 4 + "d";
        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//                text.setText(String.format(format,String.valueOf(dayOfMonth))+" / "+String.format(format,String.valueOf(month+1))+" / "+String.format(format1,String.valueOf(year)));}}, yr,mon-1,day);
        text.setText(String.valueOf(dayOfMonth)+" / "+String.valueOf(month+1)+" / "+String.valueOf(year));}}, 2023,5-1,25);
        dialog.show();
    }

    public void openClockDialog(EditText text2){
//        String temp = text2.getText().toString().trim();
//        int hr,min;
//        hr = 04;
//        min = 30;
//        if(temp.length()==5){
//            hr = Integer.parseInt(temp.substring(0,1));
//            min = Integer.parseInt(temp.substring(3,4));
//        }
//        String format = "%0" + 2 + "d";
        TimePickerDialog dialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                text2.setText(String.format(format,String.valueOf(hourOfDay))+":"+String.format(format,String.valueOf(minute)));
                text2.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
            }
        }, 04,30, true);
        dialog.show();
    }

    public interface ExampleDialogListener{
        void applyTexts(String date, String time, String passage, String deadline, String reminder);
        void btnNoAction();
    }
}
