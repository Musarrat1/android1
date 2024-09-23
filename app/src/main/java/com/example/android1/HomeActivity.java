package com.example.android1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView quantityText, seekBarValue, priceText, ratingText;
    private CheckBox croissant, donut, eclair;
    private RadioGroup sizeGroup;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch expressDeliverySwitch;
    private int quantity = 0;
    private final int basePrice = 100; // Price per item

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Views
        quantityText = findViewById(R.id.quantityText);
        seekBarValue = findViewById(R.id.seekBarValue);
        priceText = findViewById(R.id.priceText);
        ratingText = findViewById(R.id.ratingText);
        Button incrementQty = findViewById(R.id.incrementQty);
        Button decrementQty = findViewById(R.id.decrementQty);
        Button placeOrder = findViewById(R.id.placeOrder);
        croissant = findViewById(R.id.croissant);
        donut = findViewById(R.id.donut);
        eclair = findViewById(R.id.eclair);
        sizeGroup = findViewById(R.id.sizeGroup);
        SeekBar quantitySeekBar = findViewById(R.id.quantitySeekBar);
        expressDeliverySwitch = findViewById(R.id.expressDeliverySwitch);
        RatingBar ratingBar1 = findViewById(R.id.ratingBar);

        // Handle Quantity Increment
        incrementQty.setOnClickListener(v -> {
            quantity++;
            displayQuantity();
        });

        // Handle Quantity Decrement
        decrementQty.setOnClickListener(v -> {
            if (quantity > 0) {
                quantity--;
                displayQuantity();
            } else {
                Toast.makeText(HomeActivity.this, "Quantity cannot be negative", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle SeekBar Changes
        quantitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText("Selected: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Handle Switch Toggle
        expressDeliverySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(HomeActivity.this, "Express Delivery Enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(HomeActivity.this, "Express Delivery Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle RatingBar Changes
        ratingBar1.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> ratingText.setText("Rating: " + rating));

        // Handle Place Order Button
        placeOrder.setOnClickListener(v -> {
            int selectedSizeId = sizeGroup.getCheckedRadioButtonId();
            if (quantity == 0 || selectedSizeId == -1 || (!croissant.isChecked() && !donut.isChecked() && !eclair.isChecked())) {
                Toast.makeText(HomeActivity.this, "Please select items, size, and quantity", Toast.LENGTH_SHORT).show();
            } else {
                int totalPrice = quantity * basePrice;
                if (expressDeliverySwitch.isChecked()) {
                    totalPrice += 50; // Add express delivery fee
                }
                priceText.setText("Price: BDT " + totalPrice);
                Toast.makeText(HomeActivity.this, "Order placed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to display the current quantity and price
    private void displayQuantity() {
        quantityText.setText(String.valueOf(quantity));
        // Calculate and update the price based on the current quantity
        int totalPrice = quantity * basePrice;
        if (expressDeliverySwitch.isChecked()) {
            totalPrice += 50; // Add express delivery fee if enabled
        }
        priceText.setText("Price: BDT " + totalPrice);
    }
}
