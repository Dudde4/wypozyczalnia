package com.example.wypozyczalnia;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CarsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cars_layout);

        Spinner markaSpinner = findViewById(R.id.markaSpinner);
        Spinner cenaSpinner = findViewById(R.id.cenaSpinner);
        Spinner odbiorSpinner = findViewById(R.id.odbiorSpinner);

        List<String> markaList = new ArrayList<>();
        markaList.add("Marka");
        markaList.add("Erbe");
        markaList.add("Orin");
        markaList.add("Toya");
        markaList.add("BMW");

        List<String> cenaList = new ArrayList<>();
        cenaList.add("Cena");
        cenaList.add("do 150 zł");
        cenaList.add("150 - 200 zł");
        cenaList.add("200+ zł");

        List<String> odbiorList = new ArrayList<>();
        odbiorList.add("Odbiór");
        odbiorList.add("Wasilkow");
        odbiorList.add("Bialystok");
        odbiorList.add("Studzianki");

// Adaptery
        ArrayAdapter<String> markaAdapter = new ArrayAdapter<>(CarsActivity.this, android.R.layout.simple_spinner_item, markaList);

        markaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> cenaAdapter = new ArrayAdapter<>(CarsActivity.this, android.R.layout.simple_spinner_item, cenaList);
        cenaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> odbiorAdapter = new ArrayAdapter<>(CarsActivity.this, android.R.layout.simple_spinner_item, odbiorList);
        odbiorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        markaSpinner.setAdapter(markaAdapter);
        cenaSpinner.setAdapter(cenaAdapter);
        odbiorSpinner.setAdapter(odbiorAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cars);

        ShapeableImageView carView = findViewById(R.id.myImageView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.cars) {
                    return true;
                } else if (itemId == R.id.search) {
                    startActivity(new Intent(CarsActivity.this, SearchActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.account) {
                    startActivity(new Intent(CarsActivity.this, AccountActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;


            }
        });

        carView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarsActivity.this, CarActivity.class);
                startActivity(intent);


            }
        });
    }
}
