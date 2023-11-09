package com.example.sticky_note_application;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    private Button increaseSizeBtn,decreaseSizeBtn,boldBtn,underlineBtn,italicBtn;
    private Button btn_date, btn_time, btn_deadline, btn_reminder;
    private ExampleDialogListener listener;
    private TextView sizeTV ;
    private TextView text;
    private Context context;
    float currentSize;

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

        btn_date = view.findViewById(R.id.btn_date_gen);
        btn_time = view.findViewById(R.id.btn_time_gen);
        btn_deadline = view.findViewById(R.id.btn_deadline_gen);
        btn_reminder = view.findViewById(R.id.btn_reminder_gen);

        increaseSizeBtn = view.findViewById(R.id.idBtnIncreaseSize);
        decreaseSizeBtn = view.findViewById(R.id.idBtnReducesize);
        boldBtn = view.findViewById(R.id.idBtnBold);
        underlineBtn = view.findViewById(R.id.idBtnUnderline);
        italicBtn = view.findViewById(R.id.idBtnitalic);
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

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                View vw = view.findViewById(R.id.edit_date);
                openDialog_for_date();
                editDate.setText(text.toString());
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_for_clock();
                editTime.setText(text.toString());
            }
        });

        btn_deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_for_date();
                editDeadline.setText(text.toString());
            }
        });

        btn_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_for_clock();
                editReminder.setText(text.toString());
            }
        });

        builder.setView(view)
                .setTitle("Create Your Note Here")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String date = editDate.getText().toString();
                        String time = editTime.getText().toString();
                        String passage = editPassage.getText().toString();
                        String deadline = editDeadline.getText().toString();
                        String reminder = editReminder.getText().toString();

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
    public interface ExampleDialogListener{
        void applyTexts(String date, String time, String passage, String deadline, String reminder);
    }

    private void openDialog_for_date(){
//        DatePickerDialog dialog = new DatePickerDialog(this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                text.setText(String.valueOf(year)+" / "+String.valueOf(month+1)+" / "+String.valueOf(dayOfMonth));
            }
        }, 2023, 0, 0);
        dialog.show();
    }

    private void openDialog_for_clock(){
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                text.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
            }
        }, 15, 00, true);
        dialog.show();
    }
}
