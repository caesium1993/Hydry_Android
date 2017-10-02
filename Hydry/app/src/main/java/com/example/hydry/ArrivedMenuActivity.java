package com.example.hydry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//import com.example.zumoappname.R;

public class ArrivedMenuActivity extends Activity {

    private ArrayList<Category> menu = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrived_menu);

        /*
        main menu for the arrived travellers
         */
        initializeMenu();

        CategoryAdapter mCategoryAdapter =
                new CategoryAdapter(ArrivedMenuActivity.this, R.layout.menu_list, menu);

        ListView listView = (ListView) findViewById(R.id.list_arr_menu);
        listView.setAdapter(mCategoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Category category = menu.get(position);
                String category_name = category.getName();

                if (category_name.equals("Mobile Communication")){
                    toMobile(view);
                } else if(category_name.equals("Supermarkets")){
                    toSupermarket(view);
                } else if(category_name.equals("Transport")){
                    toTransport(view);
                } else if(category_name.equals("What's for dinner?")){
                    toShake(view);
                }
                else{
                    Toast.makeText(ArrivedMenuActivity.this, category.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void toMobile (View view){
        Intent intent_mobile = new Intent(ArrivedMenuActivity.this, MobileActivity.class);
        startActivity(intent_mobile);
    }

    public void toSupermarket (View view){
        Intent intent_supermarket = new Intent(ArrivedMenuActivity.this, SupermarketActivity.class);
        startActivity(intent_supermarket);
    }

    public void toTransport (View view){
        Intent intent_trsnsport = new Intent(ArrivedMenuActivity.this, TransportActivity.class);
        startActivity(intent_trsnsport);
    }

    public void toShake (View view){
        Intent intent_shake = new Intent(ArrivedMenuActivity.this, ShakeActivity.class);
        startActivity(intent_shake);
    }

    private void initializeMenu(){

       String[] categories = {"Mobile Communication",  "Supermarkets","Transport", "Banks",
               "Accommodation", "Shopping", "Medicine", "Beauty", "Touristic Attractions","What's for dinner?"};

       for (String c:categories){
           Category category = new Category(c);
           menu.add(category);
           Log.d("ArrivedMenuActivity",category.toString());
       }
        /*Category mobile = new Category("Mobile Communication");
        menu.add(mobile);
        Category q1 = new Category("Transport");
        menu.add(q1);
        Category q2 = new Category("Supermarkets");
        menu.add(q2);*/

    }
}
