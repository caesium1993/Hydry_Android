package com.example.hydry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2017/10/5.
 */

public class ItemAdapter extends ArrayAdapter<Items> {
    private int resourceId;
    //private int[] imageids = { R.drawable.coles_kangrooburger, R.drawable.coles_croissant,
            //R.drawable.coles_gippsland, R.drawable.coles_honeyham };
    public ItemAdapter(Context context, int textViewResourceId, List<Items> obj){
        super (context, textViewResourceId,obj);
        resourceId = textViewResourceId;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        Items item = getItem(position);
        TextView itemname = (TextView) view.findViewById(R.id.itemtitle);//???
        TextView itemdescription = (TextView) view.findViewById(R.id.itemdescription);
        ImageView itemimage = (ImageView) view.findViewById(R.id.itemimage);
        itemname.setText(item.getItemname());
        itemdescription.setText(item.getItemdescription()+item.getPrefered());
        itemimage.setImageResource(item.getItemimage());
        //itemimage.findViewById(R.id.itemimage);
        return view;
    }

    /*public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemAdapter itemAdapter = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView itemname = (TextView) view.findViewById(R.id.itemtitle);//???
        TextView itemdescription = (TextView) view.findViewById(R.id.itemdescription);
        ImageView itemimage = (ImageView) view.findViewById(R.id.itemimage);
        itemname.setText(itemAdapter.getName());
        itemdescription.setText();
        return view;
    }*/
}
