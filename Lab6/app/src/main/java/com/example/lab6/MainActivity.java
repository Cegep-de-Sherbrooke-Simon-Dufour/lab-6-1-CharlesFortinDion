package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private String nom;
    private String email;
    private ArrayList<Item> items = new ArrayList<Item>(Arrays.asList(
            new Item("Bob", "bobestcool@gmail.com"),
            new Item("Alice", "ACC@gmail.com"),
            new Item("Bob", "bobestcool@gmail.com"),
            new Item("Alice", "ACC@gmail.com"),
            new Item("Bob", "bobestcool@gmail.com")
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ViewModelDemo viewModel = newe ViewModelProvider(this).get(ViewModelDemo.class)

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter adapter = new UserAdapter(items);
        adapter.submitList(new ArrayList<Item>(items));
        recyclerView.setAdapter(adapter);

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(ajouterUser);
    }

    ActivityResultLauncher<Intent> getNewUser = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult> () {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    if (result.getResultCode() != 0) {
                        nom = data.getStringExtra("nom");
                        email = data.getStringExtra("email");
                        items.add(new Item(nom, email));

                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        UserAdapter adapter = new UserAdapter(items);
                        adapter.submitList(new ArrayList<Item>(items));
                        recyclerView.setAdapter(adapter);
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