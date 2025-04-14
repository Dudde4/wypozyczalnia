package com.example.wypozyczalnia;
import com.example.wypozyczalnia.testy.RegistrationChecker;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
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

            if (imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty()) {
                Toast.makeText(this, "Wszystkie pola muszą być wypełnione!", Toast.LENGTH_SHORT).show();
                return;
            }

            String dane = imie + ";" + nazwisko + ";" + email + ";" + haslo + "\n";

            if (isExternalStorageWritable()) {
                File externalFile = new File(getExternalFilesDir(null), "uzytkownicy.txt");

                // Sprawdzenie, czy email już istnieje
                if (externalFile.exists()) {
                    try {
                        byte[] buffer = new byte[(int) externalFile.length()];
                        FileInputStream fis = new FileInputStream(externalFile);
                        fis.read(buffer);
                        fis.close();

                        String fileContents = new String(buffer);
                        if (RegistrationChecker.emailExists(fileContents, email)) {
                            Toast.makeText(this, "Konto z takim adresem e-mail już istnieje!", Toast.LENGTH_LONG).show();
                            return;
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Błąd przy sprawdzaniu istniejących użytkowników", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Dopisywanie nowych danych
                try (FileOutputStream fos = new FileOutputStream(externalFile, true)) {
                    fos.write(dane.getBytes());
                    Toast.makeText(this, "Zarejestrowano pomyślnie!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Błąd zapisu pliku", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Pamięć zewnętrzna niedostępna", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
