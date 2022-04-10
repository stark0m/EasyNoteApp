package com.example.easynoteapp.ui.list;

import static com.example.easynoteapp.ui.list.NotesListFragment.COUNT_OUTPUT_LONG_DESCRIPTION_CHARS;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private ArrayList<Note> data = new ArrayList<>();
    public Fragment fragment;
    private Note noteSelected;
    private int noteSelectedIndex;

    public void deleteSelectedNote(){
        data.remove(noteSelectedIndex);
    }
    public Note getNoteSelected() {
        return noteSelected;
    }

    public void setNoteSelected(Note noteSelected) {
        this.noteSelected = noteSelected;
    }

    public int getNoteSelectedIndex() {
        return noteSelectedIndex;
    }

    public void setNoteSelectedIndex(int noteSelectedIndex) {
        this.noteSelectedIndex = noteSelectedIndex;
    }

    public RecyclerAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public interface ItemClicked {
         void onItemClicked(Note note);
         void onItemLongClicked(Note note,int index);

    }


    public ItemClicked getiC() {
        return iC;
    }


    public void setiC(ItemClicked iC) {
        this.iC = iC;
    }


    private ItemClicked iC;

    public void getData(ArrayList<Note> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        String text = data.get(position).getText();
        String description = data.get(position).getDescription();
        String date = data.get(position).getDate().toString();


        holder.description.setText(description);
        String long40SymbText = String.copyValueOf(text.toCharArray(), 0, COUNT_OUTPUT_LONG_DESCRIPTION_CHARS);
        holder.text.setText(long40SymbText);
        holder.date.setText(date);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView description;
        TextView text;
        TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.note_description);
            text = itemView.findViewById(R.id.note_text);
            date = itemView.findViewById(R.id.note_date_creation);

            fragment.registerForContextMenu(itemView);


            itemView.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (iC != null) {
                        iC.onItemClicked(data.get(getAdapterPosition()));
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (iC != null) {
                        iC.onItemLongClicked(data.get(getAdapterPosition()),getAdapterPosition());
                    }
                    view.showContextMenu();

                    return true;
                }
            });

        }
    }
}
