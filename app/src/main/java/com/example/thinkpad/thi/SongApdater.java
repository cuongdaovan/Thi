package com.example.thinkpad.thi;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by THINKPAD on 03-May-18.
 */

public class SongApdater extends BaseAdapter implements Filterable{
    private List<Song> songs;
    private Activity context;

    public SongApdater(List<Song> songs, Activity context) {
        this.songs = songs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Song getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView ==null){
            convertView =context.getLayoutInflater().inflate(R.layout.song_item,null);
            viewHolder=new ViewHolder();
            viewHolder.song_name=convertView.findViewById(R.id.song);
            viewHolder.singer=convertView.findViewById(R.id.singer);
            viewHolder.time=convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Song song=  getItem(position);
        viewHolder.song_name.setText(song.getSong_name());
        viewHolder.singer.setText(song.getSinger());
        viewHolder.time.setText(song.getTime());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                List<Song> ketQua = new ArrayList<>();

                for (Song bh :
                        songs) {
                    if (bh.getSinger().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        ketQua.add(bh);
                    }
                }

                filterResults.values = ketQua;
                filterResults.count = ketQua.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                songs.clear();
                songs.addAll((List<Song>) results.values);
                notifyDataSetChanged();
            }
        };
    }
    private static class ViewHolder{
        TextView song_name, singer,time;
    }
}
