package com.example.wypozyczalnia.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.wypozyczalnia.R;

public class CarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.car_layout);

        Switch switchButton = findViewById(R.id.switchButton);
        ConstraintLayout containerSwitch2 = findViewById(R.id.containerSwitch2);
        ConstraintLayout containerSwitch1 = findViewById(R.id.containerSwitch1);

        Button backButton = findViewById(R.id.backButton);

        switchButton.setChecked(false);
        containerSwitch2.setVisibility(View.GONE);
        containerSwitch1.setVisibility(View.VISIBLE);

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarActivity.this, CarsActivity.class);
                startActivity(intent);
            }
        });
    }
}
