package com.example.android1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private int quantity = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);  // Ensure this is the name of your layout file

        // Initialize Views
        CheckBox croissantCheckBox = findViewById(R.id.croissant);
        CheckBox donutCheckBox = findViewById(R.id.donut);
        CheckBox eclairCheckBox = findViewById(R.id.eclair);
        Button incrementBtn = findViewById(R.id.incrementQty);
        Button decrementBtn = findViewById(R.id.decrementQty);
        Button placeOrderBtn = findViewById(R.id.placeOrder);
        TextView quantityText = findViewById(R.id.quantityText);
        TextView priceText = findViewById(R.id.priceText);
        TextView ratingText = findViewById(R.id.ratingText);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        // Set up quantity increment and decrement using lambdas
        incrementBtn.setOnClickListener(view -> {
            incrementQuantity();
            updateQuantityAndPrice(quantityText, priceText, croissantCheckBox, donutCheckBox, eclairCheckBox);
        });

        decrementBtn.setOnClickListener(view -> {
            decrementQuantity();
            updateQuantityAndPrice(quantityText, priceText, croissantCheckBox, donutCheckBox, eclairCheckBox);
        });

        // Set up RatingBar change listener using lambda
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) ->
                ratingText.setText("Rating: " + rating));

        // Set up order button using lambda
        placeOrderBtn.setOnClickListener(view ->
                calculateTotalPrice(priceText, croissantCheckBox, donutCheckBox, eclairCheckBox));
    }

    private void incrementQuantity() {
        quantity++;
    }

    private void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    private void updateQuantityAndPrice(TextView quantityText, TextView priceText,
                                        CheckBox croissantCheckBox, CheckBox donutCheckBox, CheckBox eclairCheckBox) {
        quantityText.setText(String.valueOf(quantity));
        calculateTotalPrice(priceText, croissantCheckBox, donutCheckBox, eclairCheckBox);
    }

    @SuppressLint("SetTextI18n")
    private void calculateTotalPrice(TextView priceText,
                                     CheckBox croissantCheckBox, CheckBox donutCheckBox, CheckBox eclairCheckBox) {
        // BDT 100 per item
        int pricePerItem = 100;
        int totalPrice = quantity * pricePerItem;

        // Check selected items and adjust price accordingly
        if (croissantCheckBox.isChecked()) {
            totalPrice += quantity * pricePerItem; // Adding croissant cost
        }
        if (donutCheckBox.isChecked()) {
            totalPrice += quantity * pricePerItem; // Adding donut cost
        }
        if (eclairCheckBox.isChecked()) {
            totalPrice += quantity * pricePerItem; // Adding eclair cost
        }

        // Display total price
        priceText.setText("BDT " + totalPrice);
    }
}
