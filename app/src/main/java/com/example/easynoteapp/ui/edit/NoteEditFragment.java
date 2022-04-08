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

    private static final String EXTRA_PARAM = "EXTRA_PARAM";

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

        Log.i("BBBBB", "Fragment on create");
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
//        this.container =(LinearLayout) this.requireView();
        CoordinatorLayout container = view.findViewById(R.id.note_edit_coordinator_layout);
        Bundle args = getArguments();
        noteEditPresenter = new NoteEditPresenter(this, container, args);


        Toolbar.OnMenuItemClickListener menuItemClickListener = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                noteEditPresenter.action(item.getItemId());
                return false;
            }
        };
        MaterialToolbar toolbar = view.findViewById(R.id.app_bar_edit_note);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);



        //        topMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
////                noteEditPresenter.action(menuItem.getItemId());
//                return false;
//            }
//        });
//        view.findViewById(R.id.menu_button_back).setOnClickListener(baseClickListener);
//        view.findViewById(R.id.menu_button_share).setOnClickListener(baseClickListener);
        //    view.findViewById(R.id.menu_button_find).setOnClickListener(baseClickListener);
//        view.findViewById(R.id.menu_button_delete).setOnClickListener(baseClickListener);
//        View temp = view.findViewById(R.id.menu_button_delete);
//temp.setOnClickListener(baseClickListener);

       /* view.findViewById(R.id.app_bar_edit_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .popBackStack();

            }
        });*/



/*        getParentFragmentManager()
                .setFragmentResultListener(NotesListFragment.NOTE_SELECTED, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Log.i("BBBBB","SWITCED NOTE");
                        Note note = result.getParcelable(EXTRA_PARAM);
                        edit(note);


                    }
                });*/


    }

//    @Override
//    public void show(@Nullable Note note) {
////       View editView = getLayoutInflater().inflate(R.layout.fragment_note_edit,container,false);
//
//
//
//
//        EditText editText = container.findViewById(R.id.text_edit_note);
//        MaterialToolbar toolbar = container.findViewById(R.id.app_bar_edit_note);
//
//
//        editText.setText(note.getText());
//
//        toolbar.setTitle(note.getDescription());
//
//    }
//
//    @Override
//    public void message(String message) {
//        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
//    }
}