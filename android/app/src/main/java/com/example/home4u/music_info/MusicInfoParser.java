package com.example.home4u.music_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MusicInfoParser {

    public static String[] getSongs(JSONObject jsonObject){
        try {
            final JSONArray songs = jsonObject.getJSONArray("songs");

            final int songAmt = songs.length();
            final String[] array = new String[songAmt];

            for (int i = 0; i < songAmt; i++) {
                final JSONObject songItem = songs.getJSONObject(i);
                array[i] = songItem.getString("name");
            }

            return array;
        } catch (JSONException e) {
            e.printStackTrace();
            return new String[]{};
        }
    }
}
