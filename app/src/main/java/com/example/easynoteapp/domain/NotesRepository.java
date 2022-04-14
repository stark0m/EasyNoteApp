package com.example.easynoteapp.domain;

import java.util.ArrayList;
import java.util.List;

public interface NotesRepository {
    void getNotes(CallBack<ArrayList<Note>> callback);
    void updateNote(Note note, int index,CallBack<Void> callback);
    void deleteNote(int index,CallBack<Void> callback);
    void deleteNote(Note note,CallBack<Void> callback);
    void addNote(Note note, int index,CallBack<Void> callback);

}
