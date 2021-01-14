package com.pharos.a2_NoteApp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.pharos.a2_NoteApp.models.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormFragment extends Fragment {
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        view.findViewById(R.id.btnSave).setOnClickListener(v -> save());
    }

    private void save() {
        String text = editText.getText().toString().trim();
        String time = getTime();
        Bundle bundle = new Bundle();

        Note note = new Note(text,time);

        bundle.putSerializable("note", note);
        getParentFragmentManager().setFragmentResult("rk_form", bundle);
        close();
    }
    
    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigateUp();
    }
    private String getTime(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return dateFormat.format(date);
    }

}