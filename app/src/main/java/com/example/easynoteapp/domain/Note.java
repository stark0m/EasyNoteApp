package com.example.easynoteapp.domain;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Date;

public class Note {
    private final String id;
    private String description;
    private Date date;
    private String text;

    public Note(String id, String description) {
        this.id = id;
        this.description = description;
        this.date = new Date();
        this.text = new String();
    }
    public Note(String id, String description,String inputText) {
        this.id = id;
        this.description = description;
        this.date = new Date();
        this.text = inputText;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void update(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
