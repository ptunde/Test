package com.example.tundepeter.test.json;

import android.util.JsonReader;

import com.example.tundepeter.test.model.Person;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    private final static String ENCODING = "UTF-8";
    private final static String JSON_NAME = "name";
    private final static String JSON_DESCRIPTION = "position";
    private final static String JSON_SMALL_PIC = "smallpic";
    private final static String JSON_LARGE_PIC = "lrgpic";

    public List getJsonList(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, ENCODING));
        try {
            return readMessagesArray(reader);
        }
        finally {
            inputStream.close();
        }
    }

    public List readMessagesArray(JsonReader reader) throws IOException {
        List messages = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    public Person readMessage(JsonReader reader) throws IOException {
        String name = null;
        String description = null;
        String smallPic = null;
        String largePic = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String readingName = reader.nextName();
            if (readingName.equals(JSON_NAME)) {
                name = reader.nextString();
            } else if (readingName.equals(JSON_DESCRIPTION)) {
                description = reader.nextString();
            } else if (readingName.equals(JSON_SMALL_PIC)) {
                smallPic = reader.nextString();
            } else if (readingName.equals(JSON_LARGE_PIC)) {
                largePic = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Person(name, description, smallPic, largePic);
    }
}
