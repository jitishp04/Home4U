package com.example.home4u;

import org.json.JSONObject;

public interface MusicInfoDownloaderCallback {
    void onSuccess(JSONObject jsonObject);
    void onFailure();
}
