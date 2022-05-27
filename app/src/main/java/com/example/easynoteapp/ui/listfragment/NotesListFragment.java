package com.example.easynoteapp.ui.listfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.ui.NavDrawable;
import com.example.easynoteapp.ui.edit.NoteEditFragment;
import com.example.easynoteapp.ui.listfragment.fragmentInterface.NotesListView;
import com.example.easynoteapp.ui.listfragment.presenter.NoteListPresenter;
import com.google.android.material.appbar.MaterialToolbar;

public class NotesListFragment extends Fragment implements NotesListView {
    public static final int COUNT_OUTPUT_LONG_DESCRIPTION_CHARS = 40;
    public static final String KEY_NOTE_ITEM = "EXTRA_PARAM";
    public static final String KEY_NOTE_INDEX = "KEY_NOTE_INDEX";
    public static final String NOTE_SELECTED = "LANDSCAPE_LISTENER";
    public static final String NOTE_CHANGED = "NOTE_CHANGED";
    public static final String NOTE_UPDATED = "NOTE_UPDATED";
    public static final String KEY_NEED_UPDATE = "KEY_NEED_UPDATE";

    private static NotesListFragment Instance;
    private MaterialToolbar topToolbar;
    private NoteListPresenter presenter;
    private RecyclerAdapter recyclerAdapter;

    private  View fragmentView;

    public  static NotesListFragment NewInstance(@Nullable Bundle bundle) {
        if (Instance == null) {
            Instance = new NotesListFragment();
        }


        if (bundle != null) {
            bundle.putBoolean(KEY_NEED_UPDATE, true);
            Instance.setArguments(bundle);
        }

        return Instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("DDDD", "CREATE");
        super.onCreate(savedInstanceState);
        presenter = new NoteListPresenter(this);

    }

    public NotesListFragment() {
        super(R.layout.fragments_notes_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topToolbar = view.findViewById(R.id.app_bar_list_note);
        fragmentView = view;

        showRecycler();
        presenter.requestNotes();
        Bundle newBundle = getArguments();
        presenter.applyArguments(newBundle);
        presenter.show();

        setListeners();

    }

    private void setListeners() {

        recyclerAdapter.setiC(new RecyclerAdapter.ItemClicked() {
            @Override

            public void onItemClicked(int index) {
                presenter.goEditNote(index);
                return;
            }

            @Override
            public void onItemLongClicked(Note note, int index) {

                recyclerAdapter.setNoteSelected(note);
                recyclerAdapter.setNoteSelectedIndex(index);
            }
        });


        Toolbar.OnMenuItemClickListener topMenuListener = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.addNote();
                return true;
            }
        };


        topToolbar.setOnMenuItemClickListener(topMenuListener);


        if (requireActivity() instanceof NavDrawable) {
            ((NavDrawable) requireActivity()).setAppToolbar(topToolbar);
        }
    }

    private void showRecycler() {
        RecyclerView listNotes = fragmentView.findViewById(R.id.id_list_recycler);
        RecyclerView.LayoutManager recyclerManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        listNotes.setLayoutManager(recyclerManager);
        recyclerAdapter = new RecyclerAdapter(this);
        listNotes.setAdapter(recyclerAdapter);
    }


    @Override
    public void showNotes() {


        recyclerAdapter.getData(presenter.getNotesList());
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.id_delete_context: // delete context selected
                presenter.deleteNote(recyclerAdapter.getNoteSelectedIndex());
                recyclerAdapter.getData(presenter.getNotesList());
                recyclerAdapter.notifyDataSetChanged();


                Toast.makeText(requireContext(), "DELETE CLICKED", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.id_edit_context: // edit context selected
                presenter.goEditNote(recyclerAdapter.getNoteSelectedIndex());
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void editNote() {

        int index = presenter.getCurrentIndex();
        Note note = presenter.getNoteByIndex(index);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_note_list, NoteEditFragment.newInstance(note, index))
                .addToBackStack("")
                .commitAllowingStateLoss();


    }


    @Override
    public void onPause() {
        super.onPause();
        Log.i("DDDD", "PAUSE");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("DDDD", "Destroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("DDDD", "Destroy View");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("DDDD", "DETACHED");
    }
}
