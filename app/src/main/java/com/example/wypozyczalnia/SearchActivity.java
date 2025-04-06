package com.example.wypozyczalnia;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Spinner;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.ArrayList;
import androidx.activity.EdgeToEdge;


import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_layout);

        Spinner timeSpinner1 = findViewById(R.id.timeSpiner1);
        Spinner timeSpinner2 = findViewById(R.id.timeSpiner2);

        List<String> hoursList = new ArrayList<>();
        for (int hour = 8; hour <= 16; hour++) {
            String hourString = String.format("%02d:00", hour);
            hoursList.add(hourString);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hoursList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        timeSpinner1.setAdapter(adapter);
        timeSpinner2.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.search);

        Switch switchButton = findViewById(R.id.switchButton);
        ConstraintLayout containerSwitch2 = findViewById(R.id.containerSwitch2);
        ConstraintLayout containerSwitch1 = findViewById(R.id.containerSwitch1);

        Button searchButton = findViewById(R.id.searchButton);

        switchButton.setChecked(false);
        containerSwitch2.setVisibility(View.GONE);
        containerSwitch1.setVisibility(View.VISIBLE);

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

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, CarsActivity.class);
                startActivity(intent);
            }
        });
    }
}
