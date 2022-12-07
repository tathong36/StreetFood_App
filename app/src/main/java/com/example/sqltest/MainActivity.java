package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // reference to control on layout
    Button btn_add, btn_viewAll;
    EditText et_Name, et_localization;
    Switch sw_active;
    ListView lv_restaurantList;

    // class member variable
    DataBaseHelper dataBaseHelper;
    ArrayAdapter restaurantArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add= findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_Name = findViewById(R.id.et_Name);
        et_localization = findViewById(R.id.et_Localization);
        sw_active = findViewById(R.id.sw_active);
        lv_restaurantList = findViewById(R.id.lv_restaurantList);

         dataBaseHelper = new DataBaseHelper(MainActivity.this);
        //no need to send dataBaseHelper as parameter
         showRestaurantOnListView();

        //button listeners self contain
        btn_add.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                RestaurantModel restaurantModel;
                try{
                    restaurantModel = new RestaurantModel(-1, et_Name.getText().toString(), Integer.parseInt(et_localization.getText().toString()),sw_active.isChecked());
                    Toast.makeText(MainActivity.this,restaurantModel.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this,"Error age", Toast.LENGTH_SHORT).show();
                    restaurantModel = new RestaurantModel(-1, "error", 0,false);//default if missing localization

                }
                //context is the reference to the application Mainactiviy, if put only this it will refer to  onClick listener
               // DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                try {
                    boolean success = dataBaseHelper.addOne(restaurantModel);
                    Toast.makeText(MainActivity.this, "success insert =" + success, Toast.LENGTH_SHORT).show();
                }
                catch (Exception t){
                    Toast.makeText(MainActivity.this,"Error insert", Toast.LENGTH_SHORT).show();
                }
                showRestaurantOnListView();


            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                // Simple display
                showRestaurantOnListView();

                // Toast.makeText(MainActivity.this, all.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lv_restaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RestaurantModel clickedRestaurant = (RestaurantModel) adapterView.getItemAtPosition(i); // specify which restaurant position "i" was click
                dataBaseHelper.deleteOne(clickedRestaurant);
                showRestaurantOnListView();
                Toast.makeText(MainActivity.this, "deleted ="+ clickedRestaurant.toString() , Toast.LENGTH_SHORT).show();


            }
        });



    }

    private void showRestaurantOnListView() {
        restaurantArrayAdapter = new ArrayAdapter<RestaurantModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAll());
        lv_restaurantList.setAdapter(restaurantArrayAdapter);
    }
}