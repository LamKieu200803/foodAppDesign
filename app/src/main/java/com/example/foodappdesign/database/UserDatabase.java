package com.example.foodappdesign.database;

import android.content.Context;

import androidx.room.Database;

import com.example.foodappdesign.dao.UserDAO;

/*@Database(entities = {User.class},version = 1)
public  abstract  class UserDatabase extends RoomDatabase {
    public static final String DB_NAME="USER.DB";
    public static  UserDatabase instance;
    public static  synchronized UserDatabase getInstance(Context context){
        if(instance==null){
            instance=Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
    public  abstract UserDAO userDAO();
}*/
