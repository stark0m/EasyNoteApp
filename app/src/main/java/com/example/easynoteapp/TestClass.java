package com.example.easynoteapp;

import static com.example.easynoteapp.ui.list.NotesListFragment.EXTRA_PARAM;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.easynoteapp.R;
import com.example.easynoteapp.domain.Note;
import com.example.easynoteapp.ui.edit.NoteEditFragment;
import com.example.easynoteapp.ui.list.NotesListFragment;
import com.google.android.material.appbar.MaterialToolbar;

public class TestClass extends Fragment {
    /*
    * public class ThemePresenterImpl implements ThemePresenter {
    private Context context;
    private SharedPreferences preferences;

    private static final String SETTINGS_FILE_NAME = "THEMES";
    private static final String THEMM_KEY = "THEME";



    @Override
    public ThemeEnum setTheme(Context context) {

        preferences = context.getSharedPreferences(SETTINGS_FILE_NAME,Context.MODE_PRIVATE);
        String savedTheme = preferences.getString(THEMM_KEY,"default");
        ThemeEnum newTheme = ThemeEnum.ONE;

        for (ThemeEnum th : ThemeEnum.values() ) {
            if (savedTheme.equals(th.getKey())){
                return th;
            }

        }
        return newTheme;


        }

    @Override
    public void saveTheme(ThemeEnum theme) {
        preferences.edit().putString(THEMM_KEY,theme.getKey()).apply();
    }

}*/
    /* findViewById(R.id.open_manual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri google = Uri.parse("https://yandex.ru");

                Intent googleIntent = new Intent(Intent.ACTION_VIEW, google);

                startActivity(Intent.createChooser(googleIntent, ""));
            }
        });
        */
    /*
    Intent intent = new Intent(CalculatorActivity.this, TextInputActivity.class);
                startActivity(intent);
    * */



    /*MainActivity DrawerLayout -> fragment-> NavigationView*/
/*PopupMenu popupMenu = new PM(requireContext(),viev);
popupMenu.GetMenuInflater().inflate(R.menu.menuname,popumMenu.getMenu());
popupMenu.show();
* */
    /*CoordinatorLayout
    AppbarLayout
    MaterialToolbar
    Nested ->behavor=...scroling_view_beh...
    * */
    /* MainActivity
                Toolbar toolbar = findViewById(r.id.tolbar);
                setSupportActionBar(toolbar);

            *@Override
      public boolean onCreateOptionsMenu(Menu menu) {
          getMenuInflater().inflate(R.menu.menu_name,menu);
          return super.onCreateOptionsMenu(menu);
      }
  */
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.app_bar_edit_note){
            Toast.makeText(this, "text", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(item.getItemId()==R.id.app_bar_edit_note2){
            Toast.makeText(this, "text2", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*

        @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { //Frame
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);

/
 */
    public TestClass() {
        super(R.layout.fragment_note_edit);
    }
//++++++++++++++++++++++++++++++++++++++++++++

//++++++++++++++++++++++++++++++++++++++++++++
        /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("BBBBB","Creation view");
        return inflater.inflate(R.layout.fragment_note_edit,container,false);

    }*/

    //+++++++++++++++++++++fragment 1+++++++++++++++++++++++
    // noteVIew.setOnClickListener(new View.OnClickListener() {
    //                @Override
    //                public void onClick(View view) {
    //
    //                    if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
    //                        Bundle bundle = new Bundle();
    //                        bundle.putParcelable(EXTRA_PARAM,note);
    //
    //                        getParentFragmentManager()
    //                                .setFragmentResult(NOTE_SELECTED,bundle);
    //
    //                    } else {
    //                        NoteEditActivity.start(requireContext(),note);
    //                    }
    //
    //
    //
    //                }
    //            });
    //++++++++++++++++++++++++fragment 2++++++++++++++++++++
    public void test() {
        //  getChildFragmentManager()
        getParentFragmentManager()
                .setFragmentResultListener(NotesListFragment.NOTE_SELECTED, getViewLifecycleOwner()/*this*/, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Log.i("BBBBB", "SWITCED NOTE");
                        Note note = result.getParcelable(EXTRA_PARAM);


                    }
                });
//++++++++++++++++++++++++++++++++++++++++++++
//        Note note = getIntent().getParcelableExtra(NotesListFragment.EXTRA_PARAM);
//
//        getSupportFragmentManager().
//                beginTransaction().
//                replace(R.id.edit_note_activity, NoteEditFragment.newInstance(note))
//                .commit();
//    }
//++++++++++++++++++++++++++++++++++++++++++++
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(requireContext(), "HELLO", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
//++++++++++++++++++++++++++++++++++++++++++++
        /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("BBBBB","Creation view");
        return inflater.inflate(R.layout.fragment_note_edit,container,false);

    }*/
//++++++++++++++++++++++++++++++++++++++++++++
        Bundle arguments = getArguments();
        Toast.makeText(requireContext(), "text", Toast.LENGTH_SHORT).show();

        getParentFragmentManager().popBackStack();
        /*
        Fragment fragment = getParentFragmentManager().findFragmentById(R.id.XXX)
        Fragment fragment = getParentFragmentManager().findFragmentByTag(TAG);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, NoteEditFragment.newInstance(note))
                .add(R.id.container, NoteEditFragment.newInstance(note),TAG)
                .replace(R.id.container, NoteEditFragment.newInstance(note))
                .addToBackStack("")
                .remove(fragment)
                .hide(fragment)
                .show(fragment)
                .commit();
                .commitNow();
                */
        //++++++++++++++++++++++++++++++++++++++++++++
    }

    public static void main(String[] args) {

    }
}
