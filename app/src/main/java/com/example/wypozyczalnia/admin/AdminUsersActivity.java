package com.example.wypozyczalnia.admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wypozyczalnia.LoginActivity;
import com.example.wypozyczalnia.R;
import com.example.wypozyczalnia.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AdminUsersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_users_layout);

        loadUsersFromFile();

        // Obsługa nawigacji
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.users);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.users) {
                    return true;
                } else if (itemId == R.id.cars) {
                    startActivity(new Intent(AdminUsersActivity.this, AdminCarsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.logout) {
                    startActivity(new Intent(AdminUsersActivity.this, LoginActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });
    }

    private void loadUsersFromFile() {
        LinearLayout usersContainer = findViewById(R.id.usersContainer);
        File file = new File(getExternalFilesDir(null), "uzytkownicy.txt");

        if (!file.exists()) {
            Toast.makeText(this, "Plik users.txt nie istnieje", Toast.LENGTH_SHORT).show();
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(this);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";"); // Rozdziel dane

                if (parts.length >= 3) { // Sprawdź czy jest email
                    String email = parts[2];

                    View userView = inflater.inflate(R.layout.admin_user_item, usersContainer, false); // użyj user_item.xml!

                    TextView emailTextView = userView.findViewById(R.id.emailTextView);
                    emailTextView.setText(email);

                    emailTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(AdminUsersActivity.this, AdminUserActivity.class);
                            intent.putExtra("EMAIL", email);
                            startActivity(intent);
                        }
                    });

                    usersContainer.addView(userView);
                }
            }
            reader.close();



        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Błąd odczytu pliku", Toast.LENGTH_SHORT).show();
        }
    }
}
