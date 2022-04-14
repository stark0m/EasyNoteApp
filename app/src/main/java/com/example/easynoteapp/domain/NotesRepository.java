package com.example.easynoteapp.domain;

import java.util.ArrayList;
import java.util.List;

public interface NotesRepository {
    ArrayList<Note> getNotes();
    void updateNote(Note note, int index);
    void deleteNote(int index);
    void addNote(Note note, int index);

}
