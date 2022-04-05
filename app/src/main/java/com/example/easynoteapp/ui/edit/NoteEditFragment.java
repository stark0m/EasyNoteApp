package com.example.easynoteapp.ui.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.google.android.material.appbar.MaterialToolbar;


public class NoteEditFragment extends Fragment implements NoteEdit {

    private LinearLayout container;
    private NoteEditPresenter noteEditPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteEditPresenter = new NoteEditPresenter(this,new Note("1","descr","any text"));
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_note_edit,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        container = view.findViewById(R.id.note_edit_container_layout);
        noteEditPresenter.show();
    }

    @Override
    public void edit(@Nullable Note note) {
        View editView = getLayoutInflater().inflate(R.layout.activity_note_edit,container,false);
        EditText editText = editView.findViewById(R.id.text_edit_note);
        MaterialToolbar toolbar = editView.findViewById(R.id.app_bar_edit_note);

        editText.setText(note.getText());
        toolbar.setTitle(note.getDescription());

        container.addView(editView);
    }
}