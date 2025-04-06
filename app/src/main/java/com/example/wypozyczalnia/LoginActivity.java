package com.example.wypozyczalnia;

import android.content.Intent;
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

import java.io.FileInputStream;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_layout);

        // Pobieramy widoki
        TextView registerTextView = findViewById(R.id.RegistertextView); // Link do rejestracji
        EditText emailInput = findViewById(R.id.emailInput);  // Email
        EditText hasloInput = findViewById(R.id.hasloInput);  // Hasło
        Button loginButton = findViewById(R.id.loginButton);  // Przycisk logowania

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

        // Kliknięcie na przycisk logowania
        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String haslo = hasloInput.getText().toString();

            if (checkCredentials(email, haslo)) {
                // Jeśli dane są poprawne, przechodzimy do SearchActivity
                Intent intent = new Intent(LoginActivity.this, SearchActivity.class);
                startActivity(intent);
            } else {
                // Jeśli dane są niepoprawne, wyświetlamy komunikat
                Toast.makeText(LoginActivity.this, "Błędny email lub hasło", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Funkcja sprawdzająca dane w pliku
    private boolean checkCredentials(String email, String haslo) {
        try {
            FileInputStream fis = openFileInput("dupa.txt");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            String fileContents = new String(buffer);

            // Szukamy linii, która zawiera email i hasło
            String[] lines = fileContents.split("\n");
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String savedEmail = parts[2];  // Zakładając, że email jest na 3. pozycji
                    String savedHaslo = parts[3];  // Zakładając, że hasło jest na 4. pozycji

                    if (savedEmail.equals(email) && savedHaslo.equals(haslo)) {
                        return true;  // Zgodność danych
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;  // Brak zgodności danych
    }
}
