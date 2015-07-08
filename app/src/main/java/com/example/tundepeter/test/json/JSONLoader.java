package com.example.tundepeter.test.json;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JSONLoader {
    private static final int READ_TIME_OUT = 20000;
    private static final String REQUEST_METHOD = "GET";

    public List getJsonFromUrl(String stringUrl) throws IOException {
        JSONParser parser = new JSONParser();
        return parser.getJsonList(makeRequest(stringUrl));
    }

    private InputStream makeRequest(String stringUrl) throws IOException{
        URL url = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(READ_TIME_OUT);
        connection.setRequestMethod(REQUEST_METHOD);
        connection.connect();
        int responseCode = connection.getResponseCode();
        InputStream inputStream = null;

        switch (responseCode) {
            case HttpURLConnection.HTTP_ACCEPTED:
            case HttpURLConnection.HTTP_OK:
                inputStream = connection.getInputStream();
                break;
            default:
                // some error occured
                break;
        }

        return inputStream;
    }
}
