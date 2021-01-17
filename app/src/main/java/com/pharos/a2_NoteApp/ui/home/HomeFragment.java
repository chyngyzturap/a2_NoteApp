package com.pharos.a2_NoteApp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.pharos.a2_NoteApp.OnItemClickListener;
import com.pharos.a2_NoteApp.Prefs;
import com.pharos.a2_NoteApp.R;
import com.pharos.a2_NoteApp.models.Note;

public class HomeFragment extends Fragment implements PopupMenu.OnMenuItemClickListener{

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private ImageButton imageButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // this method runs once only when you launch the app
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter(getContext());
        add10();
    }

    private void add10() {
        for (int i = 12; i > 0; i--) {
            adapter.addItem(new Note("Эта запись # " + i, ""));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        Prefs prefs = new Prefs(getContext());

        view.findViewById(R.id.fab).setOnClickListener(v -> openForm());
        setFragmentListener();
        initList();
        view.findViewById(R.id.imageButtonPopup).setOnClickListener(v -> showPopup(v));
    }

    private void initList() {
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {

                Note note = adapter.getItem(position);
                Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void longClick(int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                        .setTitle("Данная строка будет удалена")
                        .setMessage("Удалить?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.remove(position);
                            }
                        })
                        .setNegativeButton("Нет", null);
                alert.create().show();
            }
        });
    }

    private void openForm() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.formFragment);
    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("rk_form", getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = (Note) result.getSerializable("note");
                        adapter.addItem(note);
                    }
                });
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.erase_home:
                Prefs prefsEr = new Prefs(requireContext());
                prefsEr.erasePreferences();
                return true;

            default:
                return false;

        }

    }
    private void showPopup(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.settings_erase_menu);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
}}