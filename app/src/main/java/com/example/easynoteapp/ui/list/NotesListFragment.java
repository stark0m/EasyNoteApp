package com.example.easynoteapp.ui.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.domain.NotesRepositoryImpl;
import com.example.easynoteapp.ui.edit.NoteEditFragment;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Date;
import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {
    public static final int COUNT_OUTPUT_LONG_DESCRIPTION_CHARS = 40;
    public static final String EXTRA_PARAM="EXTRA_PARAM";
    public static final String NOTE_SELECTED ="LANDSCAPE_LISTENER";
    private LinearLayout container;
    private NoteListPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("AAAAA","NLF onCreate");
        super.onCreate(savedInstanceState);
        presenter = new NoteListPresenter(this, NotesRepositoryImpl.getInstance());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("AAAAA","NLF onCreateView");
        return inflater.inflate(R.layout.fragments_notes_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        container = view.findViewById(R.id.notes_list_linear_layout);
        presenter.requestNotes();


        Log.i("AAAAA","NLF onViewCreated");
    }

    @Override
    public void showNotes(List<Note> notes) {
        Log.i("AAAAA","NLF BeginShowing listNotes!!!!");
       if (notes==null){
           Log.d("AAAAA","Shownotes: NOTELIST IS NULL");
       }

        for (Note note : notes) {
            Log.i("AAAAA","NoteLIstFormat get note from note list");
            View noteVIew = getLayoutInflater().inflate(R.layout.item_note,container,false);

            TextView description = noteVIew.findViewById(R.id.note_description);
//            description.setText("DESCR");
            String descriptoon = note.getDescription();
            description.setText(descriptoon);

            TextView date = noteVIew.findViewById(R.id.note_date_creation);
//            date.setText("DATE");

            date.setText(note.getDate().toString());

            Log.i("AAAAA","note Text = "+ note.getText().toString());
            String long40SymbText = String.copyValueOf(note.getText().toString().toCharArray(),0, COUNT_OUTPUT_LONG_DESCRIPTION_CHARS);
//            String long40SymbText = "MAIN TEXt";
            TextView text = noteVIew.findViewById(R.id.note_text);
            text.setText(long40SymbText);

            ImageView closeButton = noteVIew.findViewById(R.id.button_close);
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(requireContext(),view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_close,popupMenu.getMenu());
                    popupMenu.show();

                    PopupMenu.OnMenuItemClickListener del = new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId()==R.id.id_menu_item__delete){
//                                presenter.deleteNote(note);
                                Toast.makeText(requireContext(), "DELETED", Toast.LENGTH_SHORT).show();

                            }
                            return false;
                        }
                    };



                    popupMenu.setOnMenuItemClickListener(del);
                }
            });


            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
//                        Bundle bundle = new Bundle();
//                        bundle.putParcelable(EXTRA_PARAM,note);
//
//                        getParentFragmentManager()
//                                .setFragmentResult(NOTE_SELECTED,bundle);
//
//                    } else {
                       getParentFragmentManager()
                               .beginTransaction()
                               .replace(R.id.main_activity_note_list, NoteEditFragment.newInstance(note))
                               .addToBackStack("")
                               .commitAllowingStateLoss();
//                    }

             

                }
            });

           Log.i("AAAAA","NoteLIstFormat add note");
            container.addView(noteVIew);
        }
        Toolbar.OnMenuItemClickListener menuItemClickListener = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                presenter.action(item.getItemId());
                Toast.makeText(requireContext(), "New Note", Toast.LENGTH_SHORT).show();
                return false;
            }
        };


      MaterialToolbar toolbar = getView().findViewById(R.id.app_bar_list_note);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);


    }
}
