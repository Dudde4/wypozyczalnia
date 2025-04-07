package com.example.wypozyczalnia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {

    public boolean adminMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_layout);

        copyDefaultUsersFileIfNeeded();

        // Pobieramy widoki
        TextView registerTextView = findViewById(R.id.RegistertextView); // Link do rejestracji

        // Ustawienie paddingu dla system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Kliknięcie na rejestrację (przenosi do RegisterActivity)
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        EditText emailInput = findViewById(R.id.emailInput);  // Email
        EditText hasloInput = findViewById(R.id.hasloInput);  // Hasło
        Button loginButton = findViewById(R.id.loginButton);  // Przycisk logowania

        // Kliknięcie na przycisk logowania
        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String haslo = hasloInput.getText().toString();

            if (checkCredentials(email, haslo)) {
                if(adminMode){
                    Intent intent = new Intent(LoginActivity.this, AdminCarsActivity.class);
                    startActivity(intent);
                }
                else{
                    // Jeśli dane są poprawne, przechodzimy do SearchActivity
                    Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(intent);
                }

            } else {
                // Jeśli dane są niepoprawne, wyświetlamy komunikat
                Toast.makeText(LoginActivity.this, "Błędny email lub hasło", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Funkcja sprawdzająca dane w pliku
    private boolean checkCredentials(String email, String haslo) {
        File file = new File(getExternalFilesDir(null), "uzytkownicy.txt");

        if (!file.exists()) {
            Toast.makeText(this, "Brak zarejestrowanych użytkowników", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            String fileContents = new String(buffer);

            // Szukamy linii, która zawiera email i hasło
            String[] lines = fileContents.split("\n");
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String savedEmail = parts[2].trim();
                    String savedHaslo = parts[3].trim();

                    if (savedEmail.equals(email) && savedHaslo.equals(haslo)) {
                        if(savedEmail.equals("Admin@gmail.com"))
                        {
                            adminMode = true;
                            return true;
                        }
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Błąd podczas odczytu pliku", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void copyDefaultUsersFileIfNeeded() {
        // SharedPreferences do zapisania stanu
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isFileCopied = prefs.getBoolean("isFileCopied", false);

        if (!isFileCopied) {
            prefs.edit().putBoolean("isFileCopied", true).apply();
            File destFile = new File(getExternalFilesDir(null), "uzytkownicy.txt");
            try {
                InputStream is = getAssets().open("uzytkownicy.txt");
                FileOutputStream fos = new FileOutputStream(destFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }

                fos.close();
                is.close();

                // Ustawiamy flagę, że plik został już skopiowany
                prefs.edit().putBoolean("isFileCopied", true).apply();

                Toast.makeText(this, "Plik użytkowników został utworzony", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Błąd kopiowania pliku", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
