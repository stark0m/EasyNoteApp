package com.example.easynoteapp.ui.list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.domain.NotesRepositoryImpl;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {
    public static final int COUNT_OUTPUT_LONG_DESCRIPTION_CHARS = 40;
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

        container = view.findViewById(R.id.notes_list_container_layout);
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
            description.setText(note.getDescription());

            TextView date = noteVIew.findViewById(R.id.note_date_creation);
//            date.setText("DATE");
            date.setText(note.getDate().toString());

            Log.i("AAAAA","note Text = "+ note.getText().toString());
            String long40SymbText = String.copyValueOf(note.getText().toString().toCharArray(),0, COUNT_OUTPUT_LONG_DESCRIPTION_CHARS);
//            String long40SymbText = "MAIN TEXt";
            TextView text = noteVIew.findViewById(R.id.note_text);
            text.setText(long40SymbText);

           Log.i("AAAAA","NoteLIstFormat add note");
            container.addView(noteVIew);
        }
    }
}
