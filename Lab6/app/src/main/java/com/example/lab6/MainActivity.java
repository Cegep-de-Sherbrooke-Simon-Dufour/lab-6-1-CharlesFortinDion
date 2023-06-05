package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ViewModelUser viewModelUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModelUser = new ViewModelProvider(this).get(ViewModelUser.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter adapter = new UserAdapter(viewModelUser.getUsers().getValue());
        recyclerView.setAdapter(adapter);

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(ajouterUser);

        viewModelUser.getUsers().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                adapter.submitList(users);
            }
        });
    }

    ActivityResultLauncher<Intent> getNewUser = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult> () {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    if (result.getResultCode() != 0) {
                        viewModelUser.addUser(data.getStringExtra("nom"), data.getStringExtra("email"));
                    }
        }
    }
    );

    View.OnClickListener ajouterUser = new View.OnClickListener () {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CreateUser.class);
            getNewUser.launch(intent);
        }
    };
}