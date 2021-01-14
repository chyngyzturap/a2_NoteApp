package com.pharos.a2_NoteApp.ui.board;

import android.app.backup.BackupDataInput;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pharos.a2_NoteApp.OnItemClickListener;
import com.pharos.a2_NoteApp.Prefs;
import com.pharos.a2_NoteApp.R;

public class BoardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);
        BoardAdapter adapter = new BoardAdapter();
        viewPager.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                close();
            }

            @Override
            public void longClick(int position) {

            }
        });

    }
    private void close() {
        Prefs prefs = new Prefs(requireContext());
        prefs.saveBoardStatus();
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigateUp();
    }
}