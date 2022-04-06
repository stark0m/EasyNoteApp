package com.example.easynoteapp.ui.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.ui.list.NotesListFragment;
import com.google.android.material.appbar.MaterialToolbar;


public class NoteEditFragment extends Fragment implements NoteEdit {

    private LinearLayout container;
    private NoteEditPresenter noteEditPresenter;

    private static final String EXTRA_PARAM="EXTRA_PARAM";
    public static NoteEditFragment newInstance(Note note) {
        
        Bundle args = new Bundle();
        args.putParcelable(NotesListFragment.EXTRA_PARAM,note);

        NoteEditFragment fragment = new NoteEditFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Log.i("BBBBB","Fragment on create");
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("BBBBB","Creation view");
        return inflater.inflate(R.layout.fragment_note_edit,container,false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("BBBBB","View Created");
//        this.container =(LinearLayout) this.requireView();
        this.container = view.findViewById(R.id.note_edit_linear_layout);

        view.findViewById(R.id.app_bar_edit_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager()
                        .popBackStack();

            }
        });



/*        getParentFragmentManager()
                .setFragmentResultListener(NotesListFragment.NOTE_SELECTED, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Log.i("BBBBB","SWITCED NOTE");
                        Note note = result.getParcelable(EXTRA_PARAM);
                        edit(note);


                    }
                });*/
       Bundle arguments = getArguments();
       if (arguments!=null && arguments.containsKey(NotesListFragment.EXTRA_PARAM)){
            Note note = arguments.getParcelable(NotesListFragment.EXTRA_PARAM);
        noteEditPresenter = new NoteEditPresenter(this,note);
        noteEditPresenter.show();
    } else{
            Toast.makeText(requireContext(), "NO NOTE TO EDIT", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void edit(@Nullable Note note) {
//       View editView = getLayoutInflater().inflate(R.layout.fragment_note_edit,container,false);

        EditText editText = container.findViewById(R.id.text_edit_note);
        MaterialToolbar toolbar = container.findViewById(R.id.app_bar_edit_note);



        editText.setText(note.getText());

        toolbar.setTitle(note.getDescription());

    }
}