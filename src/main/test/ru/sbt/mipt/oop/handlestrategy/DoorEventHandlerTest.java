package ru.sbt.mipt.oop.handlestrategy;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomeobjects.Door;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventHandlerTest {

    private void eventForEachDoor(SmartHome smartHome,
                                  DoorEventHandler doorEventHandler, SensorEventType doorOpen) {
        smartHome.execute(obj -> {
            if (!(obj instanceof Door)) {
                return;
            }
            Door door = (Door) obj;
            SensorEvent event = new SensorEvent(doorOpen, door.getId());
            doorEventHandler.handle(event);
        });
    }

    private void checkAllDoors(SmartHome smartHome, boolean mode) {
        smartHome.execute(obj -> {
            if (!(obj instanceof Door)) {
                return;
            }
            Door door = (Door) obj;
            if (mode) {
                assertTrue(door.isOpen());
            } else {
                assertFalse(door.isOpen());
            }
        });
    }

    @Test
    void openAllDoors() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        DoorEventHandler doorEventHandler = new DoorEventHandler(smartHome);
        eventForEachDoor(smartHome, doorEventHandler, SensorEventType.DOOR_OPEN);

        checkAllDoors(smartHome, true);
    }

    @Test
    void closeAllDoors() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        DoorEventHandler doorEventHandler = new DoorEventHandler(smartHome);
        eventForEachDoor(smartHome, doorEventHandler, SensorEventType.DOOR_CLOSED);

        checkAllDoors(smartHome, false);
    }
}