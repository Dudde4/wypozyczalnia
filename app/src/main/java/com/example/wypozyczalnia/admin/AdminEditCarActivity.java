package com.example.wypozyczalnia.admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wypozyczalnia.R;

import java.util.ArrayList;
import java.util.List;

public class AdminEditCarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_edit_car_layout);

        //lista gwiazdek
        List<String> starList = new ArrayList<>();
        starList.add("1");
        starList.add("2");
        starList.add("3");
        starList.add("4");
        starList.add("5");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, starList) {
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
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //podpisanie adaptera pod spinner
        Spinner starSpinner = findViewById(R.id.starSpinner);
        starSpinner.setAdapter(adapter);
        starSpinner.setSelection(0);


        //przycisk cofnij
        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEditCarActivity.this, AdminCarsActivity.class);
                startActivity(intent);
            }
        });
    }
}
