package com.example.hydry;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
//import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by caesium on 29/09/2017.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {
    private int resourceId;

    public CategoryAdapter(Context context, int textViewResourceId, List<Category> obj){
        super (context, textViewResourceId, obj);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Category category = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView categoryName = (TextView) view.findViewById(R.id.text_menu_item);//???
        categoryName.setText(category.getName());
        return view;
    }
}
