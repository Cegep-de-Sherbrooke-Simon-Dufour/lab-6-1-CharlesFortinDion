package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Button soumettreButton = findViewById(R.id.soumettreNewUser);
        soumettreButton.setOnClickListener(ajouterUser);
        Button annulerButton = findViewById(R.id.annulerCreate);
        annulerButton.setOnClickListener(annulerAjout);
    }

    View.OnClickListener ajouterUser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText nom = findViewById(R.id.editNom);
            EditText email = findViewById(R.id.editEmail);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("nom", nom.getText().toString());
            resultIntent.putExtra("email", email.getText().toString());

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    };

    View.OnClickListener annulerAjout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();

        }
    };
}