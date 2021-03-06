package ru.sbt.mipt.oop.json;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeReader implements SmartHomeProvider {
    private final String filename;

    public JsonSmartHomeReader(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome getSmartHome(){
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(this.filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
