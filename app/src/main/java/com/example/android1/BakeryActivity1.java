package com.example.android1;

import android.os.Bundle;
import android.widget.ExpandableListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BakeryActivity1 extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private BakeryExpandableListAdapter listAdapter;
    private List<String> categoryList;
    private HashMap<String, List<BakeryItem>> itemList;  // Corrected this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakery1); // Make sure this layout is correct

        // Initialize the ExpandableListView
        expandableListView = findViewById(R.id.expandableListView);

        // Prepare data for the list
        prepareListData();

        // Set up the adapter
        listAdapter = new BakeryExpandableListAdapter(this, categoryList, itemList);
        expandableListView.setAdapter(listAdapter);

        // Handle group click events
        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            // Group clicked
            return false; // Returning false allows default behavior
        });

        // Handle child click events
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            // Handle child click here
            BakeryItem selectedItem = itemList.get(categoryList.get(groupPosition)).get(childPosition);
            // Example: Toast or action
            return false;
        });
    }

    // Prepare data for ExpandableListView
    private void prepareListData() {
        categoryList = new ArrayList<>();
        itemList = new HashMap<>();

        // Categories
        categoryList.add("Breads");
        categoryList.add("Cakes");
        categoryList.add("Pastries");

        // Items under categories (using BakeryItem objects)
        List<BakeryItem> breads = new ArrayList<>();
        breads.add(new BakeryItem("Croissant", 2.5, R.drawable.croissant));
        breads.add(new BakeryItem("Baguette", 1.8, R.drawable.baguette));
        breads.add(new BakeryItem("Sourdough", 3.0, R.drawable.sourdough));

        List<BakeryItem> cakes = new ArrayList<>();
        cakes.add(new BakeryItem("Chocolate Cake", 5.0, R.drawable.chocolate_cake));
        cakes.add(new BakeryItem("Vanilla Sponge", 4.0, R.drawable.vanilla_sponge));
        cakes.add(new BakeryItem("Red Velvet", 6.0, R.drawable.red_velvet));

        List<BakeryItem> pastries = new ArrayList<>();
        pastries.add(new BakeryItem("Eclair", 2.2, R.drawable.eclair));
        pastries.add(new BakeryItem("Danish", 2.0, R.drawable.danish));
        pastries.add(new BakeryItem("Puff Pastry", 2.8, R.drawable.puff_pastry));

        // Map data to categories
        itemList.put(categoryList.get(0), breads); // Breads
        itemList.put(categoryList.get(1), cakes);  // Cakes
        itemList.put(categoryList.get(2), pastries); // Pastries
    }
}
