package com.example.android1;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BakeryActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private BakeryExpandableListAdapter expandableListAdapter;
    private List<String> bakeryCategories;
    private HashMap<String, List<BakeryItem>> bakeryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery1); // Ensure this is your correct layout file

        // Initialize ExpandableListView
        expandableListView = findViewById(R.id.expandableListView);

        // Prepare bakery categories and items
        prepareBakeryData();

        // Initialize the adapter
        expandableListAdapter = new BakeryExpandableListAdapter(this, bakeryCategories, bakeryItems);

        // Set the adapter for the ExpandableListView
        expandableListView.setAdapter(expandableListAdapter);
    }

    // Method to prepare data for bakery categories and items
    private void prepareBakeryData() {
        bakeryCategories = new ArrayList<>();
        bakeryItems = new HashMap<>();

        bakeryCategories.add("Pastries");
        bakeryCategories.add("Cakes");
        bakeryCategories.add("Cookies");

        // Adding bakery items under "Pastries"
        List<BakeryItem> pastries = new ArrayList<>();
        pastries.add(new BakeryItem("Croissant", 2.5, R.drawable.croissant));
        pastries.add(new BakeryItem("Donut", 1.2, R.drawable.donut));
        pastries.add(new BakeryItem("Eclair", 2.2, R.drawable.eclair));
        pastries.add(new BakeryItem("Danish", 2.0, R.drawable.danish));
        pastries.add(new BakeryItem("Puff Pastry", 2.8, R.drawable.puff_pastry));


        // Adding bakery items under "Cakes"
        List<BakeryItem> cakes = new ArrayList<>();
        cakes.add(new BakeryItem("Cupcake", 3.0, R.drawable.cupcake));

        // Adding bakery items under "Cookies"
        List<BakeryItem> cookies = new ArrayList<>();
        cookies.add(new BakeryItem("Chocolate Chip", 1.5, R.drawable.cookie));

        // Adding all items to the HashMap
        bakeryItems.put(bakeryCategories.get(0), pastries);
        bakeryItems.put(bakeryCategories.get(1), cakes);
        bakeryItems.put(bakeryCategories.get(2), cookies);
    }
}
