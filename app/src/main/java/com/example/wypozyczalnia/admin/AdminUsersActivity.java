package com.example.wypozyczalnia.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wypozyczalnia.LoginActivity;
import com.example.wypozyczalnia.R;
import com.example.wypozyczalnia.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminUsersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_users_layout);

        //obsluga klikniecia w email
        TextView emailTextView = findViewById(R.id.emailTextView);

        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminUsersActivity.this, AdminUserActivity.class);
                startActivity(intent);
            }
        });

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
}
