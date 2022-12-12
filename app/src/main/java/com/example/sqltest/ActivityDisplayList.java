package com.example.sqltest;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityDisplayList extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
    ListView lv2_restaurantList;

    //The ArrayAdapter fits in between an ArrayList (data source) and the ListView (visual representation) and configures two aspects:
    //
    //Which array to use as the data source for the list
    //How to convert any given item in the array into a corresponding View object
    ArrayAdapter restaurantArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        lv2_restaurantList = findViewById(R.id.lv2_restaurantList);

        dataBaseHelper = new DataBaseHelper(ActivityDisplayList.this);
        showRestaurantOnListView();


        lv2_restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RestaurantModel clickedRestaurant = (RestaurantModel) adapterView.getItemAtPosition(i); // specify which restaurant position "i" was click
                dataBaseHelper.deleteOne(clickedRestaurant);
                showRestaurantOnListView();
                Toast.makeText(ActivityDisplayList.this, "deleted ="+ clickedRestaurant.toString() , Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void showRestaurantOnListView() {
        restaurantArrayAdapter = new ArrayAdapter<RestaurantModel>(ActivityDisplayList.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAll());
        lv2_restaurantList.setAdapter(restaurantArrayAdapter);
    }
}