package com.example.thinkpad.thi;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by SBE on 3/31/2018.
 */

public class SongDb extends SQLiteOpenHelper {

    public static final String tableName = "Song";
    public static final String song_code = "song_code";
    public static final String song_name = "song_name";
    public static final String singer = "singer";
    public static final String time = "time";

    public SongDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = String.format("Create table if not exists %s (%s Text Primary key, %s Text,%s Text,%s Text);", tableName, song_code, song_name, singer, time);

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        //Tạo lại
        onCreate(db);
    }

    void addSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(song_code, song.getSong_code());
        values.put(song_name, song.getSong_name());
        values.put(singer, song.getSinger());
        values.put(time, song.getTime());

        db.insert(tableName, null, values);
        db.close();
    }

    void editSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(song_code, song.getSong_code());
        values.put(song_name, song.getSong_name());
        values.put(singer, song.getSinger());
        values.put(time, song.getTime());

        db.update(tableName, values, song_code + "=?", new String[]{song.getSong_code() + ""});
        db.close();
    }

    void deleteSong(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + tableName + " where " + song_code + " = " + id + ";";
        db.execSQL(sql);
        db.close();
    }

    public List<Song> getAllSong() {
        List<Song> songs = new ArrayList<>();
        String sql = "select * from " + tableName + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Song song = new Song(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                songs.add(song);
            }
        }
        return songs;
    }
}