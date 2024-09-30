package com.example.android1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button homeButton, formButton;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the buttons
      // Button for HomeActivity
        formButton = findViewById(R.id.btn_form); // Button for BakeryFormActivity

        // Set an OnClickListener for HomeActivity


        // Set an OnClickListener for BakeryFormActivity
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Navigating to Form", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
