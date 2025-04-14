package com.example.wypozyczalnia.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wypozyczalnia.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_user_layout);

        String email = getIntent().getStringExtra("EMAIL");

        //przycisk cofnij
        Button backButton = findViewById(R.id.backButton);
        TextView emailTextView = findViewById(R.id.imieInput);
        emailTextView.setText(email);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminUserActivity.this, AdminUsersActivity.class);
                startActivity(intent);
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> {
            File file = new File(getExternalFilesDir(null), "uzytkownicy.txt");

            if (!file.exists()) {
                Toast.makeText(this, "Plik nie istnieje", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                List<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    // Pomijamy linię z tym emailem
                    if (!line.contains(email)) {
                        lines.add(line);
                    }
                }

                reader.close();

                // Zapisujemy nową wersję pliku bez usuniętego użytkownika
                FileWriter writer = new FileWriter(file, false);
                for (String l : lines) {
                    writer.write(l + "\n");
                }
                writer.close();

                Toast.makeText(this, "Użytkownik usunięty", Toast.LENGTH_SHORT).show();

                // Wróć do listy użytkowników
                Intent intent = new Intent(AdminUserActivity.this, AdminUsersActivity.class);
                startActivity(intent);
                finish();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Błąd podczas usuwania", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
