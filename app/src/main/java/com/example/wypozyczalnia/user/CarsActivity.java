package com.example.wypozyczalnia.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wypozyczalnia.R;
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

        // Odbierz wartość z SearchActivity
        String miejsceOdbioru = getIntent().getStringExtra("MIEJSCE_ODBIORU");

        // Lista marek samochodów
        List<String> markaList = new ArrayList<>();
        markaList.add("Marka");
        markaList.add("Erbe");
        markaList.add("Orin");
        markaList.add("Toya");
        markaList.add("BMW");

        // Lista cen
        List<String> cenaList = new ArrayList<>();
        cenaList.add("Cena");
        cenaList.add("do 150 zł");
        cenaList.add("150 - 200 zł");
        cenaList.add("200+ zł");

        // Lista miejsc odbioru (domyślna + ewentualnie nowa wartość z SearchActivity)
        List<String> odbiorList = new ArrayList<>();
        odbiorList.add("Odbiór");
        odbiorList.add("Wasilkow");
        odbiorList.add("Bialystok");
        odbiorList.add("Studzianki");

        // Dodaj wpisaną lokalizację, jeśli nie jest pusta i nie istnieje już w liście
        if (miejsceOdbioru != null && !miejsceOdbioru.isEmpty() && !odbiorList.contains(miejsceOdbioru)) {
            odbiorList.add(miejsceOdbioru);
        }

        // Inicjalizacja adapterów z warunkowym kolorem tekstu
        ArrayAdapter<String> markaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, markaList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                // Ustaw kolor: szary dla pozycji 0 ("Marka"), czarny dla reszty
                text.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }
        };
        markaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> cenaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cenaList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }
        };
        cenaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> odbiorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, odbiorList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(position == 0 ? Color.GRAY : Color.BLACK);
                return view;
            }
        };
        odbiorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Ustaw adaptery
        markaSpinner.setAdapter(markaAdapter);
        cenaSpinner.setAdapter(cenaAdapter);
        odbiorSpinner.setAdapter(odbiorAdapter);

        // Ustaw wybraną opcję w Spinnerze na wpisaną wartość (jeśli istnieje)
        if (miejsceOdbioru != null && !miejsceOdbioru.isEmpty()) {
            int pozycja = odbiorAdapter.getPosition(miejsceOdbioru);
            if (pozycja >= 0) {
                odbiorSpinner.setSelection(pozycja);
            }
        }

        // Obsługa nawigacji
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cars);

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

        // Obsługa kliknięcia w obrazek samochodu
        ShapeableImageView carView = findViewById(R.id.myImageView);
        carView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarsActivity.this, CarActivity.class);
                startActivity(intent);
            }
        });
    }
}