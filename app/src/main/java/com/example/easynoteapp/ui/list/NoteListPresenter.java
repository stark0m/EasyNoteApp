package com.example.easynoteapp.ui.list;

import android.util.Log;

import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.domain.NotesRepository;

import java.util.List;

public class NoteListPresenter {
    private NotesListView view;
    private NotesRepository repository;

    public NoteListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes(){
        List<Note> notes = repository.getNotes();
        Log.i("BBBBB","Got from rep"+ notes.toString());
        view.showNotes(notes);
    }
}
