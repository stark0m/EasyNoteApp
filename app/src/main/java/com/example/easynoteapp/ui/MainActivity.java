package com.example.easynoteapp.ui;

import static com.example.easynoteapp.ui.listfragment.NotesListFragment.NOTE_CHANGED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;

import com.example.easynoteapp.R;
import com.example.easynoteapp.ui.listfragment.NotesListFragment;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity implements NavDrawable {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);

        getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.main_activity_note_list, NotesListFragment.NewInstance(null))
                .commit();


        getSupportFragmentManager()
                .setFragmentResultListener(NOTE_CHANGED, this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                        getSupportFragmentManager().
                                beginTransaction()
                                .replace(R.id.main_activity_note_list, NotesListFragment.NewInstance(result))
                                .commit();

                    }


                });


//59.56!
    }



    @Override
    public void setAppToolbar(MaterialToolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }
}