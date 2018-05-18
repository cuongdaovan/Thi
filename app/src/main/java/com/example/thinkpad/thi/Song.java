package com.example.thinkpad.thi;

/**
 * Created by THINKPAD on 03-May-18.
 */

public class Song {
    String song_code;
    String song_name;
    String singer;
    String time;
    public Song(){
        
    }

    public Song(String song_code, String song_name, String singer, String time) {
        this.song_code = song_code;
        this.song_name = song_name;
        this.singer = singer;
        this.time = time;
    }

    public String getSong_code() {
        return song_code;
    }

    public void setSong_code(String song_code) {
        this.song_code = song_code;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
