package com.pharos.a2_NoteApp.ui;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pharos.a2_NoteApp.R;

public class ProfileFragment extends Fragment {

    private ActivityResultLauncher<String> mGetContent;
    private ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        imageView = view.findViewById(R.id.imageView);
        imageView.setOnClickListener(v -> openGallery());

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> imageView.setImageURI(uri));
    }

    private void openGallery() {
        mGetContent.launch("image/*");
    }
}