package com.example.android1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BakeryAdapter extends BaseAdapter {

    private Context context;
    private List<BakeryItem> bakeryItems;

    public BakeryAdapter(Context context, List<BakeryItem> bakeryItems) {
        this.context = context;
        this.bakeryItems = bakeryItems;
    }

    @Override
    public int getCount() {
        return bakeryItems.size();
    }

    @Override
    public Object getItem(int position) {
        return bakeryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bakery_item_layout, parent, false);
        }

        BakeryItem bakeryItem = bakeryItems.get(position);

        // Get the views from the layout
        TextView nameText = convertView.findViewById(R.id.bakeryItemName);
        TextView priceText = convertView.findViewById(R.id.bakeryItemPrice);
        ImageView imageView = convertView.findViewById(R.id.bakeryItemImage);

        // Set the data for each item
        nameText.setText(bakeryItem.getName());
        priceText.setText("$" + bakeryItem.getPrice());
        imageView.setImageResource(bakeryItem.getImageResId());

        return convertView;
    }
}
