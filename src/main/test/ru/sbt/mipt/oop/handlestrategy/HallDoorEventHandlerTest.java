package ru.sbt.mipt.oop.handlestrategy;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.commandsendstrategy.PrintCommandSender;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomeobjects.Light;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventHandlerTest {

    private void eventForEachLight(SmartHome smartHome,
                                   LightEventHandler lightEventHandler, SensorEventType lightOn) {
        smartHome.execute(obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            SensorEvent event = new SensorEvent(lightOn, light.getId());
            lightEventHandler.handle(event);
        });
    }

    private void closeHallDoor(HallDoorEventHandler hallDoorEventHandler) {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");
        hallDoorEventHandler.handle(event);
    }

    private void checkAllLights(SmartHome smartHome) {
        smartHome.execute(obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            assertFalse(light.isOn());
        });
    }

    @Test
    void closeHallDoorWhenAllLightsTurnOn() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        try {
            SmartHome smartHome = provider.getSmartHome();
            LightEventHandler lightEventHandler = new LightEventHandler(smartHome);
            eventForEachLight(smartHome, lightEventHandler, SensorEventType.LIGHT_ON);
            HallDoorEventHandler hallDoorEventHandler = new HallDoorEventHandler(smartHome, new PrintCommandSender());
            closeHallDoor(hallDoorEventHandler);
            checkAllLights(smartHome);
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
            eventForEachLight(smartHome, lightEventHandler, SensorEventType.LIGHT_OFF);
            HallDoorEventHandler hallDoorEventHandler = new HallDoorEventHandler(smartHome, new PrintCommandSender());
            closeHallDoor(hallDoorEventHandler);
            checkAllLights(smartHome);
        } catch (IOException exc) {
            System.out.println("File reading error!");
            assert(false);
        }
    }
}