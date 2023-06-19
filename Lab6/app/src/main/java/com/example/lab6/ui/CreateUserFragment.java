package com.example.lab6.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.lab6.R;

import java.io.File;
import java.util.UUID;

public class CreateUserFragment extends Fragment {
    private Uri uri = Uri.parse("");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewModelUser viewModelUser =new ViewModelProvider(requireActivity()).get(ViewModelUser.class);

        ImageView imageView = view.findViewById(R.id.createUserPic);
        ImageButton takePicButton = view.findViewById(R.id.takePic);
        ImageButton chosePicButton = view.findViewById(R.id.choseGalleryPic);

        Button soumettreButton = view.findViewById(R.id.soumettreNewUser);
        soumettreButton.setOnClickListener(v -> {
            EditText nom = view.findViewById(R.id.editNom);
            EditText email = view.findViewById(R.id.editEmail);
            viewModelUser.addUser(nom.getText().toString(), email.getText().toString(), uri.toString());
            Navigation.findNavController(view).navigateUp();
        });

        ActivityResultLauncher<Uri> cameraResultLauncher = registerForActivityResult(
            new ActivityResultContracts.TakePicture(),
            pictureTaken -> {
                if (pictureTaken) {
                    imageView.setImageURI(uri);
                }
            }
        );

        ActivityResultLauncher<PickVisualMediaRequest> galleryResultLauncher = registerForActivityResult(
            new ActivityResultContracts.PickVisualMedia(),
            uri -> {
                int flags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                requireContext().getContentResolver().takePersistableUriPermission(uri,
                        flags);
                imageView.setImageURI(uri);
                this.uri = uri;
            }
        );


        takePicButton.setOnClickListener(v -> {
            File folder = new File(requireContext().getFilesDir(), "camera_images");
            if (!folder.exists()) folder.mkdirs();
            File file = new File(folder, UUID.randomUUID().toString() + ".png");
            uri = FileProvider.getUriForFile(requireContext(),requireContext().getPackageName() + ".provider", file);
            cameraResultLauncher.launch(uri);
        });

        chosePicButton.setOnClickListener(v -> {
            galleryResultLauncher.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        });




        Button annulerButton = view.findViewById(R.id.annulerCreate);
        annulerButton.setOnClickListener(v -> Navigation.findNavController(view).navigateUp());
    }
}