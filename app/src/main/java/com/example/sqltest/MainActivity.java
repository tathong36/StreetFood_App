package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

   //The ArrayAdapter fits in between an ArrayList (data source) and the ListView (visual representation) and configures two aspects:
   //
   //Which array to use as the data source for the list
   //How to convert any given item in the array into a corresponding View object
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



            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //open to a new page to display
                Intent intent = new Intent(MainActivity.this,ActivityDisplayList.class);
                startActivity(intent);



                // Toast.makeText(MainActivity.this, all.toString(), Toast.LENGTH_SHORT).show();
            }
        });





    }


}