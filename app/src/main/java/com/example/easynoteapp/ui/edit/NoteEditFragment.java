package com.example.easynoteapp.ui.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.ui.NavDrawable;
import com.example.easynoteapp.ui.edit.fragmentinterface.NoteEdit;
import com.example.easynoteapp.ui.edit.presenter.NoteEditPresenter;
import com.example.easynoteapp.ui.listfragment.NotesListFragment;
import com.google.android.material.appbar.MaterialToolbar;


public class NoteEditFragment extends Fragment implements NoteEdit {


    private NoteEditPresenter<CoordinatorLayout> noteEditPresenter;
    EditText text;
    Note editableNote;
    MaterialToolbar toolbar;
    View view;

    public static NoteEditFragment newInstance(Note note, int editNoteIndex) {

        Bundle args = new Bundle();
        args.putParcelable(NotesListFragment.KEY_NOTE_ITEM, note);
        args.putInt(NotesListFragment.KEY_NOTE_INDEX, editNoteIndex);

        NoteEditFragment fragment = new NoteEditFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_edit, container, false);

    }


    @Override
    public void onPause() {
        super.onPause();
        noteEditPresenter.saveNote(editableNote);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("BBBBB", "View Created");
        text = view.findViewById(R.id.text_edit_note);
        toolbar = view.findViewById(R.id.app_bar_edit_note);
        this.view = view;

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(NotesListFragment.KEY_NOTE_ITEM)) {
            editableNote = bundle.getParcelable(NotesListFragment.KEY_NOTE_ITEM);

        }


        noteEditPresenter = NoteEditPresenter.newInstance(this, bundle);

        noteEditPresenter.show();


        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                editableNote.setText(text.getText().toString());
            }
        });

        Toolbar.OnMenuItemClickListener menuItemClickListener = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                noteEditPresenter.action(item.getItemId(), editableNote);
                return false;
            }
        };


        MaterialToolbar toolbar = view.findViewById(R.id.app_bar_edit_note);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);


    }


    @Override
    public void show() {

        if (requireActivity() instanceof NavDrawable) {
            ((NavDrawable) requireActivity()).setAppToolbar(toolbar);
        }


        text.setText(noteEditPresenter.getNote().getText());

        toolbar.setTitle(noteEditPresenter.getNote().getDescription());
    }

    @Override
    public void message(String message) {

    }
}