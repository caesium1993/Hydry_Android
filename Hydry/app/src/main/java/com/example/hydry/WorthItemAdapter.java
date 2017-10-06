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
 * Created by lenovo on 2017/10/6.
 */

public class WorthItemAdapter extends ArrayAdapter<Items> {
    private int resourceId;
    public WorthItemAdapter(Context context, int textViewResourceId, List<Items> obj){
        super (context, textViewResourceId,obj);
        resourceId = textViewResourceId;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        Items item = getItem(position);
        TextView itemname = (TextView) view.findViewById(R.id.worthitemtitle);//???
        TextView itemdescription = (TextView) view.findViewById(R.id.worthitemdescription);
        ImageView itemimage = (ImageView) view.findViewById(R.id.worthitemimage);
        itemname.setText(item.getItemname());
        itemdescription.setText(item.getItemdescription()+"\nPrefered: "+item.getPrefered());
        itemimage.setImageResource(item.getItemimage());
        //itemimage.findViewById(R.id.itemimage);
        return view;
    }
}
