package com.example.wypozyczalnia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_layout);

        // Pobieramy przycisk
        Button registerButton = findViewById(R.id.registerButton);

        // Pobieramy pola tekstowe
        EditText imieInput = findViewById(R.id.imieInput);
        EditText nazwiskoInput = findViewById(R.id.nazwiskoInput);
        EditText emailInput = findViewById(R.id.emailInput);
        EditText hasloInput = findViewById(R.id.hasloInput);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Przycisk rejestracji
        registerButton.setOnClickListener(v -> {
            String imie = imieInput.getText().toString();
            String nazwisko = nazwiskoInput.getText().toString();
            String email = emailInput.getText().toString();
            String haslo = hasloInput.getText().toString();

            // Tworzymy linię danych (CSV-style)
            String dane = imie + ";" + nazwisko + ";" + email + ";" + haslo + "\n";

            // Zapisujemy dane do pliku
            try {
                FileOutputStream fos = openFileOutput("dupa.txt", MODE_APPEND); // dopisuje dane
                fos.write(dane.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Przechodzimy do ekranu logowania
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
