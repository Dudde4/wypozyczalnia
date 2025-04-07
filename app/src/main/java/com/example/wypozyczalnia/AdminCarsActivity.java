package com.example.wypozyczalnia;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminCarsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_cars_layout);

        // Obsługa nawigacji
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cars);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.cars) {
                    return true;
                } else if (itemId == R.id.users) {
                    startActivity(new Intent(AdminCarsActivity.this, SearchActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.logout) {
                    startActivity(new Intent(AdminCarsActivity.this, AccountActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        // Obsługa kliknięcia w obrazek samochodu
        ShapeableImageView carView = findViewById(R.id.myImageView);
        carView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(AdminCarsActivity.this, CarActivity.class);
                //startActivity(intent);
            }
        });

        // Cofanie do poprzedniego widoku
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCarsActivity.this, AdminAddCarActivity.class);
                startActivity(intent);
            }
        });
    }
}
