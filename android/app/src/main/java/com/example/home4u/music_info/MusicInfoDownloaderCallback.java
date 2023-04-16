package com.example.home4u.music_info;

import org.json.JSONObject;

public interface MusicInfoDownloaderCallback {
    void onSuccess(JSONObject jsonObject);
    void onFailure();
}
