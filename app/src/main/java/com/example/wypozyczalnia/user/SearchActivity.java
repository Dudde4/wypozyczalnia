package com.example.wypozyczalnia.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.wypozyczalnia.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_layout);


        // Lista godzin (8:00 - 16:00)
        List<String> hoursList = new ArrayList<>();
        hoursList.add("Godzina"); // Domyślna szara opcja
        for (int hour = 8; hour <= 16; hour++) {
            String hourString = String.format("%02d:00", hour);
            hoursList.add(hourString);
        }

        // Adapter dla Spinnerów z warunkowym kolorem tekstu
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hoursList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(position == 0 ? Color.GRAY : Color.BLACK); // Szary dla "Godzina", czarny dla reszty
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
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner timeSpiner1 = findViewById(R.id.timeSpiner1);
        Spinner timeSpiner2 = findViewById(R.id.timeSpiner2);

        timeSpiner1.setAdapter(adapter);
        timeSpiner2.setAdapter(adapter);

        // Ustaw domyślną opcję "Godzina" (pozycja 0)
        timeSpiner1.setSelection(0);
        timeSpiner2.setSelection(0);

        Switch switchButton = findViewById(R.id.switchButton);
        ConstraintLayout containerSwitch2 = findViewById(R.id.containerSwitch2);
        ConstraintLayout containerSwitch1 = findViewById(R.id.containerSwitch1);

        // Obsługa switcha (zwrot w innym miejscu)
        switchButton.setChecked(false);
        containerSwitch2.setVisibility(View.GONE);
        containerSwitch1.setVisibility(View.VISIBLE);

        //nawigacja
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                containerSwitch1.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                containerSwitch2.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        // Obsługa przycisku wyszukiwania
        Button searchButton = findViewById(R.id.searchButton);
        EditText odbiorEditText = findViewById(R.id.odbiorEditText); // Miejsce odbioru

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchActivity.this, CarsActivity.class);
                    // Przekazanie miejsca odbioru do CarsActivityiza
                    String miejsceOdbioru = odbiorEditText.getText().toString();
                    intent.putExtra("MIEJSCE_ODBIORU", miejsceOdbioru);
                startActivity(intent);
            }
        });

        // Nawigacja dolna
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.search) {
                    return true;
                } else if (itemId == R.id.cars) {
                    startActivity(new Intent(SearchActivity.this, CarsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.account) {
                    startActivity(new Intent(SearchActivity.this, AccountActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        //czy odbior na miejscu czy nie
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    containerSwitch1.setVisibility(ConstraintLayout.GONE);
                    containerSwitch2.setVisibility(ConstraintLayout.VISIBLE);
                } else {
                    containerSwitch1.setVisibility(ConstraintLayout.VISIBLE);
                    containerSwitch2.setVisibility(ConstraintLayout.GONE);
                }
            }
        });

        if (switchButton.isChecked()) {
            containerSwitch1.setVisibility(ConstraintLayout.GONE);
            containerSwitch2.setVisibility(ConstraintLayout.VISIBLE);
        } else {
            containerSwitch1.setVisibility(ConstraintLayout.VISIBLE);
            containerSwitch2.setVisibility(ConstraintLayout.GONE);
        }
    }
}