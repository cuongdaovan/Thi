package com.example.thinkpad.thi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by THINKPAD on 13-Apr-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String Dbname="song.db";
    private static  final String TABLE_NAME="song";
    private static final Integer version=1;
    public DatabaseHelper(Context context) {
        super(context, Dbname, null, version);
            //SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContacts.Place.CreateSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertData(String song_name){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
//        contentValues.put(DatabaseContacts.Place.song_code,song_code);
        contentValues.put(DatabaseContacts.Place.song_name,song_name);
        long result=db.insert(DatabaseContacts.Place.tableName,null,contentValues);
        if(result==-1){
            return  false;
        }else return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+DatabaseContacts.Place.tableName,null);
        return cursor;
    }
}
