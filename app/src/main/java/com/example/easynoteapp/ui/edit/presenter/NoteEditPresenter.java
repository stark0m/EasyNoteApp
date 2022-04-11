package com.example.easynoteapp.ui.edit.presenter;


import static com.example.easynoteapp.ui.listfragment.NotesListFragment.KEY_NOTE_INDEX;
import static com.example.easynoteapp.ui.listfragment.NotesListFragment.NOTE_CHANGED;
import static com.example.easynoteapp.ui.listfragment.NotesListFragment.NOTE_UPDATED;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.ui.edit.fragmentinterface.NoteEdit;
import com.example.easynoteapp.ui.edit.NoteEditFragment;
import com.example.easynoteapp.ui.listfragment.NotesListFragment;

public class NoteEditPresenter<T extends View> implements NoteEdit {

    private T view;
    private Note note;
    private int indexNote;
    private NoteEditFragment fragment;



    public static NoteEditPresenter newInstance(NoteEditFragment fragment, @Nullable Bundle bundle) {
        NoteEditPresenter instance = new NoteEditPresenter();
        instance.fragment = fragment;
        instance.view = fragment.getView();
        if (bundle != null && bundle.containsKey(NotesListFragment.KEY_NOTE_ITEM)) {
            instance.note =bundle.getParcelable(NotesListFragment.KEY_NOTE_ITEM);
            instance.indexNote = bundle.getInt(KEY_NOTE_INDEX);




        } else {
            Toast.makeText(fragment.requireContext(), "NO NOTE TO EDIT", Toast.LENGTH_SHORT).show();
        }

        return instance;
    }

    public void saveNote(Note editedNote) {
        Bundle bundle = new Bundle();



        bundle.putParcelable(NOTE_UPDATED, editedNote);
        bundle.putInt(KEY_NOTE_INDEX,indexNote);

        fragment.requireActivity().getSupportFragmentManager()
                .setFragmentResult(NOTE_CHANGED, bundle);
    }


    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public void show() {

                 fragment.show();


        }


        public void action (@IdRes int id,Note noteReceived){
            switch (id) {
                case R.id.menu_button_back: {

                    fragment.getParentFragmentManager()
                            .popBackStack();
                    break;

                }
                case R.id.menu_button_share: {
                    saveNote(noteReceived);

                    break;
                }
                case R.id.menu_button_find: {
                    message("Find clicked");
                    break;
                }
                case R.id.menu_button_delete: {
                    message("Delete clicked");
                    break;
                }

            }

        }


    @Override
    public void message(String message) {

    }

    public Note getNote() {
        return note;
    }
}