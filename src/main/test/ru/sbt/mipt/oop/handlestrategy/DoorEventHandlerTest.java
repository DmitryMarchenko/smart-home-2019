package ru.sbt.mipt.oop.handlestrategy;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomeobjects.Door;
import ru.sbt.mipt.oop.smarthomeobjects.Light;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventHandlerTest {

    @Test
    void openAllDoors() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        try {
            SmartHome smartHome = provider.getSmartHome();
            DoorEventHandler lightEventHandler = new DoorEventHandler(smartHome);
            for (Room room: smartHome.getRooms()) {
                for (Door door: room.getDoors()) {
                    SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, door.getId());
                    lightEventHandler.handle(event);
                }
            }
            for (Room room: smartHome.getRooms()) {
                for (Door door: room.getDoors()) {
                    assertTrue(door.isOpen());
                }
            }
        } catch (IOException exc) {
            System.out.println("File reading error!");
            assert(false);
        }
    }

    @Test
    void closeAllDoors() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        try {
            SmartHome smartHome = provider.getSmartHome();
            DoorEventHandler lightEventHandler = new DoorEventHandler(smartHome);
            for (Room room: smartHome.getRooms()) {
                for (Door door: room.getDoors()) {
                    SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, door.getId());
                    lightEventHandler.handle(event);
                }
            }
            for (Room room: smartHome.getRooms()) {
                for (Door door: room.getDoors()) {
                    assertFalse(door.isOpen());
                }
            }
        } catch (IOException exc) {
            System.out.println("File reading error!");
            assert(false);
        }
    }
}