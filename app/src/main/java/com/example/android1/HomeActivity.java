package com.example.android1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private CheckBox croissantCheckBox, donutCheckBox, eclairCheckBox;
    private RadioGroup sizeGroup;
    private Button incrementBtn, decrementBtn, placeOrderBtn;
    private TextView quantityText, priceText, ratingText;
    private RatingBar ratingBar;

    private int quantity = 0;
    private int pricePerItem = 100;  // BDT 100 per item
    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);  // Ensure this is the name of your layout file

        // Initialize Views
        croissantCheckBox = findViewById(R.id.croissant);
        donutCheckBox = findViewById(R.id.donut);
        eclairCheckBox = findViewById(R.id.eclair);
        sizeGroup = findViewById(R.id.sizeGroup);
        incrementBtn = findViewById(R.id.incrementQty);
        decrementBtn = findViewById(R.id.decrementQty);
        placeOrderBtn = findViewById(R.id.placeOrder);
        quantityText = findViewById(R.id.quantityText);
        priceText = findViewById(R.id.priceText);
        ratingText = findViewById(R.id.ratingText);
        ratingBar = findViewById(R.id.ratingBar);

        // Set up quantity increment and decrement
        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementQuantity();
            }
        });

        decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementQuantity();
            }
        });

        // Set up RatingBar change listener
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingText.setText("Rating: " + rating);
            }
        });

        // Set up order button
        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTotalPrice();
            }
        });
    }

    private void incrementQuantity() {
        quantity++;
        updateQuantityAndPrice();
    }

    private void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
        updateQuantityAndPrice();
    }

    private void updateQuantityAndPrice() {
        quantityText.setText(String.valueOf(quantity));
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = quantity * pricePerItem;

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
