package com.example.wypozyczalnia;

import android.os.Bundle;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.ArrayList;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_layout);

        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);

        List<String> hoursList = new ArrayList<>();
        for (int hour = 8; hour <= 16; hour++) {
            String hourString = String.format("%02d:00", hour);
            hoursList.add(hourString);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hoursList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);

    }
}
