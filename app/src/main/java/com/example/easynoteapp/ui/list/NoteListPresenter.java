package com.example.easynoteapp.ui.list;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.domain.NotesRepository;

import java.util.ArrayList;
import java.util.List;

public class NoteListPresenter {
    private NotesListView view;
    private NotesRepository repository;

    public NoteListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes(){
        ArrayList<Note> notes = repository.getNotes();

        view.showNotes(notes);
    }

}
