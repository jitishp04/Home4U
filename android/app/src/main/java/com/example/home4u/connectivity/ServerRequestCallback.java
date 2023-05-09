package com.example.home4u.connectivity;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface ServerRequestCallback {
    void onMakeConnection(HttpURLConnection urlConnection) throws IOException;

    void onConnectionError();
}
