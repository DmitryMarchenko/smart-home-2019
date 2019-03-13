package ru.sbt.mipt.oop.handlestrategy;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomeobjects.Light;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LightEventHandlerTest {

    @org.junit.jupiter.api.Test
    void turnOnAllLights() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        try {
            SmartHome smartHome = provider.getSmartHome();
            LightEventHandler lightEventHandler = new LightEventHandler(smartHome);
            for (Room room: smartHome.getRooms()) {
                for (Light light: room.getLights()) {
                    SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, light.getId());
                    lightEventHandler.handle(event);
                }
            }
            for (Room room: smartHome.getRooms()) {
                for (Light light: room.getLights()) {
                    assertTrue(light.isOn());
                }
            }
        } catch (IOException exc) {
            System.out.println("File reading error!");
            assert(false);
        }
    }

    @org.junit.jupiter.api.Test
    void turnOffAllLights() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        try {
            SmartHome smartHome = provider.getSmartHome();
            LightEventHandler lightEventHandler = new LightEventHandler(smartHome);
            for (Room room: smartHome.getRooms()) {
                for (Light light: room.getLights()) {
                    SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, light.getId());
                    lightEventHandler.handle(event);
                }
            }
            for (Room room: smartHome.getRooms()) {
                for (Light light: room.getLights()) {
                    assertFalse(light.isOn());
                }
            }
        } catch (IOException exc) {
            System.out.println("File reading error!");
            assert(false);
        }
    }
}