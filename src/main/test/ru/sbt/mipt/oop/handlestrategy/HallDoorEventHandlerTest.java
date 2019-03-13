package ru.sbt.mipt.oop.handlestrategy;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.commandsendstrategy.PrintCommandSender;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomeobjects.Door;
import ru.sbt.mipt.oop.smarthomeobjects.Light;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventHandlerTest {

    @Test
    void closeHallDoorWhenAllLightsTurnOn() {
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
                HallDoorEventHandler hallDoorEventHandler = new HallDoorEventHandler(smartHome, new PrintCommandSender());
                boolean hallExist = false;
                for (Room room: smartHome.getRooms()) {
                    for (Door door: room.getDoors()) {
                        if (room.getName().equals("hall")) {
                            hallExist = true;
                            SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, door.getId());
                            hallDoorEventHandler.handle(event);
                            break;
                        }
                    }
                }
                if (!hallExist) {
                    return;
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

    @Test
    void closeHallDoorWhenAllLightsTurnOff() {
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
            HallDoorEventHandler hallDoorEventHandler = new HallDoorEventHandler(smartHome, new PrintCommandSender());
            boolean hallExist = false;
            for (Room room: smartHome.getRooms()) {
                for (Door door: room.getDoors()) {
                    if (room.getName().equals("hall")) {
                        hallExist = true;
                        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, door.getId());
                        hallDoorEventHandler.handle(event);
                        break;
                    }
                }
            }
            if (!hallExist) {
                return;
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