package com.example.easynoteapp.ui.listfragment.presenter;

import static com.example.easynoteapp.ui.listfragment.NotesListFragment.KEY_NEED_UPDATE;
import static com.example.easynoteapp.ui.listfragment.NotesListFragment.KEY_NOTE_INDEX;
import static com.example.easynoteapp.ui.listfragment.NotesListFragment.NOTE_UPDATED;

import android.os.Bundle;

import com.example.easynoteapp.domain.CallBack;
import com.example.easynoteapp.domain.FirestoreRepositiryImpl;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.domain.NotesRepository;
import com.example.easynoteapp.domain.NotesRepositoryImpl;
import com.example.easynoteapp.domain.SharedNotesRepositoryImpl;
import com.example.easynoteapp.ui.listfragment.NotesListFragment;
import com.example.easynoteapp.ui.listfragment.fragmentInterface.NotesListView;

import java.util.ArrayList;
import java.util.UUID;

public class NoteListPresenter {


    private NotesListView view;
    private NotesRepository repository ;
//    private NotesRepository repository = new NotesRepositoryImpl();
    private ArrayList<Note> notesList = new ArrayList<>();
    private int currentIndex;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void applyArguments(Bundle newBundle){
        if (newBundle != null && newBundle.containsKey(KEY_NEED_UPDATE)) {

            Note noteUpdated = newBundle.getParcelable(NOTE_UPDATED);
            int indexNote = newBundle.getInt(KEY_NOTE_INDEX);

            updateNote(noteUpdated, indexNote);

        }

    }

    public NoteListPresenter(NotesListFragment view) {
        this.view = view;
        repository = new SharedNotesRepositoryImpl(view.requireContext());
        repository = FirestoreRepositiryImpl.INSTANCE;
    }

    public ArrayList<Note> getNotesList(){
        return notesList;
    }
    public void requestNotes(){
//       notesList = repository.getNotes();
         repository.getNotes(new CallBack<ArrayList<Note>>() {
            @Override
            public void onSuccess(ArrayList<Note> data) {
                notesList = data;
                view.showNotes();
            }
        });


    }
    public Note getNoteByIndex(int index){
        return notesList.get(index);
    }

    public void goEditNote(int editIndex){
       setCurrentIndex(editIndex);
        view.editNote();
    }

    public void show(){
        view.showNotes();
    }



    public void updateNote(Note noteUpdated, int indexNote) {
        repository.updateNote(noteUpdated, indexNote, data -> {
        });
//        notesList =  repository.getNotes();
        repository.getNotes(new CallBack<ArrayList<Note>>() {
            @Override
            public void onSuccess(ArrayList<Note> data) {
                notesList = data;
                view.showNotes();
            }
        });
    }
    public void deleteNote(int indexToDelete){
//        repository.deleteNote(indexToDelete,data -> {   });
        repository.deleteNote(notesList.get(indexToDelete),data -> {   });
//        notesList = repository.getNotes();
        repository.getNotes(new CallBack<ArrayList<Note>>() {
            @Override
            public void onSuccess(ArrayList<Note> data) {
                notesList = data;
                view.showNotes();
            }
        });
    }


    public void addNote() {
        Note newNote = new Note(UUID.randomUUID().toString(), "New Description", "");
        repository.addNote(newNote,0,data -> {   });
        goEditNote(0);
    }
}
