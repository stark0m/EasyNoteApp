package com.example.easynoteapp.domain;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class SharedNotesRepositoryImpl implements NotesRepository{

    private static final String KEY_SAVED ="KEY_SAVED";
    private static final String PREFS_FILE_NAME ="preferences";
    private final SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();
    private ArrayList<Note> data = new ArrayList<>();

    public SharedNotesRepositoryImpl(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(KEY_SAVED, "[]");
        Type notesType = new TypeToken<ArrayList<Note>>() {
        }.getType();

        data.addAll(gson.fromJson(value, notesType));
    }

    @Override
    public ArrayList<Note> getNotes() {
        return data;
    }

    @Override
    public void updateNote(Note note, int index) {
        Type notesType = new TypeToken<ArrayList<Note>>() {
        }.getType();
        data.set(index,note);
        sharedPreferences.edit()
                .putString(KEY_SAVED, gson.toJson(data, notesType))
                .apply();
    }

    @Override
    public void deleteNote(int index) {

        Type notesType = new TypeToken<ArrayList<Note>>() {
        }.getType();
        data.remove(index);
        sharedPreferences.edit()
                .putString(KEY_SAVED, gson.toJson(data, notesType))
                .apply();
    }

    @Override
    public void addNote(Note note, int index) {
        Type notesType = new TypeToken<ArrayList<Note>>() {
        }.getType();
        data.add(index,note);
        sharedPreferences.edit()
                .putString(KEY_SAVED, gson.toJson(data, notesType))
                .apply();


    }
}
