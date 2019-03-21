package ru.sbt.mipt.oop.handlestrategy;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomeobjects.Light;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LightEventHandlerTest {

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

    private void checkAllLights(SmartHome smartHome, boolean mode) {
        smartHome.execute(obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            if (mode) {
                assertTrue(light.isOn());
            } else {
                assertFalse(light.isOn());
            }
        });
    }

    @org.junit.jupiter.api.Test
    void turnOnAllLights() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        try {
            SmartHome smartHome = provider.getSmartHome();
            LightEventHandler lightEventHandler = new LightEventHandler(smartHome);
            eventForEachLight(smartHome, lightEventHandler, SensorEventType.LIGHT_ON);

            checkAllLights(smartHome, true);
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
            eventForEachLight(smartHome, lightEventHandler, SensorEventType.LIGHT_OFF);

            checkAllLights(smartHome, false);
        } catch (IOException exc) {
            System.out.println("File reading error!");
            assert(false);
        }
    }
}