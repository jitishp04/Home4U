package com.example.home4u.music;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SongsListAdapter extends ArrayAdapter<SongInfo> {
    private final String TAG = SongsListAdapter.class.getSimpleName();
    private final LayoutInflater inflater;
    private final List<SongInfo> songs;

    public SongsListAdapter(@NonNull Context context, int resource, List<SongInfo> songs) {
        super(context, resource, songs);

        this.songs = songs;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.nameView = convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SongInfo songInfo = songs.get(pos);
        viewHolder.nameView.setText(songInfo.getName());
        Log.e(TAG, songInfo.getName());

        return convertView;
    }

    private static class ViewHolder{
        TextView nameView;
    }
}
