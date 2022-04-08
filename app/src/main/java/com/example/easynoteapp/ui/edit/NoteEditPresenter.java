package com.example.easynoteapp.ui.edit;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.ui.list.NoteListPresenter;
import com.example.easynoteapp.ui.list.NotesListFragment;
import com.google.android.material.appbar.MaterialToolbar;

public class NoteEditPresenter<T extends View> implements NoteEdit {

    private T view;
    private Note note;
    private NoteEditFragment fragment;


    public static NoteEditPresenter newInstance(NoteEditFragment fragment, @Nullable Bundle bundle) {
        NoteEditPresenter instance = new NoteEditPresenter();
        instance.fragment = fragment;
        instance.view = fragment.getView();
        if (bundle != null && bundle.containsKey(NotesListFragment.EXTRA_PARAM)) {
            instance.setNote(bundle.getParcelable(NotesListFragment.EXTRA_PARAM));

            instance.show();

        } else {
            Toast.makeText(fragment.requireContext(), "NO NOTE TO EDIT", Toast.LENGTH_SHORT).show();
        }

        return instance;
    }

    ;





    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public void show() {


        EditText editText = view.findViewById(R.id.text_edit_note);
        MaterialToolbar toolbar = view.findViewById(R.id.app_bar_edit_note);


        editText.setText(note.getText());

        toolbar.setTitle(note.getDescription());

    }

    @Override
    public void message(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void action(@IdRes int id) {
        switch (id) {
            case R.id.menu_button_back: {
//                message(fragment.getView().getClass().toString());
                fragment.getParentFragmentManager()
                        .popBackStack();

            }
            case R.id.menu_button_share: {
                message("Share clicked");
                Log.i("BBBBB", "CLICKED SHARE");
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
}
