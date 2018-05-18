package com.example.thinkpad.thi;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SongDb songDb;
    SongApdater songApdater;
    List<Song> songs;
    EditText txtSearch;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputMethodManager mgr = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        mgr.showSoftInput(txtSearch, InputMethodManager.SHOW_IMPLICIT);
        this.initDB();
        this.addControls();
        this.event();
    }

    public void event(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xoa");
                final Song song=songs.get(position);
                builder.setMessage("ban co muon xoa "+ song.getSong_name()+" khong?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        songDb.deleteSong(song.getSong_code());
                        songs.remove(position);
                        songApdater.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                songApdater.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void addControls(){
        txtSearch=findViewById(R.id.txtsearch);
        listView=findViewById(R.id.songs);
        songs=songDb.getAllSong();
        songApdater=new SongApdater(songs,this);
        listView.setAdapter(songApdater);
    }
    private void initDB(){
        songDb=new SongDb(this,"db_song",null,1);
        Song song=new Song("1","phut cuoi","bang kieu","5.00");
        Song song1=new Song("2","Noi nay co anh","song tung","5.00");
        Song song2=new Song("3","Chay ngay di","song tung","5.00");
        Song song3=new Song("4","con co be be","bang kieu","5.00");
        songDb.addSong(song);
        songDb.addSong(song1);
        songDb.addSong(song2);
        songDb.addSong(song3);
    }
}
