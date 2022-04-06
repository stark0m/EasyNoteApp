package com.example.easynoteapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Date;

public class Note implements Parcelable {
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

    protected Note(Parcel in) {
        id = in.readString();
        description = in.readString();
        text = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(description);
        parcel.writeString(text);
    }
}
