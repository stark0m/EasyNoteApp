package com.example.easynoteapp.domain;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class SharedNotesRepositoryImpl implements NotesRepository {

    private static final String KEY_SAVED = "KEY_SAVED";
    private static final String PREFS_FILE_NAME = "preferences";
    private final SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();
    private ArrayList<Note> data = new ArrayList<>();
    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    public SharedNotesRepositoryImpl(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(KEY_SAVED, "[]");
        Type notesType = new TypeToken<ArrayList<Note>>() {
        }.getType();

        data.addAll(gson.fromJson(value, notesType));
    }


    @Override
    public void getNotes(CallBack<ArrayList<Note>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(30L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(data);

                    }
                });
            }
        });

    }

    @Override
    public void updateNote(Note note, int index, CallBack<Void> callback) {
        Type notesType = new TypeToken<ArrayList<Note>>() {
        }.getType();
        data.set(index, note);
        sharedPreferences.edit()
                .putString(KEY_SAVED, gson.toJson(data, notesType))
                .apply();

        callback.onSuccess(null);
    }

    @Override
    public void deleteNote(int index, CallBack<Void> callback) {

        Type notesType = new TypeToken<ArrayList<Note>>() {
        }.getType();
        data.remove(index);
        sharedPreferences.edit()
                .putString(KEY_SAVED, gson.toJson(data, notesType))
                .apply();
        callback.onSuccess(null);
    }

    @Override
    public void deleteNote(Note note, CallBack<Void> callback) {

    }

    @Override
    public void addNote(Note note, int index, CallBack<Void> callback) {
        Type notesType = new TypeToken<ArrayList<Note>>() {
        }.getType();
        data.add(index, note);
        sharedPreferences.edit()
                .putString(KEY_SAVED, gson.toJson(data, notesType))
                .apply();
        callback.onSuccess(null);

    }
}
