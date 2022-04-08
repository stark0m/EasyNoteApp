package com.example.easynoteapp.ui.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.ui.list.NotesListFragment;
import com.google.android.material.appbar.MaterialToolbar;


public class NoteEditFragment extends Fragment {


    private NoteEditPresenter<CoordinatorLayout> noteEditPresenter;

    public static NoteEditFragment newInstance(Note note) {

        Bundle args = new Bundle();
        args.putParcelable(NotesListFragment.EXTRA_PARAM, note);

        NoteEditFragment fragment = new NoteEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.i("BBBBB", "Fragment on create");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("BBBBB", "Creation view");
        return inflater.inflate(R.layout.fragment_note_edit, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("BBBBB", "View Created");


        Bundle args = getArguments();
        noteEditPresenter = NoteEditPresenter.newInstance(this, args);

        Toolbar.OnMenuItemClickListener menuItemClickListener = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                noteEditPresenter.action(item.getItemId());
                return false;
            }
        };


        MaterialToolbar toolbar = view.findViewById(R.id.app_bar_edit_note);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);


    }


}