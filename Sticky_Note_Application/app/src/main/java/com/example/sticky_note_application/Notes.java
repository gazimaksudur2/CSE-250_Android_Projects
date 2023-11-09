package com.example.sticky_note_application;

public class Notes {
    private String text, date, time, deadline, reminder;


    public Notes(String time,String text,String date, String deadline, String reminder) {
        this.time = time;
        this.text = text;
        this.date = date;
        this.deadline = deadline;
        this.reminder = reminder;
    }
    public Notes(Notes note) {
        this.time = note.time;
        this.text = note.text;
        this.date = note.date;
        this.deadline = note.deadline;
        this.reminder = note.reminder;
    }

    boolean check(Notes nt){
        if((this.time == nt.time) && (this.text == nt.text) && (this.date == nt.date) && (this.deadline == nt.deadline) && this.reminder == nt.reminder){
            return true;
        }
        return false;
    }

    public String text() {
        return text;
    }

    public String date() {
        return date;
    }

    public String time() {
        return time;
    }

    public String deadline() {
        return deadline;
    }
    public String reminder() {
        return reminder;
    }
}
