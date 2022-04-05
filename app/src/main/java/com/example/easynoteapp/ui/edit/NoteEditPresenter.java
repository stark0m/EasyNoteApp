package com.example.easynoteapp.ui.edit;


import com.example.easynoteapp.domain.Note;

public class NoteEditPresenter {

    private NoteEdit view;
    private Note note;

    public NoteEditPresenter(NoteEdit view, Note note) {
        this.view = view;
        this.note = note;
    }



    public void show(){
        view.edit(note);
    }
}
