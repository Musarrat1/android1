package com.example.android1; // Change this to your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    private Spinner bakeryItemsSpinner;
    private Button submitOrder;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form); // Change to your layout file

        bakeryItemsSpinner = findViewById(R.id.bakeryItemsSpinner);
        submitOrder = findViewById(R.id.submitOrder);
        outputText = findViewById(R.id.outputText);

        // Populate Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bakery_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bakeryItemsSpinner.setAdapter(adapter);

        // Handle button click
        submitOrder.setOnClickListener(v -> {
            // Gather data from form
            String customerName = ((EditText) findViewById(R.id.customerName)).getText().toString();
            String orderId = ((EditText) findViewById(R.id.orderId)).getText().toString();
            String quantity = ((EditText) findViewById(R.id.quantity)).getText().toString();
            String email = ((EditText) findViewById(R.id.email)).getText().toString();
            String mobileNumber = ((EditText) findViewById(R.id.mobileNumber)).getText().toString();

            // Validate inputs
            if (!validateName(customerName)) {
                Toast.makeText(this, "Invalid name!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!validateEmail(email)) {
                Toast.makeText(this, "Invalid email!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!validatePhoneNumber(mobileNumber)) {
                Toast.makeText(this, "Invalid phone number!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create intent to navigate to OrderActivity
            Intent intent = new Intent(FormActivity.this, OrderActivity.class);
            intent.putExtra("customerName", customerName);
            intent.putExtra("orderId", orderId);
            intent.putExtra("quantity", quantity);
            intent.putExtra("email", email);
            intent.putExtra("mobileNumber", mobileNumber);
            intent.putExtra("bakeryItem", bakeryItemsSpinner.getSelectedItem().toString());

            // Start OrderActivity
            startActivity(intent);
        });
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^(\\+?\\d{1,3}[-.\\s]?)?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$");
    }

    private boolean validateName(String name) {
        return name.matches("^[A-Za-z\\s-]{1,100}$");
    }

    private boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
}
