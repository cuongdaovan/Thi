package com.example.thinkpad.thi.database;

/**
 * Created by THINKPAD on 13-Apr-18.
 */

public class DatabaseContacts {
    //dinh ngia bang
    //ten cac truong
    //cau tao bang
    public static class Place{
        public static final String tableName="song";
        public static final String song_code="Song_code";
        public static final String song_name="Song_name";
        public static  final String singer="Singer";
        public static final String time="Time";
        public static final String CreateSql="CREATE TABLE "+tableName+"  ( "+song_code+" INTEGER PRIMARY KEY AUTOINCREMENT , "+song_name+" nvarchar(50), "+ singer+" nvarchar(50), "+time+" nvarchar(50) )";
    }

}
