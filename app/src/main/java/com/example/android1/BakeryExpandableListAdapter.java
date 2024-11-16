package com.example.android1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
public class BakeryExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> bakeryCategories;
    private HashMap<String, List<BakeryItem>> bakeryItems;

    public BakeryExpandableListAdapter(Context context, List<String> bakeryCategories, HashMap<String, List<BakeryItem>> bakeryItems) {
        this.context = context;
        this.bakeryCategories = bakeryCategories;
        this.bakeryItems = bakeryItems;
    }

    @Override
    public int getGroupCount() {
        return bakeryCategories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return bakeryItems.get(bakeryCategories.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return bakeryCategories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return bakeryItems.get(bakeryCategories.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_item_layout, parent, false);
        }

        TextView groupTitleTextView = convertView.findViewById(R.id.groupName);
        groupTitleTextView.setText(groupTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        BakeryItem item = (BakeryItem) getChild(groupPosition, childPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_item_layout, parent, false);
        }

        TextView childNameTextView = convertView.findViewById(R.id.childItemName);
        TextView childPriceTextView = convertView.findViewById(R.id.childItemPrice);
        ImageView childImageView = convertView.findViewById(R.id.childItemImage);

        childNameTextView.setText(item.getName());
        childPriceTextView.setText("Price: $" + item.getPrice());
        childImageView.setImageResource(item.getImageResId());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
