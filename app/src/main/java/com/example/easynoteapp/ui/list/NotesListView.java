package com.example.easynoteapp.ui.list;

import com.example.easynoteapp.domain.Note;

import java.util.List;

public interface NotesListView {
    void showNotes(List<Note> notes);
}
