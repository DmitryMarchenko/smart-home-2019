package ru.sbt.mipt.oop.remotecontrol;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeProvider;
import ru.sbt.mipt.oop.alarm.TurnOnAlarmState;
import ru.sbt.mipt.oop.json.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomeobjects.Light;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RCLightsTest {

    @Test
    void turnOnAllLightsTest() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        MyRemoteControl remoteControl = new MyRemoteControl("2", smartHome);
        remoteControl.registryCommand("C", new TurnOnAllLightsCommand());
        remoteControl.onButtonPressed("C", "2");
        smartHome.execute(obj -> {
            if (obj instanceof Light) {
                Light light = (Light) obj;
                assertTrue(light.isOn());
            }
        });
    }

    @Test
    void turnOffAllLightsTest() {
        SmartHomeProvider provider = new JsonSmartHomeReader("smart-home-1.js");
        SmartHome smartHome = provider.getSmartHome();
        MyRemoteControl remoteControl = new MyRemoteControl("2", smartHome);
        remoteControl.registryCommand("C", new TurnOffAllLightsCommand());
        remoteControl.onButtonPressed("C", "2");
        smartHome.execute(obj -> {
            if (obj instanceof Light) {
                Light light = (Light) obj;
                assertFalse(light.isOn());
            }
        });
    }
}
