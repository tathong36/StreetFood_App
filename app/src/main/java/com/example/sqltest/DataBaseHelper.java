package com.example.sqltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String RESTAURANT_TABLE = "RESTAURANT_TABLE";
    public static final String COL_ID = "ID";
    public static final String COL_RESTAURANT_NAME = "RESTAURANT_NAME";
    public static final String COL_LOCALIZATION = "LOCALIZATION";
    public static final String COL_ACTIVE_RESTAURANT = "ACTIVE_RESTAURANT";

    //contructor
    public DataBaseHelper(@Nullable Context  context) // other parameter are passed hard coded
    {
        super(context, "restaurant.db", null, 1); // default value for factory is null
    }

    // Fist to when acces dataBase
    @Override
    public void onCreate(SQLiteDatabase db) {
        // !!!!!!standard sql statement to cReate new tab PAY ATTENTION TO SPACE AT begining and end except the last one
        String createTableStatement = "CREATE TABLE " + RESTAURANT_TABLE +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_RESTAURANT_NAME + " TEXT, " +
                COL_LOCALIZATION + " INTEGER, " +
                COL_ACTIVE_RESTAURANT + " BOOL)";
        db.execSQL(createTableStatement);

    }

    // when need new feature
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(RestaurantModel RestaurantModel) {
        SQLiteDatabase db = this.getWritableDatabase(); // attribue from SQLiteOpenHelper
        ContentValues cv = new ContentValues(); // similar to hasmap
        cv.put(COL_RESTAURANT_NAME, RestaurantModel.getName()); // associate variable to value
        cv.put(COL_LOCALIZATION, RestaurantModel.getLocalization());
        cv.put(COL_ACTIVE_RESTAURANT, RestaurantModel.isActive());

        long insert = db.insert(RESTAURANT_TABLE, null, cv);
        db.close(); // Closing database connection
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean deleteOne(RestaurantModel restaurantModel){
        //find and delete
        SQLiteDatabase db= this.getWritableDatabase();
        String queryString = "DELETE FROM " + RESTAURANT_TABLE + " WHERE " + COL_ID + " = " + restaurantModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);
      //??
        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }


    public List<RestaurantModel> getAll(){
        List<RestaurantModel> returnList = new ArrayList<>();
        // get all data from database
        String queryString = "SELECT * FROM " + RESTAURANT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase(); // getWritableDatabase will lock the database to other user 
        Cursor cursor = db.rawQuery(queryString, null);// return cursor = result. selectionArg : prepare vallue

        if(cursor.moveToFirst()){
            //loop through and creat a new restaurant object then put them in return list
            do{
                int restaurandID = cursor.getInt(0);
                String restaurantName= cursor.getString(1);
                int restaurantLocalization = cursor.getInt(2);
                boolean restaurantActive = cursor.getInt(3) == 1 ? true : false ;

                RestaurantModel restaurantModel= new RestaurantModel(restaurandID, restaurantName, restaurantLocalization, restaurantActive);
                returnList.add(restaurantModel);

            }while (cursor.moveToNext());//move to the next if not empty
        }
        else{
            //do not add anything if empty list;
        }
        //clean up by closing
        cursor.close();
        db.close();
        return returnList;

    }





}
