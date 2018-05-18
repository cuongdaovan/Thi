package com.example.thinkpad.thi.database;

import android.content.Context;

/**
 * Created by THINKPAD on 13-Apr-18.
 */

public class DatabaseManager
{
    DatabaseHelper a;
    public DatabaseManager(Context context){
        a=new DatabaseHelper(context);
    }
    public static DatabaseManager GetInstance(Context context){
        return new DatabaseManager(context);
    }

}